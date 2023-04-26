import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatchTest {

    /**
     * 通过循环依次插入
     * 缺点在于每次都需IO, 传输速度>>IO
     */
    @Test
    public void testBatchInsert1() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "insert into student(id, name) values (?, ?)";
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 20; i++) {
                ps.setObject(1, i + 3);
                ps.setObject(2, "name_" + i);

                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, ps);
        }

    }

    /**
     * 通过循环依次插入, 但积累一定batch后才execute
     * 采用Scanner->BufferedReader思想, 设置一个Buffer, Buffer满了则执行
     * 减少IO次数, 理想情况下传输速度≈均摊IO
     */
    @Test
    public void testBatchInsert2() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "insert into student(id, name) values (?, ?)";
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 20; i++) {
                ps.setObject(1, i + 3);
                ps.setObject(2, "name_" + i);

                ps.addBatch();

                // 每隔10 次执行
                if (i % 10 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, ps);
        }

    }

    /**
     * 除了可以在业务端提速, 服务器端也能优化
     * 默认每插入一次都commit, 耗时
     * 可以在传输前关闭auto commit, 手动commit, 传输后再开启
     */
    @Test
    public void testBatchInsert3() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtils.getConnection();

            connection.setAutoCommit(false);

            String sql = "insert into student(id, name) values (?, ?)";
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 20; i++) {
                ps.setObject(1, i + 3);
                ps.setObject(2, "name_" + i);

                ps.addBatch();

                // 每隔10 次执行
                if (i % 10 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }

            connection.commit();
            connection.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, ps);
        }

    }
}
