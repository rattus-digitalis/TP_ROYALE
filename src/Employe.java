import java.io.Serializable;
import java.util.Date;

public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private String prenom;
    private String role;
    private double salaire;
    private Date dateEmbauche;

    public Employe(String nom, String prenom, String role, double salaire, Date dateEmbauche) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public double getSalaire() {
    return salaire;
}

    @Override
    public String toString() {
        return nom + " " + prenom + " (" + role + ")";
    }
}
