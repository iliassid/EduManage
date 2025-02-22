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

    public void insertEtudiant(Etudiant etudiant) throws SQLException {
        String sqlEtudiant = "INSERT INTO etudiant (nomEtudiant, prenom, email, dateNaissance) VALUES (?, ?, ?, ?)";
        String sqlInscription = "INSERT INTO inscrit (idCour, idEtudiant, dateInscr) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmtEtudiant = connection.prepareStatement(sqlEtudiant, Statement.RETURN_GENERATED_KEYS)) {

            // Insérer l'étudiant
            stmtEtudiant.setString(1, etudiant.getNom());
            stmtEtudiant.setString(2, etudiant.getPrenom());
            stmtEtudiant.setString(3, etudiant.getEmail());
            stmtEtudiant.setString(4, etudiant.getNaissance());
            stmtEtudiant.executeUpdate();

            // Récupérer l'ID généré
            ResultSet generatedKeys = stmtEtudiant.getGeneratedKeys();
            int idEtudiant = -1;
            if (generatedKeys.next()) {
                idEtudiant = generatedKeys.getInt(1);
            }

            // Inscrire l'étudiant aux cours
            if (idEtudiant != -1 && etudiant.getCours() != null) {
                try (PreparedStatement stmtInscription = connection.prepareStatement(sqlInscription)) {
                    for (Cour cour : etudiant.getCours()) {
                        stmtInscription.setInt(1, cour.getId());
                        stmtInscription.setInt(2, idEtudiant);
                        stmtInscription.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Date actuelle
                        stmtInscription.executeUpdate();
                    }
                }
            }
        }
    }

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