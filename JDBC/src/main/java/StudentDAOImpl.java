import pojo.Person;

import java.sql.Connection;
import java.util.List;

public class StudentDAOImpl extends DAO implements StudentDAO {

    @Override
    public void insert(Connection connection, Person person) {
        String sql = "insert into student values(?,?,?,?)";

        // 可变参数必须放在最后
        testCUD(connection, sql, person.getId(), person.getName(), person.getBirth(), null);
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from student where id=?";
        testCUD(connection, sql, id);
    }

    @Override
    public void update(Connection connection, Person person) {
        String sql = "update student set id=?, name=?, birth=?";
        testCUD(connection, sql, person.getId(), person.getName(), person.getBirth());
    }

    @Override
    public Person selectById(Connection connection, int id) {
        String sql = "select id,name,birth from student where id=?";
        List<Person> people = testR(connection, Person.class, sql, id);

        return people.get(0);
    }

    @Override
    public List<Person> selectAll(Connection connection) {
        String sql = "select id,name,birth from student";
        List<Person> people = testR(connection, Person.class, sql);

        return people;
    }

    @Override
    public Long getCount(Connection connection) {
        String sql = "select count(*) from student";

        return testSpecial(connection, sql);
    }
}
