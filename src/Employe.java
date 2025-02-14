import java.util.Date;

public class Employe {
    private String nom;
    private String prenom;
    private String role;
    private double salaire;
    private Date dateEmbauche;

    // Modifiez le constructeur pour inclure la date d'embauche
    public Employe(String nom, String prenom, String role, double salaire, Date dateEmbauche) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche; // Initialisation de la date d'embauche
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

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + " - " + role + " - Salaire : " + salaire + "€ - Date d'embauche : " + dateEmbauche;
    }
}
