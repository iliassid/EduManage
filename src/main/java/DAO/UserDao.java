package DAO;

import model.Cour;
import model.User;

import java.sql.*;

public class UserDao {
    String url = "jdbc:mysql://localhost:3306/gestion-etudiant";
    String username = "root";
    String password = "";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trouvé: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    public User getUser() {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = 1";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User( id,username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
