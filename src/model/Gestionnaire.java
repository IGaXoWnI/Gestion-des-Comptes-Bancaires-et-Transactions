package model;

import java.util.ArrayList;

public class Gestionnaire extends Person {
    private String idGestionnaire;
    private String departement;
    private ArrayList<Client> clients;
    
    public Gestionnaire(String idGestionnaire, String nom, String prenom, String email, String motDePasse, String departement) {
        super(nom, prenom, email, motDePasse);
        this.idGestionnaire = idGestionnaire;
        this.departement = departement;
        this.clients = new ArrayList<>();
    }
    
    public String getIdGestionnaire() {
        return idGestionnaire;
    }
    
    public String getDepartement() {
        return departement;
    }
    
    public ArrayList<Client> getClients() {
        return new ArrayList<>(clients);
    }
    
    public void setIdGestionnaire(String idGestionnaire) {
        this.idGestionnaire = idGestionnaire;
    }
    
    public void setDepartement(String departement) {
        this.departement = departement;
    }
    
    @Override
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    public void ajouterClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Le client ne peut pas être null");
        }
        
        boolean clientExiste = clients.stream()
                                    .anyMatch(c -> c.getIdClient().equals(client.getIdClient()));
        
        if (clientExiste) {
            throw new IllegalStateException("Un client avec cet ID existe déjà: " + client.getIdClient());
        }
        
        this.clients.add(client);
    }
    
    public boolean supprimerClient(String idClient) {
        return clients.removeIf(client -> client.getIdClient().equals(idClient));
    }
    
    public Client rechercherClient(String idClient) {
        return clients.stream()
                     .filter(client -> client.getIdClient().equals(idClient))
                     .findFirst()
                     .orElseThrow(() -> new java.util.NoSuchElementException("Client introuvable: " + idClient));
    }
    
    public Client rechercherClientParEmail(String email) {
        return clients.stream()
                     .filter(client -> client.getEmail().equals(email))
                     .findFirst()
                     .orElseThrow(() -> new java.util.NoSuchElementException("Client introuvable avec email: " + email));
    }
    
    public int getNombreClients() {
        return clients.size();
    }
    
    public boolean aDesClients() {
        return !clients.isEmpty();
    }
    
    public ArrayList<Client> getClientsTriesParNom() {
        return clients.stream()
                     .sorted((c1, c2) -> c1.getNomComplet().compareToIgnoreCase(c2.getNomComplet()))
                     .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }
    
    public double calculerTotalFondsGeres() {
        return clients.stream()
                     .mapToDouble(Client::calculerSoldeTotal)
                     .sum();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Gestionnaire ID: %s%n", idGestionnaire));
        sb.append(String.format("Nom complet: %s%n", getNomComplet()));
        sb.append(String.format("Email: %s%n", email));
        sb.append(String.format("Département: %s%n", departement));
        sb.append(String.format("Nombre de clients gérés: %d%n", clients.size()));
        
        if (!clients.isEmpty()) {
            sb.append("Clients gérés:%n");
            for (Client client : clients) {
                sb.append(String.format("  - %s (ID: %s)%n", client.getNomComplet(), client.getIdClient()));
            }
        }
        
        return sb.toString();
    }
}