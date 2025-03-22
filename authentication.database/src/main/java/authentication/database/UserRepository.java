package authentication.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/account_app";
    private static final String USER = "root";
    private static final String PASSWORD = "199520";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public ResultSet getUserByEmailAndPassword(String email, String password) throws SQLException {
        Connection conn = getConnection();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        return stmt.executeQuery();
    }

    public boolean isEmailRegistered(String email) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public void registerUser(String name, String tel, String email, String password) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO users (name, tel, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, tel);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.executeUpdate();
        }
    }
}