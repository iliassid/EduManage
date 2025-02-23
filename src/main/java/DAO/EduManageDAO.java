package DAO;

import model.Cour;

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
                cours.add(new Cour(id, nomCour));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cours;
    }
    public boolean deleteCours(Long id) throws SQLException {
        boolean rowDeleted;
         String  DELETE_USERS_SQL  =  " delete FROM cours where idCour = ?; " ;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public boolean updateCours(Cour cour) throws SQLException {
        boolean rowUpdated;
        String UPDATE_USERS_SQL = "UPDATE cours SET nomCour = ?, descriprion = ? WHERE idCour = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, cour.getNomCour());
            statement.setString(2, cour.getDescriprion());
            statement.setInt(3, cour.getId()); // Ajout de l'ID manquant
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public Cour selectCours(int id) {
        Cour cour = null;
        String sql = "SELECT * FROM cours WHERE idCour = ?";
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nom = rs.getString("nomCour");
                String descriprion = rs.getString("descriprion");


                cour = new Cour(id, nom, descriprion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cour;
    }
}