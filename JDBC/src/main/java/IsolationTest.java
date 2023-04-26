import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;

public class IsolationTest {
    @Test
    public void testIsolation() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    }
}
