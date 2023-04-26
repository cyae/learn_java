import pojo.Person;

import java.sql.Connection;
import java.util.List;

public interface StudentDAO {

    void insert(Connection connection, Person person);
    void deleteById(Connection connection, int id);
    void update(Connection connection, Person person);
    Person selectById(Connection connection, int id);
    List<Person> selectAll(Connection connection);

    Long getCount(Connection connection);
}
