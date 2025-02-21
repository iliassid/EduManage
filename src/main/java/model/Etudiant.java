package model;

import java.util.List;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String naissance;
    List<Cour> cours;

    public Etudiant(int id, List<Cour> cours, String naissance, String email, String prenom, String nom) {
        this.id = id;
        this.cours = cours;
        this.naissance = naissance;
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
    }

    public Etudiant(String nom, String prenom, String email, String naissance) {
        this.nom = nom;
        this.prenom = prenom;
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

    public List<Cour> getCours() {
        return cours;
    }

    public void setCours(List<Cour> cours) {
        this.cours = cours;
    }
}
