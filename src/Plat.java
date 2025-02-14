import java.io.Serializable;

public class Plat implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private double prix;

    public Plat(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }
}
