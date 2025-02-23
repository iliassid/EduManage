package model;

public class Cour {
    private int id;
    private String nomCour;
    private String descriprion;

    public Cour(int id, String nomCour) {
        this.id = id;
        this.nomCour = nomCour;
        this.descriprion = descriprion;
    }

    public Cour(int id, String nomCour, String descriprion) {
        this.id = id;
        this.nomCour = nomCour;
        this.descriprion = descriprion;
    }

    public Cour(String nomCour, String descriprion) {
        this.nomCour = nomCour;
        this.descriprion = descriprion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCour() {
        return nomCour;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }
}