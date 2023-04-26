package ConnectionPool;

import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class C3P0 {

    @Test
    public void testC3P0_props() throws Exception {
        InputStream inputStream = C3P0.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driverClass = properties.getProperty("driverClass");

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(driverClass);
        cpds.setJdbcUrl(url);
        cpds.setUser(user);
        cpds.setPassword(password);

        cpds.setInitialPoolSize(10);
        Connection connection = cpds.getConnection();
        System.out.println(connection);

    }

    @Test
    public void testC3P0_XML() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource("intergalactoApp");

        Connection connection = cpds.getConnection();
        System.out.println(connection);

    }


    /**
     * 连接池是单例
     * @return Connection
     * @throws Exception
     */
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("intergalactoApp");
    public static Connection testC3P0_XML_Singleton() throws Exception {
        return cpds.getConnection();
    }
}
