package DAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cour;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EduManageDAO {
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


    public void insertCour(Cour cour) throws SQLException {
        String sql = "INSERT INTO cours (nomCour, descriprion) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            System.out.println("sxxxx");
            preparedStatement.setString(1, cour.getNomCour());
            preparedStatement.setString(2, cour.getDescriprion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List< Cour> selectAllCours() {
        List < Cour > cours = new ArrayList< >();

        String sql = "select* from cours";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idCour");
                String nomCour = rs.getString("nomCour");
                String descriprion = rs.getString("descriprion");
                cours.add(new Cour(id, nomCour,descriprion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cours;
    }
}
