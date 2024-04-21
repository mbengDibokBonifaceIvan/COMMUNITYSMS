public class Contact {
    private String nom;
    private String numeroTelephone;
    private String operateurTelecom;

    public Contact(String nom, String numeroTelephone, String operateurTelecom) {
        this.nom = nom;
        this.numeroTelephone = numeroTelephone;
        this.operateurTelecom = operateurTelecom;
    }

    public String getNom() {
        return nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getOperateurTelecom() {
        return operateurTelecom;
    }
}