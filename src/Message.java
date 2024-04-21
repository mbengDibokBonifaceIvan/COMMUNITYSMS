public class Message {
    private static int nextId = 1;
    private String id;
    private String numeroDestinataire;
    private String contenu;
    public Message(String numeroDestinataire, String contenu, Statut statut) {
        this.id = generateUniqueId();
        this.numeroDestinataire = numeroDestinataire;
        this.contenu = contenu;
    }

    private String generateUniqueId() {
        String uniqueId = "MSG" + nextId;
        nextId++;
        return uniqueId;
    }

    public String getId() {
        return id;
    }

    public String getNumeroDestinataire() {
        return numeroDestinataire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String nouveauContenu) {
        this.contenu = nouveauContenu;
    }
}

