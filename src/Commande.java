import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Plat> plats;

    public Commande() {
        this.plats = new ArrayList<>();
    }

    public void ajouterPlat(Plat plat) {
        plats.add(plat);
    }

    // Calculer le prix total de la commande
    public double calculerTotal() {
        double total = 0;
        for (Plat plat : plats) {
            total += plat.getPrix();
        }
        return total;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Commande: ");
        for (Plat plat : plats) {
            sb.append(plat.getNom()).append(" (").append(plat.getPrix()).append("), ");
        }
        sb.append("Total: ").append(calculerTotal());
        return sb.toString();
    }
}
