package dao;

import utils.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseAccessObject
 * 封装所有考虑事务的基本CRUD操作, 作为抽象父类
 * @param <T> 根据ORM规则, 具体针对每张表的接口方法所操作的对象是单一固定的,
 *           所以可向基本DAO传入类型T, 子类DAOImpl实现接口方法时规定T的具体类型,
 *           当创建子类对象DAOImpl时, 先加载父类基本DAO, 此时需要以子类的this视角,
 *           在父类构造器或代码块中获得T.class
 *           免去为不同实现子类获取clazz
 *
 * @author Rellik
 */
@SuppressWarnings("all")
public abstract class DAO<T> {

    private Class<T> clazz = null;

    {
        // 在继承基本DAO的子类中, 获取父类基本DAO的泛型类型T.class(泛型T在子类创建时确定为具体的待操作对象类型)
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }

    /**
     * @param connection 事务开启的连接
     * @param sql 增, 删. 插入sql语句
     * @param args 查询字段
     * @return 0未命中 | 1匹配条数
     */
    public int updateDAO(Connection connection, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; ++i) {
                ps.setObject(i + 1, args[i]);
            }

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, ps);
        }

        return 0;
    }

    /**
     * 返回多条结果的查询
     * @param connection 事务开启的连接
     * @param clazz
     * @param sql 选择sql语句
     * @param args 查询字段
     * @param <T> 已省略
     * @return 查询结果列表
     */
    public List<T> selectDAO(Connection connection, String sql, Object... args) {
        PreparedStatement ps = null;
        List<T> res = null;
        ResultSet rs = null;

        try {
            res = new ArrayList<>();

            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();

                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);

                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 通过反射,将t的colLabel属性赋值为colValue
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                res.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, ps, rs);
        }

        return res;
    }

    /**
     * 针对规约函数的query
     * @param connection 事务开启的连接
     * @param sql 函数sql语句
     * @param args 查询字段
     */
    public <T> T funcDAO(Connection connection, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                return (T) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, ps, rs);
        }

        return null;
    }
}
