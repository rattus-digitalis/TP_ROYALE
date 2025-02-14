import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestionRestaurants {
    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Ajouter un restaurant");
            System.out.println("2. Ajouter un employé");
            System.out.println("3. Ajouter une commande");
            System.out.println("4. Afficher les restaurants");
            System.out.println("5. Quitter");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom du restaurant : ");
                    String nomResto = scanner.nextLine();
                    System.out.print("Adresse : ");
                    String adresse = scanner.nextLine();
                    System.out.print("Type de cuisine : ");
                    String typeCuisine = scanner.nextLine();

                    Restaurant resto = new Restaurant(nomResto, adresse, typeCuisine);
                    resto.sauvegarderRestaurant();
                    break;

                case 2:
                    System.out.print("Nom de l'employé : ");
                    String nomEmploye = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenomEmploye = scanner.nextLine();
                    System.out.print("Rôle : ");
                    String role = scanner.nextLine();
                    System.out.print("Salaire : ");
                    double salaire = scanner.nextDouble();
                    scanner.nextLine();

                    Employe employe = new Employe(nomEmploye, prenomEmploye, role, salaire);
                    employe.sauvegarderEmploye();
                    break;

                case 3:
                    Commande commande = new Commande();
                    while (true) {
                        System.out.print("Nom du plat (ou 'fin' pour terminer) : ");
                        String nomPlat = scanner.nextLine();
                        if (nomPlat.equalsIgnoreCase("fin")) break;
                        System.out.print("Prix du plat : ");
                        double prixPlat = scanner.nextDouble();
                        scanner.nextLine();
                        commande.ajouterPlat(new Plat(nomPlat, prixPlat));
                    }
                    commande.sauvegarderCommande();
                    break;

                case 4:
                    afficherRestaurants();
                    break;

                case 5:
                    scanner.close();
                    System.out.println("Programme terminé !");
                    return;

                default:
                    System.out.println("Option invalide.");
            }
        }
    }

    public static void afficherRestaurants() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/restaurants.txt"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] infos = ligne.split(";");
                System.out.println("Nom : " + infos[0] + ", Adresse : " + infos[1] + ", Cuisine : " + infos[2]);
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }
    }
}
