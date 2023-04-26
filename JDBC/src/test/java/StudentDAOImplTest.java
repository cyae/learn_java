import org.junit.Test;
import pojo.Person;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;

import static org.junit.Assert.*;

public class StudentDAOImplTest {

    StudentDAOImpl dao = new StudentDAOImpl();

    @Test
    public void insert() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Person person = new Person(23, "123", new Date(242344344L));
            dao.insert(connection, person);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }

    }

    @Test
    public void deleteById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void selectById() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void getCount() {
    }
}