package model;

import java.util.ArrayList;

public class Client extends Person {
    private String idClient;
    private ArrayList<Compte> comptes;
    
    public Client(String idClient, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idClient = idClient;
        this.comptes = new ArrayList<>();
    }
    
    public String getIdClient() {
        return idClient;
    }
    
    public ArrayList<Compte> getComptes() {
        return new ArrayList<>(comptes);
    }
    
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    
    @Override
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    public void ajouterCompte(Compte compte) {
        this.comptes.add(compte);
    }
    
    public boolean supprimerCompte(String idCompte) {
        System.out.println("STUB: Méthode supprimerCompte() - À implémenter");
        return false;
    }
    
    public Compte rechercherCompte(String idCompte) {
        for (Compte compte : comptes) {
            if (compte.getIdCompte().equals(idCompte)) {
                return compte;
            }
        }
        return null;
    }
    
    public double calculerSoldeTotal() {
        return comptes.stream()
                     .mapToDouble(Compte::getSolde)
                     .sum();
    }
    
    public int getNombreComptes() {
        return comptes.size();
    }
    
    public boolean aDesComptes() {
        return !comptes.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Client ID: %s%n", idClient));
        sb.append(String.format("Nom complet: %s%n", getNomComplet()));
        sb.append(String.format("Email: %s%n", email));
        sb.append(String.format("Nombre de comptes: %d%n", comptes.size()));
        
        if (!comptes.isEmpty()) {
            sb.append("Comptes:%n");
            for (Compte compte : comptes) {
                sb.append(String.format("  - %s%n", compte.toString()));
            }
        }
        
        return sb.toString();
    }
}