import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "localhost");
    private static final String PORT = System.getenv().getOrDefault("DB_PORT", "3306");

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/shopping_cart_localization";

    private static final String USER = "root";
    private static final String PASSWORD = "mehdizaidane1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
