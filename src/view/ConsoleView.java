package view;

import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;
    
    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }
    
    public void afficherMenuPrincipal() {
        System.out.println("\n=== SYSTÈME BANCAIRE ===");
        System.out.println("1. Espace Client");
        System.out.println("2. Espace Gestionnaire");
        System.out.println("0. Quitter");
        System.out.print("Votre choix: ");
    }
    
    public void afficherMenuClient() {
        System.out.println("\n=== ESPACE CLIENT ===");
        System.out.println("1. Consulter mes comptes");
        System.out.println("2. Voir historique des transactions");
        System.out.println("3. Calculer solde total");
        System.out.println("0. Retour");
        System.out.print("Votre choix: ");
    }
    
    public void afficherMenuGestionnaire() {
        System.out.println("\n=== ESPACE GESTIONNAIRE ===");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Créer un compte");
        System.out.println("3. Effectuer une transaction");
        System.out.println("4. Consulter un client");
        System.out.println("5. Liste des clients");
        System.out.println("0. Retour");
        System.out.print("Votre choix: ");
    }
    
    public void afficherMenuTransactions() {
        System.out.println("\n=== TRANSACTIONS ===");
        System.out.println("1. Dépôt");
        System.out.println("2. Retrait");
        System.out.println("3. Virement");
        System.out.println("0. Retour");
        System.out.print("Votre choix: ");
    }
    
    public int lireChoix() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public String lireTexte(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    
    public double lireMontant(String message) {
        System.out.print(message);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Montant invalide");
        }
    }
    
    public void afficherMessage(String message) {
        System.out.println(message);
    }
    
    public void afficherErreur(String erreur) {
        System.err.println("ERREUR: " + erreur);
    }
    
    public void afficherSuccess(String message) {
        System.out.println("✓ " + message);
    }
    
    public void pauseConsole() {
        System.out.println("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
    
    public void fermer() {
        scanner.close();
    }
}