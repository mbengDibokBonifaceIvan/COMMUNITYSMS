public class Authentification {
   
    private String MOT_DE_PASSE = "ivan1234";

    public Authentification(String motDePasse) {
        this.MOT_DE_PASSE = motDePasse;
    }

    public boolean verifierMotDePasse(String entreeMotDePasse) {
        return entreeMotDePasse.equals(MOT_DE_PASSE);
    }
}