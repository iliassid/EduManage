package DAO;

import model.Cour;
import model.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Insert a new student and associate them with selected courses
    public void insertEtudiant(Etudiant etudiant, int[] courseIds) throws SQLException {
        String sqlEtudiant = "INSERT INTO etudiant (nomEtudiant, prenom, email, dateNaissance) VALUES (?, ?, ?, ?)";
        String sqlInscription = "INSERT INTO inscrit (idCour, idEtudiant, dateInscr) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmtEtudiant = connection.prepareStatement(sqlEtudiant, Statement.RETURN_GENERATED_KEYS)) {

            // Insert the student
            stmtEtudiant.setString(1, etudiant.getNom());
            stmtEtudiant.setString(2, etudiant.getPrenom());
            stmtEtudiant.setString(3, etudiant.getEmail());
            stmtEtudiant.setString(4, etudiant.getNaissance());
            stmtEtudiant.executeUpdate();

            // Retrieve the generated student ID
            ResultSet generatedKeys = stmtEtudiant.getGeneratedKeys();
            int idEtudiant = -1;
            if (generatedKeys.next()) {
                idEtudiant = generatedKeys.getInt(1);
            }

            // Associate the student with the selected courses
            if (idEtudiant != -1 && courseIds != null && courseIds.length > 0) {
                try (PreparedStatement stmtInscription = connection.prepareStatement(sqlInscription)) {
                    for (int courseId : courseIds) {
                        stmtInscription.setInt(1, courseId);
                        stmtInscription.setInt(2, idEtudiant);
                        stmtInscription.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Current date
                        stmtInscription.executeUpdate();
                    }
                }
            }
        }
    }

    // Fetch all students with their associated courses
    public List<Etudiant> getAllEtudiants() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT e.idEtudiant, e.nomEtudiant, e.prenom, e.email, e.dateNaissance, " +
                "c.idCour, c.nomCour " +
                "FROM etudiant e " +
                "LEFT JOIN inscrit i ON e.idEtudiant = i.idEtudiant " +
                "LEFT JOIN cours c ON i.idCour = c.idCour " +
                "ORDER BY e.idEtudiant";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            Map<Integer, Etudiant> etudiantMap = new HashMap<>();

            while (rs.next()) {
                int idEtudiant = rs.getInt("idEtudiant");
                Etudiant etudiant = etudiantMap.get(idEtudiant);

                if (etudiant == null) {
                    etudiant = new Etudiant(
                            rs.getString("nomEtudiant"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("dateNaissance"),
                            new ArrayList<>()
                    );
                    etudiant.setId(idEtudiant);
                    etudiantMap.put(idEtudiant, etudiant);
                }

                int idCour = rs.getInt("idCour");
                String nomCour = rs.getString("nomCour");
                if (idCour > 0 && nomCour != null) {
                    Cour cour = new Cour(idCour, nomCour);
                    etudiant.getCours().add(cour);
                }
            }
            etudiants.addAll(etudiantMap.values());
        }
        return etudiants;
    }

    // Fetch a student by ID with their associated courses
    public Etudiant selectEtudiant(int id) throws SQLException {
        String sql = "SELECT e.idEtudiant, e.nomEtudiant, e.prenom, e.email, e.dateNaissance, " +
                "c.idCour, c.nomCour " +
                "FROM etudiant e " +
                "LEFT JOIN inscrit i ON e.idEtudiant = i.idEtudiant " +
                "LEFT JOIN cours c ON i.idCour = c.idCour " +
                "WHERE e.idEtudiant=?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Etudiant etudiant = null;
            List<Cour> cours = new ArrayList<>();

            while (rs.next()) {
                if (etudiant == null) {
                    etudiant = new Etudiant(
                            rs.getString("nomEtudiant"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("dateNaissance"),
                            cours
                    );
                    etudiant.setId(rs.getInt("idEtudiant"));
                }

                int idCour = rs.getInt("idCour");
                String nomCour = rs.getString("nomCour");
                if (idCour > 0 && nomCour != null) {
                    Cour cour = new Cour(idCour, nomCour);
                    cours.add(cour);
                }
            }
            return etudiant;
        }
    }

    // Update a student and their associated courses
    public void updateEtudiant(Etudiant etudiant, int[] courseIds) throws SQLException {
        String sqlEtudiant = "UPDATE etudiant SET nomEtudiant=?, prenom=?, email=?, dateNaissance=? WHERE idEtudiant=?";
        String sqlDeleteInscriptions = "DELETE FROM inscrit WHERE idEtudiant=?";
        String sqlInsertInscription = "INSERT INTO inscrit (idCour, idEtudiant, dateInscr) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmtEtudiant = connection.prepareStatement(sqlEtudiant);
             PreparedStatement stmtDeleteInscriptions = connection.prepareStatement(sqlDeleteInscriptions)) {

            // Update the student
            stmtEtudiant.setString(1, etudiant.getNom());
            stmtEtudiant.setString(2, etudiant.getPrenom());
            stmtEtudiant.setString(3, etudiant.getEmail());
            stmtEtudiant.setString(4, etudiant.getNaissance());
            stmtEtudiant.setInt(5, etudiant.getId());
            stmtEtudiant.executeUpdate();

            // Delete existing course associations
            stmtDeleteInscriptions.setInt(1, etudiant.getId());
            stmtDeleteInscriptions.executeUpdate();

            // Insert new course associations
            if (courseIds != null && courseIds.length > 0) {
                try (PreparedStatement stmtInsertInscription = connection.prepareStatement(sqlInsertInscription)) {
                    for (int courseId : courseIds) {
                        stmtInsertInscription.setInt(1, courseId);
                        stmtInsertInscription.setInt(2, etudiant.getId());
                        stmtInsertInscription.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Current date
                        stmtInsertInscription.executeUpdate();
                    }
                }
            }
        }
    }

    // Delete a student and their course associations
    public void deleteEtudiant(int id) throws SQLException {
        String sqlDeleteInscriptions = "DELETE FROM inscrit WHERE idEtudiant=?";
        String sqlDeleteEtudiant = "DELETE FROM etudiant WHERE idEtudiant=?";

        try (Connection connection = getConnection();
             PreparedStatement stmtDeleteInscriptions = connection.prepareStatement(sqlDeleteInscriptions);
             PreparedStatement stmtDeleteEtudiant = connection.prepareStatement(sqlDeleteEtudiant)) {

            // Delete course associations
            stmtDeleteInscriptions.setInt(1, id);
            stmtDeleteInscriptions.executeUpdate();

            // Delete the student
            stmtDeleteEtudiant.setInt(1, id);
            stmtDeleteEtudiant.executeUpdate();
        }
    }
}
