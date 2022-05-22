```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class TestConnection {

    private static Connection connection;

    //直接创建
    static {
        try {
             new TestConnection("jdbc:mysql://localhost:3306/music","root","zq969811.");
            //数据库连接也可以配到环境变量中 反正可以不固定
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
/*    private  String url;
    private String user;
    private String pwd;*/

    private TestConnection(String URL,String user,String pwd) throws SQLException {
        start(URL,user,pwd);
    }

    private void start(String URL,String user,String pwd) throws SQLException {
        if(connection!=null){
            connection.close();
        }
        connection = DriverManager.getConnection(URL,user,pwd);
        System.out.println("连接一次");
    }

    public static  Connection getConnection() throws SQLException {
        if (connection==null){
            System.out.println("未连接");
            return null;
        }
        return connection;
    }

    public static void close() throws SQLException {
        connection.close();
        System.out.println("数据库已关闭");
    }
}
```

```java
import java.sql.Connection;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {

       // new TestConnection("jdbc:mysql://localhost:3306/music","root","zq969811.");
        Connection connection1 = TestConnection.getConnection();

        if (connection1==null)
            System.out.println("null");

        Connection connection2 = TestConnection.getConnection();

        if (connection2==null)
            System.out.println("null");

        testConnection();

        TestConnection.close();
    }

    public static void testConnection() throws SQLException {
        Connection connection = TestConnection.getConnection();
        if (connection==null)
            System.out.println("null");
    }
}
```