import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Plat> plats = new ArrayList<>();

    public void ajouterPlat(Plat plat) {
        plats.add(plat);
        sauvegarderPlat(plat);
    }

    public void sauvegarderPlat(Plat plat) {
        try (FileWriter writer = new FileWriter("data/restaurants.txt", true)) {
            writer.write(plat.getNom() + ";" + plat.getPrix() + "\n");
            System.out.println("Plat ajouté au menu !");
        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }
}
