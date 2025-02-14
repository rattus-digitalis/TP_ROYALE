public class Employe {
    private String nom;
    private String prenom;
    private String role;
    private double salaire;

    public Employe(String nom, String prenom, String role, double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.salaire = salaire;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRole() {
        return role;
    }

    public double getSalaire() {
        return salaire;
    }
}
