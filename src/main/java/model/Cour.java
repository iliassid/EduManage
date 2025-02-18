package model;

public class Cour {

    private String courName;
    private String description;

    public Cour(String courName, String description) {
        this.courName = courName;
        this.description = description;
    }

    public String getCourName() {
        return courName;
    }

    public void setCourName(String courName) {
        this.courName = courName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}