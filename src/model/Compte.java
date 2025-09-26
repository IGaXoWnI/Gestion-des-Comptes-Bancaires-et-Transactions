package model;

import enums.TypeCompte;
import java.util.ArrayList;

public class Compte {
    private String idCompte;
    private TypeCompte typeCompte;
    private double solde;
    private ArrayList<Transaction> transactions;
    private Client proprietaire;
    
    public Compte(String idCompte, TypeCompte typeCompte, double soldeInitial, Client proprietaire) {
        this.idCompte = idCompte;
        this.typeCompte = typeCompte;
        this.solde = soldeInitial;
        this.proprietaire = proprietaire;
        this.transactions = new ArrayList<>();
    }
    
    public String getIdCompte() {
        return idCompte;
    }
    
    public TypeCompte getTypeCompte() {
        return typeCompte;
    }
    
    public double getSolde() {
        return solde;
    }
    
    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
    
    public Client getProprietaire() {
        return proprietaire;
    }
    
    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }
    
    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }
    
    protected void setSolde(double solde) {
        this.solde = solde;
    }
    
    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }
    
    protected void ajouterTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
    
    public void deposer(double montant, String motif) {
        System.out.println("STUB: Méthode deposer() - À implémenter");
    }
    
    public void retirer(double montant, String motif) {
        System.out.println("STUB: Méthode retirer() - À implémenter");
    }
    
    public void virer(double montant, Compte compteDestination, String motif) {
        System.out.println("STUB: Méthode virer() - À implémenter");
    }
    
    public int getNombreTransactions() {
        return transactions.size();
    }
    
    @Override
    public String toString() {
        return String.format("Compte ID: %s | Type: %s | Solde: %.2f € | Propriétaire: %s | Transactions: %d",
                idCompte, typeCompte, solde, 
                proprietaire != null ? proprietaire.getNomComplet() : "Aucun",
                transactions.size());
    }
}