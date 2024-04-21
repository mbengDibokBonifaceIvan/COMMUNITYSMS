import java.util.ArrayList;
import java.util.List;

public class Groupe {
    private String intitule;
    private List<Contact> contacts;

    public Groupe(String intitule) {
        this.intitule = intitule;
        this.contacts = new ArrayList<>();
    }

    public String getIntitule() {
        return intitule;
    }

    public void ajouterContact(Contact contact) {
        if (!verifierSiContactExiste(contact)) {
            contacts.add(contact);
            System.out.println("Le contact a été ajouté au groupe avec succès.");
        } else {
            System.out.println("Le contact existe déjà dans le groupe.");
        }
    }

    public void supprimerContact(Contact contact) {
        contacts.remove(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public boolean verifierSiContactExiste(Contact contact) {
        for (Contact existingContact : contacts) {
            if (existingContact.getNumeroTelephone().equals(contact.getNumeroTelephone())) {
                return true;
            }
        }
        return false;
    }
}