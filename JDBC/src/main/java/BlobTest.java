import org.junit.Test;
import utils.JDBCUtils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BlobTest {

    @Test
    public void testInsertBlob() {
        Connection connection = null;
        PreparedStatement ps = null;
        FileInputStream fis = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into student values(?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);

            ps.setObject(1, 1);
            ps.setObject(2, "kkk");
            ps.setObject(3, null);

            fis = new FileInputStream(new File("path"));
            ps.setBlob(4, fis);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, ps, fis);
        }

    }
}
