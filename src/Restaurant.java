import java.io.FileWriter;
import java.io.IOException;

public class Restaurant {
    private String nom;
    private String adresse;
    private String typeCuisine;

    public Restaurant(String nom, String adresse, String typeCuisine) {
        this.nom = nom;
        this.adresse = adresse;
        this.typeCuisine = typeCuisine;
    }

    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getTypeCuisine() { return typeCuisine; }

    public void sauvegarderRestaurant() {
        try (FileWriter writer = new FileWriter("data/restaurants.txt", true)) {
            writer.write(nom + ";" + adresse + ";" + typeCuisine + "\n");
            System.out.println("Restaurant sauvegardé !");
        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }
}
