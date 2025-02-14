import java.util.*;

class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String role;
    private double salaire;

    public Employe(int id, String nom, String prenom, String role, double salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.salaire = salaire;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "Employé #" + id + " : " + nom + " " + prenom + " (" + role + "), Salaire : " + salaire + "€";
    }
}

class Plat {
    private String nom;
    private double prix;

    public Plat(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return nom + " - " + prix + "€";
    }
}

class Menu {
    private List<Plat> plats = new ArrayList<>();

    public void ajouterPlat(Plat plat) { plats.add(plat); }

    public void afficherMenu() {
        if (plats.isEmpty()) {
            System.out.println("Le menu est vide.");
        } else {
            for (Plat plat : plats) {
                System.out.println(plat);
            }
        }
    }
}

class Restaurant {
    private int id;
    private String nomRestaurant;
    private String adresse;
    private Menu menu = new Menu();
    private List<Employe> employes = new ArrayList<>();

    public Restaurant(int id, String nomRestaurant, String adresse) {
        this.id = id;
        this.nomRestaurant = nomRestaurant;
        this.adresse = adresse;
    }

    public int getId() { return id; }
    public String getNomRestaurant() { return nomRestaurant; }
    public Menu getMenu() { return menu; }

    public void ajouterEmploye(Employe employe) { employes.add(employe); }
    public void supprimerEmploye(int id) { employes.removeIf(e -> e.getId() == id); }
    
    public void afficherEmployes() {
        if (employes.isEmpty()) {
            System.out.println("Aucun employé dans ce restaurant.");
        } else {
            for (Employe employe : employes) {
                System.out.println(employe);
            }
        }
    }

    @Override
    public String toString() {
        return id + ". " + nomRestaurant + " - " + adresse;
    }
}

public class GestionRestaurants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Restaurant> restaurants = new ArrayList<>();
        int employeId = 1; // ID unique pour les employés

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Ajouter un restaurant");
            System.out.println("2. Ajouter un employé");
            System.out.println("3. Ajouter un plat au menu");
            System.out.println("4. Supprimer un employé");
            System.out.println("5. Afficher les employés");
            System.out.println("6. Quitter");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1: // Ajouter un restaurant
                    System.out.print("Nom du restaurant : ");
                    String nomResto = scanner.nextLine();
                    System.out.print("Adresse : ");
                    String adresse = scanner.nextLine();
                    restaurants.add(new Restaurant(restaurants.size() + 1, nomResto, adresse));
                    System.out.println("Restaurant ajouté !");
                    break;

                case 2: // Ajouter un employé
                    if (restaurants.isEmpty()) {
                        System.out.println("Ajoutez d'abord un restaurant !");
                        break;
                    }
                    afficherRestaurants(restaurants);
                    int restoIndex = selectionnerRestaurant(scanner, restaurants);
                    if (restoIndex == -1) break;

                    System.out.print("Nom de l'employé : ");
                    String nomEmploye = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenomEmploye = scanner.nextLine();
                    System.out.print("Rôle : ");
                    String role = scanner.nextLine();
                    System.out.print("Salaire : ");
                    double salaire = scanner.nextDouble();
                    scanner.nextLine();

                    restaurants.get(restoIndex).ajouterEmploye(new Employe(employeId++, nomEmploye, prenomEmploye, role, salaire));
                    System.out.println("Employé ajouté !");
                    break;

                case 3: // Ajouter un plat au menu
                    if (restaurants.isEmpty()) {
                        System.out.println("Ajoutez d'abord un restaurant !");
                        break;
                    }
                    afficherRestaurants(restaurants);
                    restoIndex = selectionnerRestaurant(scanner, restaurants);
                    if (restoIndex == -1) break;

                    System.out.print("Nom du plat : ");
                    String nomPlat = scanner.nextLine();
                    System.out.print("Prix du plat : ");
                    double prixPlat = scanner.nextDouble();
                    scanner.nextLine();

                    restaurants.get(restoIndex).getMenu().ajouterPlat(new Plat(nomPlat, prixPlat));
                    System.out.println("Plat ajouté au menu !");
                    break;

                case 4: // Supprimer un employé
                    if (restaurants.isEmpty()) {
                        System.out.println("Ajoutez d'abord un restaurant !");
                        break;
                    }
                    afficherRestaurants(restaurants);
                    restoIndex = selectionnerRestaurant(scanner, restaurants);
                    if (restoIndex == -1) break;

                    restaurants.get(restoIndex).afficherEmployes();
                    System.out.print("ID de l'employé à supprimer : ");
                    int employeIdToDelete = scanner.nextInt();
                    scanner.nextLine();

                    restaurants.get(restoIndex).supprimerEmploye(employeIdToDelete);
                    System.out.println("Employé supprimé !");
                    break;

                case 5: // Afficher les employés
                    if (restaurants.isEmpty()) {
                        System.out.println("Ajoutez d'abord un restaurant !");
                        break;
                    }
                    afficherRestaurants(restaurants);
                    restoIndex = selectionnerRestaurant(scanner, restaurants);
                    if (restoIndex == -1) break;

                    restaurants.get(restoIndex).afficherEmployes();
                    break;

                case 6: // Quitter
                    scanner.close();
                    System.out.println("Programme terminé !");
                    return;

                default:
                    System.out.println("Option invalide.");
            }
        }
    }

    /**
     * Affiche la liste des restaurants disponibles.
     */
    private static void afficherRestaurants(List<Restaurant> restaurants) {
        System.out.println("\n--- LISTE DES RESTAURANTS ---");
        for (Restaurant resto : restaurants) {
            System.out.println(resto);
        }
    }

    /**
     * Permet de sélectionner un restaurant et retourne son index.
     * Retourne -1 si le choix est invalide.
     */
    private static int selectionnerRestaurant(Scanner scanner, List<Restaurant> restaurants) {
        System.out.print("Sélectionnez un restaurant (ID) : ");
        int restoId = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne restante

        if (restoId < 1 || restoId > restaurants.size()) {
            System.out.println("ID invalide !");
            return -1;
        }
        return restoId - 1;
    }
}
