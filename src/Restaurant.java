import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String nom;
    private String adresse;
    private String typeCuisine;
    private Menu menu;
    private List<Employe> employes;

    // 🔹 Constructeur
    public Restaurant(String nom, String adresse, String typeCuisine) {
        this.nom = nom;
        this.adresse = adresse;
        this.typeCuisine = typeCuisine;
        this.menu = new Menu();
        this.employes = new ArrayList<>();
    }

    // 🔹 Getters
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getTypeCuisine() { return typeCuisine; }
    public Menu getMenu() { return menu; }
    public List<Employe> getEmployes() { return employes; }

    // ✅ Affiche les détails du restaurant
    public void afficherRestaurant() {
        System.out.println("\n📌 Restaurant : " + nom);
        System.out.println("📍 Adresse    : " + adresse);
        System.out.println("🍽 Cuisine   : " + typeCuisine);
    }

    // ✅ Sauvegarde le restaurant dans un fichier
    public void sauvegarderRestaurant() {
        try {
            File file = new File("data/restaurants.txt");
            file.getParentFile().mkdirs(); // Création du dossier si inexistant

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(nom + ";" + adresse + ";" + typeCuisine + "\n");
                System.out.println("✅ Restaurant sauvegardé avec succès !");
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la sauvegarde du restaurant : " + e.getMessage());
        }
    }

    // ✅ Ajoute un employé à la liste et sauvegarde dans employes.txt
    public void ajouterEmploye(Employe employe) {
        employes.add(employe);
        sauvegarderEmploye(employe);
        System.out.println("✅ Employé ajouté avec succès !");
    }

    // ✅ Sauvegarde un employé dans employes.txt
    private void sauvegarderEmploye(Employe employe) {
        try {
            File file = new File("data/employes.txt");
            file.getParentFile().mkdirs(); // Création du dossier si inexistant

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(nom + ";" + employe.getNom() + ";" + employe.getPrenom() + ";" + employe.getRole() + ";" + employe.getSalaire() + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la sauvegarde de l'employé : " + e.getMessage());
        }
    }

    // ✅ Supprime un employé de la liste et du fichier employes.txt
    public void supprimerEmploye(String nomEmploye) {
        boolean removed = employes.removeIf(emp -> emp.getNom().equalsIgnoreCase(nomEmploye));
        if (removed) {
            mettreAJourFichierEmployes();
            System.out.println("✅ Employé " + nomEmploye + " supprimé avec succès !");
        } else {
            System.out.println("⚠ Employé non trouvé !");
        }
    }

    // ✅ Met à jour le fichier employes.txt après suppression
    private void mettreAJourFichierEmployes() {
        try {
            File file = new File("data/employes.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Employe emp : employes) {
                    writer.write(nom + ";" + emp.getNom() + ";" + emp.getPrenom() + ";" + emp.getRole() + ";" + emp.getSalaire() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la mise à jour du fichier des employés : " + e.getMessage());
        }
    }

    // ✅ Charge les employés depuis employes.txt au démarrage
    public void chargerEmployes() {
        File file = new File("data/employes.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] infos = ligne.split(";");
                if (infos.length == 5 && infos[0].equalsIgnoreCase(nom)) {
                    Employe emp = new Employe(infos[1], infos[2], infos[3], Double.parseDouble(infos[4]));
                    employes.add(emp);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("❌ Erreur lors du chargement des employés : " + e.getMessage());
        }
    }

    // ✅ Affiche la liste des employés du restaurant
    public void afficherEmployes() {
        if (employes.isEmpty()) {
            System.out.println("⚠ Aucun employé dans ce restaurant.");
            return;
        }
        System.out.println("📋 Employés de " + nom + " :");
        for (Employe employe : employes) {
            System.out.println("- " + employe.getNom() + " " + employe.getPrenom() + " (" + employe.getRole() + ")");
        }
    }
}
