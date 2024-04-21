import java.util.ArrayList;
import java.util.List;

public class Repertoire {
    private List<Contact> contacts;
    private List<Groupe> groupes;

    public Repertoire() {
        this.contacts = new ArrayList<>();
        this.groupes = new ArrayList<>();
    }

    public void ajouterContact(Contact contact) {
        if (!verifierSiContactExiste(contact)) {
            //Code de la BD pour ajouter contact ici
            contacts.add(contact);
            System.out.println("Le contact a été ajouté avec succès.");
          //  System.out.println("Contact "+contact.getNom()+" ajouté avec succès.");
        } else {
            System.out.println("Le contact existe déjà, echec de l'operation !");     
        }
    }

    public void supprimerContact(Contact contact) {
        if (verifierSiContactExiste(contact)) {

            //Code de la BD pour supprimer contact ici
            System.out.println("Contact "+contact.getNom()+" supprime avec succès.");
            contacts.remove(contact);
        } else {
            System.out.println("Le contact n'existe pas, echec de l'operation !");
        }
    }

    public void creerGroupe(Groupe groupe) {
        if (!verifierSiGroupeExiste(groupe)) {
            //Code de la BD pour ajouter GROUPE ici

            groupes.add(groupe);
            System.out.println("Le groupe a été créé avec succès.");
        } else {
            System.out.println("Le groupe existe déjà.");
        }
    }

    public void supprimerGroupe(Groupe groupe) {
        if (verifierSiGroupeExiste(groupe)) {
            //Code de la BD pour supprimer contact ici
            
            System.out.println("Groupe supprimé avec succès.");
             groupes.remove(groupe);
        } else {
            System.out.println("Le groupe n'existe pas.");
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public boolean verifierSiContactExiste(Contact contact) {
        for (Contact existingContact : contacts) {
            if (existingContact.getNumeroTelephone().equals(contact.getNumeroTelephone())) {
                return true;
            }
        }
        return false;
    }

    public boolean verifierSiGroupeExiste(Groupe groupe) {
        for (Groupe existingGroupe : groupes) {
            if (existingGroupe.getIntitule().equals(groupe.getIntitule())) {
                return true;
            }
        }
        return false;
    }
}