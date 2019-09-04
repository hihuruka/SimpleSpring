import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectTest {
    private static final String driver = "com.mysql.jdbc.Driver"; // 数据库驱动
// 连接数据库的URL地址
    private static final String url = "jdbc:mysql://localhost:3306/sampledb?useUnicode=true&characterEncoding=UTF-8";
    private static final String username = "root";// 数据库的用户名
    private static final String password = "Protectme1";// 数据库的密码
    private static Connection conn = null;

// 静态代码块负责加载驱动

    static {
        try {
            Class.forName(driver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
// 单例模式返回数据库连接对象

    public static Connection getConnection() throws Exception {
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }
    public static void main(String[] args) {
        try {
            Connection conn = ConnectTest.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            String sql="INSERT INTO `t_user` VALUES (3,'admin3','123456',5,'2019-09-04 14:26:56','127.0.0.0');";
            stmt.execute(sql);
            conn.commit();
            if (conn != null) {
                System.out.println("数据库连接正常！");
            } else {
                System.out.println("数据库连接异常！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
