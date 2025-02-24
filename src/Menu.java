import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Plat> plats = new ArrayList<>();

    public void ajouterPlat(Plat plat) {
        plats.add(plat);
    }
    public void afficherMenu() {
        if (plats.isEmpty()) {
            System.out.println("⚠ Le menu est vide.");
            return;
        }
        System.out.println("\n📋 Menu du restaurant :");
        for (Plat plat : plats) {
            System.out.println("- " + plat.getNom() + " : " + plat.getPrix() + "€");
        }
    }

    public Plat trouverPlat(String nomPlat) {
        for (Plat plat : plats) {
            if (plat.getNom().equalsIgnoreCase(nomPlat)) {
                return plat;
            }
        }
        return null; // Plat non trouvé
    }
}
