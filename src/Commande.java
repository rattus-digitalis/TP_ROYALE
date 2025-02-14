import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Commande {
    private List<Plat> plats = new ArrayList<>();
    private double total = 0.0;

    public void ajouterPlat(Plat plat) {
        plats.add(plat);
        total += plat.getPrix();
    }

    public void sauvegarderCommande() {
        try (FileWriter writer = new FileWriter("data/commandes.txt", true)) {
            for (Plat plat : plats) {
                writer.write(plat.toString() + "\n");
            }
            writer.write("Total : " + total + "€\n");
            System.out.println("Commande sauvegardée !");
        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }
}
