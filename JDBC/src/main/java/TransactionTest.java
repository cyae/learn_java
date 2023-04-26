import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionTest {

    /**
     * 由于实现方法中每次操作都创建并关闭连接, 而关闭连接时, 会提交事务
     * 所以如果sql中间出现异常或拥堵, 无法回滚到原始状态
     */
    @Test
    public void testNoTx() {
        String sql1 = "insert into `student` values (?, ?, ?)";
        Object[] args1 = new Object[]{3, "luffy"};
        StatementTest.testCUD(sql1, args1);

        // 模拟出现异常, 由于insert执行完后关闭连接, 自动提交, 所以回滚失效
        System.out.println(10/ 0);

        String sql2 = "delete from `student` where id=?";
        StatementTest.testCUD(sql2, 2);

    }

    /**
     * 支持事务的testCUD, 连接不再由具体sql操作负责, 而是转移给事务层面
     */
    @Test
    public void testWithTx() {
        // 开启事务, 获得连接
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();

            // 将自动提交关闭
            connection.setAutoCommit(false);

            String sql1 = "...";
            Object[] args1 = new Object[]{3, "luffy"};
            StatementTest.testCUD(sql1, args1);

            String sql2 = "...";
            Object[] args2 = new Object[]{5, "nami"};
            StatementTest.testCUD(sql2, args2);

            // 手动提交
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();

            // 异常被捕获, 则回滚
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {

            // 恢复原状
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JDBCUtils.closeConnection(connection, null);
        }

    }
}
