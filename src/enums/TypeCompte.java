package enums;

public enum TypeCompte {
    COURANT("Compte Courant"),
    EPARGNE("Compte Épargne"),
    DEPOTATERME("Dépôt à Terme");
    
    private final String libelle;
    
    TypeCompte(String libelle) {
        this.libelle = libelle;
    }
    
    public String getLibelle() {
        return libelle;
    }
    
    @Override
    public String toString() {
        return libelle;
    }
}