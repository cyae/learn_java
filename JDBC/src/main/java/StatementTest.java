import pojo.Person;
import org.junit.Test;
import utils.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Rellik
 * 建立连接后,使用statement语句进行CRUD
 */
public class StatementTest {

    /**
     * ver1: Statement接口存在不便和弊端
     */
    @Test
    @SuppressWarnings("unused")
    public void testLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("id:");
        String id = scanner.next();

        // 使用Statement,1. 需要拼写字符串; 2. 可能导致精心编写的sql语句注入攻击
        String sql = "select id,name from student where id='" + id +"';";
        // ...
    }

    /**
     * ver2: 使用子接口PreparedStatement
     * 将查询sql分解为可变占位符?与不可变的主体部分, 能防止:
     * 拼接, 注入, 减少校验次数(假如多条查询sql, 不可变部分只用检查一次)
     * 支持Blob类型
     */
    @Test
    public void testInsert() {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = new ConnectionTest().getConnection();

            // 预编译sql语句,返回ps实例,使用安全的占位符?
            String sql = "insert into student values(?,?,?)";
            ps = connection.prepareStatement(sql);

            // 填充占位符
            ps.setInt(1,2);
            ps.setString(2,"jack");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1999-09-08");
            ps.setDate(3, new Date(date.getTime()));

            // 执行sql
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接资源
            try {
                // 防止获取连接失败
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                // 防止创建statement失败
                assert ps != null;
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * ver3: 将常用连接关闭操作封装成静态工具方法,直接调用
     */
    @Test
    public void testUpdate() {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            // 获取连接
            connection = JDBCUtils.getConnection();

            // 预编译sql,返回ps实例
            String sql = "update student set name=? where id=?";
            ps = connection.prepareStatement(sql);

            // 填充占位符?
            ps.setString(1, "rose1");
            ps.setInt(2, 1);

            // 执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.closeConnection(connection, ps);
        }

    }

    /**
     * ver4: 通用更改, 因为连接和关闭操作都一样,只有核心sql不同,所以可以使用模板设计模式
     * 可变量:sql语句及占位符
     */
    public static void testCUD(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; ++i) {
                ps.setObject(i + 1, args[i]);
            }

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, ps);
        }
    }


    /**
     * ver4.5: 支持事务的通用更改, 连接不再由具体sql管理
     */
    public static void testCUD(Connection connection, String sql, Object... args) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; ++i) {
                ps.setObject(i + 1, args[i]);
            }

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, ps);
        }
    }


    /**
     * 针对某表的查询, 有返回值, 且返回值类型确定
     */
    public static Person testQueryforCertainTBL(String sql, Object... args) {

        Connection connection = null;
        PreparedStatement ps = null;
        Person person = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 返回select到的结果集rs,保存一条记录的字段值
            rs = ps.executeQuery();

            // rsmd包含结果集的元数据:字段数目,字段名,字段别名
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                person = new Person();

                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);

                    // 获取字段的别名columnLabel, 而非字段名columnName, 防止表中字段名与POJO属性名存在差异
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 通过反射,将person的colLabel属性赋值为colValue
                    Field field = person.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(person, columnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, ps, rs);
        }

        return person;
    }

    /**
     * 通用查询, 有返回值, 且返回值的类型不确定
     *
     * @param clazz 返回类型, 通过反射+泛型确定
     * @param sql 查询语句
     * @param args 占位符参数
     * @param <T> 返回的泛型
     * @return 查找到的对象
     */
    public static <T> List<T> testQuery(Class<T> clazz, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        List<T> res = null;
        ResultSet rs = null;

        try {
            res = new ArrayList<>();

            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                T t = clazz.getConstructor().newInstance();

                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);

                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 通过反射,将t的colLabel属性赋值为colValue
                    Field field = clazz.getDeclaredField(columnLabel);
                    // 👆或者Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                res.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, ps, rs);
        }

        return res;
    }

    public static void main(String[] args) throws ParseException {
        // 加`是为了防止字段和mysql保留关键字重复,比如order订单和order by
        String sql1 = "insert into `student` values (?, ?, ?)";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse("1999-09-08");
        Date date1 = new Date(date.getTime());

        Object[] args1 = new Object[]{3, "luffy", date1};
        testCUD(sql1, args1);

        String sql2 = "delete from `student` where id=?";
        testCUD(sql2, 2);

        // 注意, 由于表中的字段名和POJO属性名存在命名规则差异, 比如:order_id <==> orderID;

        // 👇不好, 假如名称不一致, 会报错找不到Field
        // String sql3 = "select id, name, birth from `student` where id=?";

        // 所以声明sql语句时, 应该强制使用POJO的属性名作为表中字段的别名
        String sql3 = "select id as id, name as name, birth as birth from `student` where id=?";
        Person person = testQueryforCertainTBL(sql3, 1);
        System.out.println(person);

        String sql4 = "select id as id, name as name, birth as birth from `student` where id>?";
        List<Person> people = testQuery(Person.class, sql4, 0);
        people.forEach(System.out::println);

    }

}
