import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String nom;
    private String adresse;
    private String typeCuisine;
    private List<Employe> employes;
    private Menu menu;
    private List<Commande> commandes;

    public Restaurant(String nom, String adresse, String typeCuisine) {
        this.nom = nom;
        this.adresse = adresse;
        this.typeCuisine = typeCuisine;
        this.employes = new ArrayList<>();
        this.menu = new Menu();
        this.commandes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public Menu getMenu() {
        return menu;
    }

    // Méthodes liées aux employés
    public void ajouterEmploye(Employe employe) {
        employes.add(employe);
    }

    public void afficherEmployes() {
        if (employes.isEmpty()) {
            System.out.println("Aucun employé dans ce restaurant.");
            return;
        }
        for (Employe employe : employes) {
            System.out.println(employe);
        }
    }

    public void supprimerEmploye(String nomEmploye) {
        employes.removeIf(e -> e.getNom().equals(nomEmploye));
        System.out.println("Employé supprimé !");
    }

    // Méthodes liées aux commandes
    public void ajouterCommande(Commande commande) {
        commandes.add(commande);
    }

    public void afficherCommandes() {
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande pour ce restaurant.");
            return;
        }
        for (Commande commande : commandes) {
            System.out.println(commande);
        }
    }

    public void sauvegarderRestaurant() {
        // Implémentation de la sauvegarde si nécessaire.
    }
}