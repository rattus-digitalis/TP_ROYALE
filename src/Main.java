import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Restaurant> restaurants = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

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
            System.out.println("10. Quitter");
            System.out.print("Choix : ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrée invalide. Veuillez saisir un nombre.");
                scanner.next();
                continue;
            }
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
                case 10 -> {
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
        System.out.println("✅ Restaurant ajouté !");
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

    private static void ajouterEmploye(Scanner scanner) {
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
        Date dateEmbauche;
        try {
            dateEmbauche = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            System.out.println("❌ Format de date invalide, la date actuelle sera utilisée.");
            dateEmbauche = new Date();
        }

        Employe employe = new Employe(nom, prenom, role, salaire, dateEmbauche);
        restaurants.get(index).ajouterEmploye(employe);
        System.out.println("✅ Employé ajouté !");
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
        System.out.println("✅ Employé supprimé !");
    }

    private static void afficherEmployes(Scanner scanner) {
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
}