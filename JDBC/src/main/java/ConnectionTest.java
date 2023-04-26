import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 创建连接驱动,获取连接类
 * @author 7up
 */
public class ConnectionTest {

    /**
     * @throws Exception
     * 通过第三方jar包获取数据库驱动:写死,只能获取mysql
     */
    @Test
    public void testConnection1() throws Exception {

        Driver driver = new com.mysql.cj.jdbc.Driver();

        String db = "test_master_slave";
        String append = "?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        String url = "jdbc:mysql://localhost:3306/" + db + append;

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");

        Connection connection = driver.connect(url, info);
        System.out.println(connection);
    }

    /**
     * @throws Exception
     * 改进1:通过反射动态获取
     */
    @Test
    public void testConnection2() throws Exception {
        // 使用反射获取驱动对象
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.getConstructor().newInstance();

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");

        String db = "test_master_slave";
        String append = "?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        String url = "jdbc:mysql://localhost:3306/" + db + append;

        Connection connection = driver.connect(url, info);
        System.out.println(connection);
    }

    /**
     * @throws Exception
     * 改进2:DriverManager工厂方法
     */
    @Test
    public void testConnection3() throws Exception {
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.getConstructor().newInstance();

        DriverManager.registerDriver(driver);

        String db = "test_master_slave";
        String append = "?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        String url = "jdbc:mysql://localhost:3306/" + db + append;

        String user = "root";
        String password = "root";

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }

    /**
     * @throws Exception
     * 优化3, 源码中DriverManager类初始化时自动使用了DriverManager
     */
    @Test
    public void testConnection4() throws Exception {
        // 源码中类初始化时自动使用了DriverManager
        Class.forName("com.mysql.cj.jdbc.Driver");

        String db = "test_master_slave";
        String append = "?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        String url = "jdbc:mysql://localhost:3306/" + db + append;

        String user = "root";
        String password = "root";

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }

    /**
     * @throws Exception
     * 最终优化:配置写入文件,数据代码分离,只用改配置文件,避免重新打包
     */
    public Connection getConnection() throws Exception {
        // 以流形式读取并设置键值对配置文件
        InputStream inputStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

//        读取kv键值对
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driverClass = properties.getProperty("driverClass");

        // 加载驱动,DriverManager默认进行初始化
        Class.forName(driverClass);

        // 使用DriverManager获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
    }
}
