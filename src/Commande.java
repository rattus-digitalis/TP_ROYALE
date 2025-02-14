import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Commande {
    private List<Plat> plats = new ArrayList<>();
    private double total = 0.0;

    public void ajouterPlat(Plat plat) {
        plats.add(plat);
        total += plat.getPrix();
    }

    public void afficherCommande() {
        if (plats.isEmpty()) {
            System.out.println("⚠ La commande est vide.");
            return;
        }

        System.out.println("\n📋 Contenu de la commande :");
        for (Plat plat : plats) {
            System.out.println("- " + plat.getNom() + " : " + plat.getPrix() + "€");
        }
        System.out.println("💰 Total : " + total + "€");
    }

    public double getTotal() {
        return total;
    }

    public List<Plat> getPlats() {
        return plats;
    }
}
