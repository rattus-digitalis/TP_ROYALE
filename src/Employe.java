import java.io.FileWriter;
import java.io.IOException;

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

    public void sauvegarderEmploye() {
        try (FileWriter writer = new FileWriter("data/employes.txt", true)) {
            writer.write(nom + ";" + prenom + ";" + role + ";" + salaire + "\n");
            System.out.println("Employé sauvegardé !");
        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }
}
