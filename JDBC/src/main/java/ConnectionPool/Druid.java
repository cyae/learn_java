package ConnectionPool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Druid {

    private static DataSource source;
    static {
        try {
            Properties properties = new Properties();

            FileInputStream fis = new FileInputStream("src\\main\\resources\\jdbc.properties");

            properties.load(fis);
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection druidTest() throws SQLException {
        return source.getConnection();
    }
}
