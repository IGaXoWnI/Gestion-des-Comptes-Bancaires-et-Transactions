package model;

import enums.TypeTransaction;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String idTransaction;
    private TypeTransaction typeTransaction;
    private double montant;
    private LocalDateTime dateTransaction;
    private String motif;
    private Compte compteSource;
    private Compte compteDestination;
    
    public Transaction(String idTransaction, double montant, String motif, Compte compteDestination) {
        this.idTransaction = idTransaction;
        this.typeTransaction = TypeTransaction.DEPOT;
        this.montant = montant;
        this.dateTransaction = LocalDateTime.now();
        this.motif = motif;
        this.compteSource = null;
        this.compteDestination = compteDestination;
    }
    
    public Transaction(String idTransaction, double montant, String motif, Compte compteSource, boolean isRetrait) {
        this.idTransaction = idTransaction;
        this.typeTransaction = TypeTransaction.RETRAIT;
        this.montant = montant;
        this.dateTransaction = LocalDateTime.now();
        this.motif = motif;
        this.compteSource = compteSource;
        this.compteDestination = null;
    }
    
    public Transaction(String idTransaction, double montant, String motif, Compte compteSource, Compte compteDestination) {
        this.idTransaction = idTransaction;
        this.typeTransaction = TypeTransaction.VIREMENT;
        this.montant = montant;
        this.dateTransaction = LocalDateTime.now();
        this.motif = motif;
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }
    
    public String getIdTransaction() {
        return idTransaction;
    }
    
    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }
    
    public double getMontant() {
        return montant;
    }
    
    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }
    
    public String getMotif() {
        return motif;
    }
    
    public Compte getCompteSource() {
        return compteSource;
    }
    
    public Compte getCompteDestination() {
        return compteDestination;
    }
    
    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }
    
    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }
    
    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
    
    public void setMotif(String motif) {
        this.motif = motif;
    }
    
    public void setCompteSource(Compte compteSource) {
        this.compteSource = compteSource;
    }
    
    public void setCompteDestination(Compte compteDestination) {
        this.compteDestination = compteDestination;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("Transaction ID: %s%n", idTransaction));
        sb.append(String.format("Type: %s%n", typeTransaction));
        sb.append(String.format("Montant: %.2f €%n", montant));
        sb.append(String.format("Date: %s%n", dateTransaction.format(formatter)));
        sb.append(String.format("Motif: %s%n", motif));
        
        if (compteSource != null) {
            sb.append(String.format("Compte source: %s%n", compteSource.getIdCompte()));
        }
        if (compteDestination != null) {
            sb.append(String.format("Compte destination: %s%n", compteDestination.getIdCompte()));
        }
        
        return sb.toString();
    }
}