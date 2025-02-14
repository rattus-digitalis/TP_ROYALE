import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String nom;
    private String adresse;
    private String typeCuisine;
    private Menu menu;
    private List<Employe> employes;

    // 🔹 Constructeur avec initialisation correcte des listes
    public Restaurant(String nom, String adresse, String typeCuisine) {
        this.nom = nom;
        this.adresse = adresse;
        this.typeCuisine = typeCuisine;
        this.menu = new Menu();
        this.employes = new ArrayList<>();
    }

    // 🔹 Getters
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getTypeCuisine() { return typeCuisine; }
    public Menu getMenu() { return menu; }
    public List<Employe> getEmployes() { return employes; }

    // 🔹 Affiche les informations du restaurant
    public void afficherRestaurant() {
        System.out.println("\n📌 Restaurant : " + nom);
        System.out.println("📍 Adresse    : " + adresse);
        System.out.println("🍽 Cuisine   : " + typeCuisine);
    }

    // 🔹 Sauvegarde le restaurant dans un fichier
    public void sauvegarderRestaurant() {
        try {
            File file = new File("data/restaurants.txt");
            file.getParentFile().mkdirs(); // Assure que le dossier data/ existe

            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(nom + ";" + adresse + ";" + typeCuisine + "\n");
                System.out.println("✅ Restaurant sauvegardé avec succès !");
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la sauvegarde du restaurant : " + e.getMessage());
        }
    }

    // 🔹 Charge la liste des restaurants depuis le fichier restaurants.txt
    public static List<Restaurant> chargerRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        File file = new File("data/restaurants.txt");

        if (!file.exists()) {
            System.out.println("⚠ Aucun restaurant enregistré.");
            return restaurants;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] infos = ligne.split(";");
                if (infos.length == 3) {
                    restaurants.add(new Restaurant(infos[0], infos[1], infos[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors du chargement des restaurants : " + e.getMessage());
        }
        return restaurants;
    }
}
