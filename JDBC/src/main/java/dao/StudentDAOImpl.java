package dao;

import model.Person;

import java.sql.Connection;
import java.util.List;

/**
 * @author Rellik
 */
public class StudentDAOImpl extends DAO<Person> implements StudentDAO {

    @Override
    public void insert(Connection connection, Person person) {
        String sql = "insert into student values(?,?,?,?)";

        // 可变参数必须放在最后
        updateDAO(connection, sql, person.Id(), person.Name(), person.Birth(), null);
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from student where id=?";
        updateDAO(connection, sql, id);
    }

    @Override
    public void update(Connection connection, Person person) {
        String sql = "update student set name=?, birth=? where id=?";
        updateDAO(connection, sql, person.Name(), person.Birth(), person.Id());
    }

    @Override
    public Person selectById(Connection connection, int id) throws IndexOutOfBoundsException {
        String sql = "select id Id,name Name,birth Birth from student where id=?";
        List<Person> people = selectDAO(connection, sql, id);

        return people.get(0);
    }

    @Override
    public List<Person> selectAll(Connection connection) {
        String sql = "select id Id,name Name,birth Birth from student";

        return selectDAO(connection, sql);
    }

    @Override
    public Long getCount(Connection connection) {
        String sql = "select count(*) from student";

        return funcDAO(connection, sql);
    }
}
