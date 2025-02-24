package model;

import java.util.List;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String naissance;
    private List<Cour> cours;

    // Constructor for creating a new Etudiant
    public Etudiant(String nom, String prenom, String email, String naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }

    // Constructor for creating an Etudiant with associated courses
    public Etudiant(String nom, String prenom, String email, String naissance, List<Cour> cours) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
        this.cours = cours;
    }

    // Constructor for existing Etudiant with an ID and associated courses
    public Etudiant(int id, List<Cour> cours, String naissance, String email, String prenom, String nom) {
        this.id = id;
        this.cours = cours;
        this.naissance = naissance;
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter and setter for prenom
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter for naissance (date of birth)
    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    // Getter and setter for cours (courses)
    public List<Cour> getCours() {
        return cours;
    }

    public void setCours(List<Cour> cours) {
        this.cours = cours;
    }
}
