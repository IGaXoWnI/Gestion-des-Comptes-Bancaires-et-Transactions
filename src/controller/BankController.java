package controller;

import model.*;
import enums.*;
import view.ConsoleView;
import java.util.ArrayList;

public class BankController {
    private ConsoleView view;
    private ArrayList<Client> clients;
    private ArrayList<Gestionnaire> gestionnaires;
    private Gestionnaire gestionnaireActuel;
    private Client clientActuel;
    
    public BankController() {
        this.view = new ConsoleView();
        this.clients = new ArrayList<>();
        this.gestionnaires = new ArrayList<>();
        initialiserDonnees();
    }
    
    private void initialiserDonnees() {
        Gestionnaire gestionnaire = new Gestionnaire("G001", "Admin", "System", 
                                                    "admin@bank.ma", "admin123", "Direction");
        gestionnaires.add(gestionnaire);
        
        Client client = new Client("C001", "Alami", "Ahmed", "ahmed@email.com", "pass123");
        Compte compte = new Compte("CPT001", TypeCompte.COURANT, 5000.0, client);
        client.ajouterCompte(compte);
        
        clients.add(client);
        gestionnaire.ajouterClient(client);
    }
    
    public void demarrer() {
        boolean continuer = true;
        
        while (continuer) {
            view.afficherMenuPrincipal();
            int choix = view.lireChoix();
            
            switch (choix) {
                case 1:
                    espaceClient();
                    break;
                case 2:
                    espaceGestionnaire();
                    break;
                case 0:
                    continuer = false;
                    view.afficherMessage("Au revoir!");
                    break;
                default:
                    view.afficherErreur("Choix invalide");
            }
        }
        
        view.fermer();
    }
    
    private void espaceClient() {
        String email = view.lireTexte("Email: ");
        String motDePasse = view.lireTexte("Mot de passe: ");
        
        clientActuel = clients.stream()
                             .filter(c -> c.getEmail().equals(email) && c.getMotDePasse().equals(motDePasse))
                             .findFirst()
                             .orElse(null);
        
        if (clientActuel == null) {
            view.afficherErreur("Email ou mot de passe incorrect");
            return;
        }
        
        boolean continuer = true;
        while (continuer) {
            view.afficherMenuClient();
            int choix = view.lireChoix();
            
            switch (choix) {
                case 1:
                    consulterComptes();
                    break;
                case 2:
                    voirHistorique();
                    break;
                case 3:
                    calculerSoldeTotal();
                    break;
                case 0:
                    continuer = false;
                    break;
                default:
                    view.afficherErreur("Choix invalide");
            }
        }
    }
    
    private void espaceGestionnaire() {
        String email = view.lireTexte("Email gestionnaire: ");
        String motDePasse = view.lireTexte("Mot de passe: ");
        
        gestionnaireActuel = gestionnaires.stream()
                                        .filter(g -> g.getEmail().equals(email) && g.getMotDePasse().equals(motDePasse))
                                        .findFirst()
                                        .orElse(null);
        
        if (gestionnaireActuel == null) {
            view.afficherErreur("Email ou mot de passe incorrect");
            return;
        }
        
        boolean continuer = true;
        while (continuer) {
            view.afficherMenuGestionnaire();
            int choix = view.lireChoix();
            
            switch (choix) {
                case 1:
                    ajouterClient();
                    break;
                case 2:
                    creerCompte();
                    break;
                case 3:
                    effectuerTransaction();
                    break;
                case 4:
                    consulterClient();
                    break;
                case 5:
                    listerClients();
                    break;
                case 0:
                    continuer = false;
                    break;
                default:
                    view.afficherErreur("Choix invalide");
            }
        }
    }
    
    private void consulterComptes() {
        view.afficherMessage("\n=== MES COMPTES ===");
        for (Compte compte : clientActuel.getComptes()) {
            view.afficherMessage(compte.toString());
        }
        view.pauseConsole();
    }
    
    private void voirHistorique() {
        view.afficherMessage("\n=== HISTORIQUE DES TRANSACTIONS ===");
        for (Compte compte : clientActuel.getComptes()) {
            view.afficherMessage("Compte: " + compte.getIdCompte());
            for (Transaction transaction : compte.getTransactions()) {
                view.afficherMessage("  " + transaction.toString());
            }
        }
        view.pauseConsole();
    }
    
    private void calculerSoldeTotal() {
        double soldeTotal = clientActuel.calculerSoldeTotal();
        view.afficherSuccess("Solde total: " + soldeTotal + " MAD");
        view.pauseConsole();
    }
    
    private void ajouterClient() {
        try {
            String id = view.lireTexte("ID Client: ");
            String nom = view.lireTexte("Nom: ");
            String prenom = view.lireTexte("Prénom: ");
            String email = view.lireTexte("Email: ");
            String motDePasse = view.lireTexte("Mot de passe: ");
            
            Client client = new Client(id, nom, prenom, email, motDePasse);
            clients.add(client);
            gestionnaireActuel.ajouterClient(client);
            
            view.afficherSuccess("Client ajouté avec succès");
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
        view.pauseConsole();
    }
    
    private void creerCompte() {
        try {
            String idClient = view.lireTexte("ID Client: ");
            Client client = gestionnaireActuel.rechercherClient(idClient);
            
            String idCompte = view.lireTexte("ID Compte: ");
            view.afficherMessage("Types de compte: 1-COURANT, 2-EPARGNE, 3-DEPOTATERME");
            int typeChoix = view.lireChoix();
            
            TypeCompte type = TypeCompte.COURANT;
            switch (typeChoix) {
                case 2: type = TypeCompte.EPARGNE; break;
                case 3: type = TypeCompte.DEPOTATERME; break;
            }
            
            double soldeInitial = view.lireMontant("Solde initial: ");
            
            Compte compte = new Compte(idCompte, type, soldeInitial, client);
            client.ajouterCompte(compte);
            
            view.afficherSuccess("Compte créé avec succès");
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
        view.pauseConsole();
    }
    
    private void effectuerTransaction() {
        try {
            String idClient = view.lireTexte("ID Client: ");
            Client client = gestionnaireActuel.rechercherClient(idClient);
            
            String idCompte = view.lireTexte("ID Compte: ");
            Compte compte = client.rechercherCompte(idCompte);
            
            view.afficherMenuTransactions();
            int choix = view.lireChoix();
            
            double montant = view.lireMontant("Montant: ");
            String motif = view.lireTexte("Motif: ");
            
            switch (choix) {
                case 1:
                    compte.deposer(montant, motif);
                    view.afficherSuccess("Dépôt effectué");
                    break;
                case 2:
                    compte.retirer(montant, motif);
                    view.afficherSuccess("Retrait effectué");
                    break;
                case 3:
                    String idCompteDestination = view.lireTexte("ID Compte destination: ");
                    String idClientDestination = view.lireTexte("ID Client destination: ");
                    Client clientDestination = gestionnaireActuel.rechercherClient(idClientDestination);
                    Compte compteDestination = clientDestination.rechercherCompte(idCompteDestination);
                    
                    compte.virer(montant, compteDestination, motif);
                    view.afficherSuccess("Virement effectué");
                    break;
            }
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
        view.pauseConsole();
    }
    
    private void consulterClient() {
        try {
            String idClient = view.lireTexte("ID Client: ");
            Client client = gestionnaireActuel.rechercherClient(idClient);
            view.afficherMessage(client.toString());
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
        view.pauseConsole();
    }
    
    private void listerClients() {
        view.afficherMessage("\n=== LISTE DES CLIENTS ===");
        for (Client client : gestionnaireActuel.getClientsTriesParNom()) {
            view.afficherMessage(client.getNomComplet() + " (ID: " + client.getIdClient() + ")");
        }
        view.pauseConsole();
    }
}