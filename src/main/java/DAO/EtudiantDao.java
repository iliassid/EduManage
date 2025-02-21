package DAO;

import model.Cour;
import model.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDao {
    private final String url = "jdbc:mysql://localhost:3306/gestion-etudiant";
    private final String username = "root";
    private final String password = "";

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

    // Insert a new student and enroll them in courses
    public void insertEtudiant(Etudiant etudiant) throws SQLException {
        String sqlEtudiant = "INSERT INTO etudiant (nomEtudiant, prenom, email, dateNaissance) VALUES (?, ?, ?, ?)";
        String sqlInscription = "INSERT INTO inscrit (idCour, idEtudiant, dateInscr) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmtEtudiant = connection.prepareStatement(sqlEtudiant, Statement.RETURN_GENERATED_KEYS)) {

            stmtEtudiant.setString(1, etudiant.getNom());
            stmtEtudiant.setString(2, etudiant.getPrenom());
            stmtEtudiant.setString(3, etudiant.getEmail());
            stmtEtudiant.setString(4, etudiant.getNaissance());
            stmtEtudiant.executeUpdate();

            // Retrieve the generated student ID


                try (PreparedStatement stmtInscription = connection.prepareStatement(sqlInscription)) {
                    for (Cour cour : etudiant.getCours()) {
                        stmtInscription.setInt(1, cour.getId());
                        stmtInscription.setInt(2, etudiant.getId());
                        stmtInscription.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Enrollment date
                        stmtInscription.executeUpdate();
                    }
                }
            }


    }

    // Retrieve all students
    public List<Etudiant> selectAllEtudiants() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Create an empty list for courses
                List<Cour> cours = new ArrayList<>();

                etudiants.add(new Etudiant(
                        rs.getInt("idEtudiant"),
                        cours, // Empty list for now
                        rs.getString("dateNaissance"),
                        rs.getString("email"),
                        rs.getString("prenom"),
                        rs.getString("nomEtudiant")
                ));
            }
        }
        return etudiants;
    }

    // Retrieve a student by ID
    public Etudiant selectEtudiant(int id) throws SQLException {
        String sql = "SELECT * FROM etudiant WHERE idEtudiant=?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Create an empty list for courses
                List<Cour> cours = new ArrayList<>();

                return new Etudiant(
                        rs.getInt("idEtudiant"),
                        cours, // Empty list for now
                        rs.getString("dateNaissance"),
                        rs.getString("email"),
                        rs.getString("prenom"),
                        rs.getString("nomEtudiant")
                );
            }
        }
        return null;
    }

    // Update a student
    public void updateEtudiant(Etudiant etudiant) throws SQLException {
        String sql = "UPDATE etudiant SET nomEtudiant=?, prenom=?, email=?, dateNaissance=? WHERE idEtudiant=?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getEmail());
            stmt.setString(4, etudiant.getNaissance());
            stmt.setInt(5, etudiant.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a student
    public void deleteEtudiant(int id) throws SQLException {
        String sql = "DELETE FROM etudiant WHERE idEtudiant=?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}