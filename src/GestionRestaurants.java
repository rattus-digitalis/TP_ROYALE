import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionRestaurants {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Ajouter un restaurant");
            System.out.println("2. Ajouter un employé à un restaurant");
            System.out.println("3. Ajouter un plat au menu d'un restaurant");
            System.out.println("4. Supprimer un employé d'un restaurant");
            System.out.println("5. Afficher les employés d'un restaurant");
            System.out.println("6. Prendre une commande pour un restaurant");
            System.out.println("7. Afficher toutes les commandes d'un restaurant");
            System.out.println("8. Sauvegarder les commandes d'un restaurant");
            System.out.println("9. Quitter");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterRestaurant(scanner);
                case 2 -> ajouterEmploye(scanner);
                case 3 -> ajouterPlat(scanner);
                case 4 -> supprimerEmploye(scanner);
                case 5 -> afficherEmployes(scanner);
                case 6 -> prendreCommande(scanner);
                case 7 -> afficherCommandes(scanner);
                case 8 -> sauvegarderCommandes(scanner);
                case 9 -> {
                    scanner.close();
                    System.out.println("🔹 Fermeture du programme.");
                    return;
                }
                default -> System.out.println("❌ Option invalide. Réessayez.");
            }
        }
    }

    private static void ajouterRestaurant(Scanner scanner) {
        System.out.print("Nom du restaurant : ");
        String nom = scanner.nextLine();
        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();
        System.out.print("Type de cuisine : ");
        String typeCuisine = scanner.nextLine();

        Restaurant resto = new Restaurant(nom, adresse, typeCuisine);
        restaurants.add(resto);
        resto.sauvegarderRestaurant();
        System.out.println("✅ Restaurant ajouté !");
    }

    private static void ajouterEmploye(Scanner scanner) {
        if (restaurants.isEmpty()) {
            System.out.println("❌ Aucun restaurant disponible.");
            return;
        }

        afficherRestaurants();
        System.out.print("Choisissez un restaurant (ID) : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= restaurants.size()) {
            System.out.println("❌ Restaurant invalide.");
            return;
        }

        System.out.print("Nom de l'employé : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Rôle : ");
        String role = scanner.nextLine();
        System.out.print("Salaire : ");
        double salaire = scanner.nextDouble();
        scanner.nextLine();


        System.out.print("Date d'embauche (format yyyy-MM-dd) : ");
        String dateString = scanner.nextLine();
        Date dateEmbauche = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateEmbauche = dateFormat.parse(dateString);
        } catch (Exception e) {
            System.out.println("❌ Format de date invalide, la date actuelle sera utilisée.");
            dateEmbauche = new Date(); 
        }

        Employe employe = new Employe(nom, prenom, role, salaire, dateEmbauche);
        restaurants.get(index).ajouterEmploye(employe);
        System.out.println("✅ Employé ajouté !");
    }

    private static void afficherRestaurants() {
        if (restaurants.isEmpty()) {
            System.out.println("⚠ Aucun restaurant enregistré.");
            return;
        }
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getNom());
        }
    }

    private static void afficherEmployes(Scanner scanner) {
        if (restaurants.isEmpty()) {
            System.out.println("❌ Aucun restaurant disponible.");
            return;
        }

        afficherRestaurants();
        System.out.print("Choisissez un restaurant (ID) : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= restaurants.size()) {
            System.out.println("❌ Restaurant invalide.");
            return;
        }

        restaurants.get(index).afficherEmployes();
    }

    private static void supprimerEmploye(Scanner scanner) {
        afficherRestaurants();
        System.out.print("Choisissez un restaurant (ID) : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= restaurants.size()) {
            System.out.println("❌ Restaurant invalide.");
            return;
        }

        System.out.print("Nom de l'employé à supprimer : ");
        String nomEmploye = scanner.nextLine();

        restaurants.get(index).supprimerEmploye(nomEmploye);
    }

    private static void ajouterPlat(Scanner scanner) {
        if (restaurants.isEmpty()) {
            System.out.println("❌ Aucun restaurant disponible.");
            return;
        }

        afficherRestaurants();
        System.out.print("Choisissez un restaurant (ID) : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= restaurants.size()) {
            System.out.println("❌ Restaurant invalide.");
            return;
        }

        System.out.print("Nom du plat : ");
        String nomPlat = scanner.nextLine();
        System.out.print("Prix du plat : ");
        double prix = scanner.nextDouble();
        scanner.nextLine();

        Plat plat = new Plat(nomPlat, prix);
        restaurants.get(index).getMenu().ajouterPlat(plat);
        System.out.println("✅ Plat ajouté au menu !");
    }

    private static void prendreCommande(Scanner scanner) {
        if (restaurants.isEmpty()) {
            System.out.println("❌ Aucun restaurant disponible.");
            return;
        }

        afficherRestaurants();
        System.out.print("Choisissez un restaurant (ID) : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= restaurants.size()) {
            System.out.println("❌ Restaurant invalide.");
            return;
        }

        Restaurant resto = restaurants.get(index);
        resto.getMenu().afficherMenu();

        Commande commande = new Commande();
        while (true) {
            System.out.print("Ajoutez un plat au panier (nom du plat ou 'fin' pour terminer) : ");
            String nomPlat = scanner.nextLine();
            if (nomPlat.equalsIgnoreCase("fin")) break;

            Plat platChoisi = resto.getMenu().trouverPlat(nomPlat);
            if (platChoisi != null) {
                commande.ajouterPlat(platChoisi);
                System.out.println("✅ Plat ajouté à la commande !");
            } else {
                System.out.println("❌ Plat introuvable.");
            }
        }

        resto.ajouterCommande(commande);
        System.out.println("✅ Commande enregistrée !");
    }

    private static void afficherCommandes(Scanner scanner) {
        afficherRestaurants();
        System.out.print("Choisissez un restaurant (ID) : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= restaurants.size()) {
            System.out.println("❌ Restaurant invalide.");
            return;
        }

        restaurants.get(index).afficherCommandes();
    }

    private static void sauvegarderCommandes(Scanner scanner) {
        System.out.println("🔹 Fonction à implémenter.");
    }

    private static void chargerCommandes(Scanner scanner) {
        System.out.println("🔹 Fonction à implémenter.");
    }
}