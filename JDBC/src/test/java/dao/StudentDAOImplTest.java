package dao;

import model.Person;
import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class StudentDAOImplTest {

    StudentDAOImpl studentDAO = new StudentDAOImpl();

    @Test
    public void insert() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Person person = new Person(77, "Leafy", new Date(2523656L));
            studentDAO.insert(connection, person);
            System.out.println("insert success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void deleteById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            int id = 77;
            studentDAO.deleteById(connection, id);
            System.out.println("deleteById success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void update() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Person person = new Person(3, "Gary", new Date(5252365236L));
            studentDAO.update(connection, person);
            System.out.println("update success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void selectById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            int id = 18;
            Person person = studentDAO.selectById(connection, id);
            System.out.println("selectById success!");
            System.out.println(person);
        } catch (Exception e) {
            System.out.println("no such person!");
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void selectAll() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            List<Person> people = studentDAO.selectAll(connection);
            System.out.println("selectAll success!");
            people.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void getCount() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Long sum = studentDAO.getCount(connection);
            System.out.println("Count success!");
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }
}