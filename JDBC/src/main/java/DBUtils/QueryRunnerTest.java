package DBUtils;

import ConnectionPool.Druid;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class QueryRunnerTest {

    @Test
    public void insert() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = Druid.druidTest();
            String sql = "insert into student values(?, ?, ?, ?)";
            queryRunner.update(connection, sql, 99, "harry", "1997-09-27", null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }
}
