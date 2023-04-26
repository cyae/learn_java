package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Rellik
 */
public class JDBCUtils {

    /**
     * @return 连接
     */
    public static Connection getConnection() throws Exception {
        // 以流形式读取并设置键值对配置文件
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
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
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeConnection(Connection connection, PreparedStatement ps) {
        // 关闭连接资源
        try {
            // 防止获取连接失败
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // 防止创建statement失败
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 方法重载
    public static void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) {
        // 关闭连接资源
        try {
            // 防止获取连接失败
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // 防止创建statement失败
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // 防止创建ResultSet失败
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 方法重载
    public static void closeConnection(Connection connection, PreparedStatement ps, FileInputStream fis) {
        // 关闭连接资源
        try {
            // 防止获取连接失败
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // 防止创建statement失败
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // 防止创建fis失败
            if (fis != null)
                fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}