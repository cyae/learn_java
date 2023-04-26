package ConnectionPool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCP {

    @Test
    public void testDBCP() throws SQLException, IOException {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driverClass = properties.getProperty("driverClass");
        BasicDataSource source = new BasicDataSource();

        source.setDriverClassName(driverClass);
        source.setUrl(url);
        source.setUsername(user);
        source.setPassword(password);

        source.setInitialSize(10);
        source.setMaxTotal(10);

        Connection connection = source.getConnection();
        System.out.println(connection);
    }

    private static DataSource source;
    static {
        try {
            Properties properties = new Properties();

            FileInputStream fis = new FileInputStream("jdbc.properties");

            properties.load(fis);
            source = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection testDBCP_Pros() throws SQLException, IOException {
        return source.getConnection();
    }
}
