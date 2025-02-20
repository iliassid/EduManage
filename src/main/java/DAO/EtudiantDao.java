package DAO;

import model.Cour;
import model.Etudiant;

import java.sql.*;

public class EtudiantDao {
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

                    try (PreparedStatement stmtInscription = connection.prepareStatement(sqlInscription)) {
                        for (Cour cour : etudiant.getCours()) {
                            stmtInscription.setInt(1, cour.getId());
                            stmtInscription.setInt(2, etudiant.getId());
                            stmtInscription.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Date d'inscription
                            stmtInscription.executeUpdate();
                        }
                    }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Erreur lors de l'insertion de l'étudiant.");
            }


        }






}
