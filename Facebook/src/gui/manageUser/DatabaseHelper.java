package gui.manageUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    private static final String JDBC_URL = "jdbc:mysql://localhost/facebook";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "tjsals2620";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getUserInfo(String ID) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM user WHERE user_ID = '" + ID + "'";
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
