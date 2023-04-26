package dao;

import model.Person;

import java.sql.Connection;
import java.util.List;

/**
 * 针对每张表的具体业务需求, 规定接口, 以基本的通用DAO内的操作实现下述接口方法
 * @see DAO
 * @author Rellik
 */
@SuppressWarnings("unused")
public interface StudentDAO {

    /**
     * 插入
     * @param connection 事务开启的连接
     * @param person pojo
     */
    void insert(Connection connection, Person person);

    /**
     * 按照关键字id删除
     * @param connection 事务开启的连接
     * @param id id
     */
    void deleteById(Connection connection, int id);

    /**
     * 更改person的信息
     * @param connection 事务开启的连接
     * @param person pojo
     */
    void update(Connection connection, Person person);

    /**
     * 按照关键字id查找
     * @param connection 事务开启的连接
     * @param id id
     * @return 查找到的person实例
     */
    Person selectById(Connection connection, int id);

    /**
     * 返回所有记录的列表
     * @param connection 事务开启的连接
     * @return 所有记录
     */
    List<Person> selectAll(Connection connection);

    /**
     * 计算表中所有记录数目
     * @param connection 事务开启的连接
     * @return 表中记录条数
     */
    Long getCount(Connection connection);
}
