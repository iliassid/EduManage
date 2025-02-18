package model;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String naissance;

    public Etudiant(int id, String prenom, String nom, String email, String naissance) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.naissance = naissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }
}
