package model;

public abstract class Person {
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;
    
    public Person(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    @Override
    public String toString() {
        return String.format("Nom: %s, Prénom: %s, Email: %s", nom, prenom, email);
    }
    
    public abstract String getNomComplet();
}