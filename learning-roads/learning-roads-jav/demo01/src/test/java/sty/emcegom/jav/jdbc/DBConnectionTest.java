package sty.emcegom.jav.jdbc;

import com.mysql.cj.jdbc.Driver;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class DBConnectionTest {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3307/demo?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    public static final String USER = "root";
    public static final String PWD = "123456";

    private Connection connection;
    private PreparedStatement preparedStatement;


    @Before
    public void buildConnection(){
        try {
            connection= DriverManager.getConnection(JDBC_URL, USER, PWD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection1() throws Exception {
        Driver driver = new Driver();
        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PWD);
        Connection conn = driver.connect(JDBC_URL, properties);
        if (!conn.isClosed()) {
            System.out.println(conn);
            conn.close();
        }
    }

    @Test
    public void testConnection2() throws Exception {
        String clazzName = "com.mysql.cj.jdbc.Driver";
        Class<?> clazz = Class.forName(clazzName);
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();

        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PWD);
        Connection conn = driver.connect(JDBC_URL, properties);
        if (!conn.isClosed()) {
            System.out.println(conn);
            conn.close();
        }
    }


    @Test
    public void testConnection3() throws Exception {
        String clazzName = "com.mysql.cj.jdbc.Driver";
        Class<?> clazz = Class.forName(clazzName);
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();

        DriverManager.registerDriver(driver);

        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PWD);

        if (!conn.isClosed()) {
            System.out.println(conn);
            conn.close();
        }
    }


    @Test
    public void testConnection4() throws Exception {
//        String clazzName = "com.mysql.cj.jdbc.Driver";
//        Class.forName(clazzName);

        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PWD);

        if (!conn.isClosed()) {
            System.out.println(conn);
            conn.close();
        }
    }

    @Test
    public void testConnection5() throws Exception {
        InputStream is = DBConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        Class.forName(driverClass);

        Connection conn = DriverManager.getConnection(url, user, password);

        if (!conn.isClosed()) {
            System.out.println(conn);
            conn.close();
        }
    }


    @Test
    public void testSQLOperation1() throws Exception {
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PWD);
        Statement statement = conn.createStatement();

//        boolean execute = statement.execute("INSERT INTO `demo_user` (name, age) values ('Jack', '18')");
        ResultSet resultSet = statement.executeQuery("select * from `demo_user`");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getInt(3));
            System.out.println(resultSet.getDate(4));
        }
        // 增删改
        // executeUpdate
        // 查
        // executeQuery



        statement.close();
        conn.close();
    }
}
