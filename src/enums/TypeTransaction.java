package enums;

public enum TypeTransaction {
    DEPOT("Dépôt"),
    RETRAIT("Retrait"),
    VIREMENT("Virement");
    
    private final String libelle;
    
    TypeTransaction(String libelle) {
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