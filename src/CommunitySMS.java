import java.util.List;
import java.util.Scanner;

public class CommunitySMS {
// private static final String MOT_DE_PASSE = "ivan1234";
private Repertoire repertoire;
private Buffer buffer;
private Outbox outbox;
private Authentification auth;

public CommunitySMS() {
    this.repertoire = new Repertoire();
    this.buffer = new Buffer();
    this.outbox = new Outbox();
    this.auth = new Authentification("ivan1234"); // Mot de passe pour l'authentification

}

@SuppressWarnings("resource") //Ce qui derange depuis tsuip
public void demarrer() {
    Scanner scanner = new Scanner(System.in);

    // Authentification
    System.out.print("Veuillez entrer le mot de passe : ");
    String motDePasseEntre = scanner.nextLine();
    boolean estValide = auth.verifierMotDePasse(motDePasseEntre);

    if (!estValide) {
        System.out.println("Mot de passe incorrect. Fermeture de l'application.");
        return;
    }

    System.out.println("Authentification réussie.");

    // Menu principal
    boolean quitter = false;

    while (!quitter) {
        afficherMenuPrincipal();
        int choix = scanner.nextInt();
        scanner.nextLine(); // Vider la ligne

        switch (choix) {
            case 1:
                // Ajouter un contact
                ajouterContact(scanner);
                break;
            case 2:
                // Supprimer un contact
                supprimerContact(scanner);
                break;
            case 3:
                // Créer un groupe
                creerGroupe(scanner);
                break;
            case 4:
                // Supprimer un groupe
                supprimerGroupe(scanner);
                break;
            case 5:
                // Editer un message
                editerMessage(scanner);
                break;
            case 6:
                // Envoyer un message
                envoyerMessage(scanner);
                break;
               
            case 7:
                // Afficher les messages envoyés
                afficherMessagesEnvoyes();
                break;
              
            case 8:
                 // Afficher les messages en attente
                 afficherMessagesEnAttente();
                 break;
                
            case 9:
                // Quitter l'application
                quitter = true;
                System.out.println("Fermeture de l'application.");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
    }

    scanner.close();
}
private void afficherMenuPrincipal() {
    System.out.println("\n----- CommunitySMS Application -----");
    System.out.println("1. Ajouter un contact");
    System.out.println("2. Supprimer un contact");
    System.out.println("3. Créer un groupe");
    System.out.println("4. Supprimer un groupe");
    System.out.println("5. Editer un message");
    System.out.println("6. Envoyer un message");
    System.out.println("7. Afficher les messages envoyés");
    System.out.println("8. Afficher les messages en attente");
    System.out.println("9. Quitter");
    System.out.print("Choix : ");
}

private void ajouterContact(Scanner scanner) {
    System.out.print("Nom du contact : ");
    String nom = scanner.nextLine();
    System.out.print("Numéro de téléphone : ");
    String numeroTelephone = scanner.nextLine();
    System.out.print("Opérateur de télécommunication : ");
    String operateurTelecom = scanner.nextLine();

    Contact contact = new Contact(nom, numeroTelephone, operateurTelecom);
    repertoire.ajouterContact(contact);
   }

private void supprimerContact(Scanner scanner) {
    System.out.print("Numero du contact à supprimer : ");
    String numero = scanner.nextLine();

    for (Contact contact : repertoire.getContacts()) {
        if (contact.getNumeroTelephone().equalsIgnoreCase(numero)) {
            repertoire.supprimerContact(contact);
            return;
        }
    }

    System.out.println("Contact non trouvé.");
}

private void creerGroupe(Scanner scanner) {
    System.out.print("Intitulé du groupe : ");
    String intitule = scanner.nextLine();

    Groupe groupe = new Groupe(intitule);
    repertoire.creerGroupe(groupe);

//    System.out.println("Groupe créé avec succès.");
}

private void supprimerGroupe(Scanner scanner) {
    System.out.print("Intitulé du groupe à supprimer : ");
    String intitule = scanner.nextLine();

    for (Groupe groupe : repertoire.getGroupes()) {
        if (groupe.getIntitule().equalsIgnoreCase(intitule)) {
            repertoire.supprimerGroupe(groupe);
            return;
        }
    }
    System.out.println("Groupe non trouvé.");
}


private void editerMessage(Scanner scanner) {
    List<Message> messages = outbox.getMessagesEnvoyes();
    if (messages == null || messages.isEmpty()) {
        System.out.println("Aucun message trouvé.");
        return;
    }

    // Afficher la liste des messages avec leurs indices
    System.out.println("Liste des messages :");
    for (int i = 0; i < messages.size(); i++) {
        Message message = messages.get(i);
        System.out.println(i + ".{ \n Message: " + message.getContenu() + "\nDestinataire: " + message.getNumeroDestinataire() + " \n }");
    }
    // Demander à l'utilisateur de choisir l'indice du message à éditer
    System.out.print("Indice du message à éditer : ");
    int choix = scanner.nextInt();
    scanner.nextLine(); // Vider la ligne
    // Vérifier si l'indice est valide
    if (choix < 0 || choix >= messages.size()) {
        System.out.println("Indice invalide.");
        return;
    }
    // Récupérer le message choisi
    Message messageChoisi = messages.get(choix);
    // Demander le nouveau contenu du message
    System.out.print("Nouveau contenu du message : ");
    String nouveauContenu = scanner.nextLine();
    // Mettre à jour le contenu du message choisi
    messageChoisi.setContenu(nouveauContenu);
    // Enregistrer les modifications
    buffer.ajouterMessage(messageChoisi);
   
}
private void envoyerMessage(Scanner scanner) {
    System.out.print("Numéro de téléphone du destinataire : ");
    String numeroDestinataire = scanner.nextLine();
    System.out.print("Contenu du message : ");
    String contenuMessage = scanner.nextLine();
    System.out.println("Statut du message : "+Statut.ENVOYE);
   
    Message message = new Message(numeroDestinataire, contenuMessage, Statut.ENVOYE);
    outbox.ajouterMessageEnvoye(message);

}



private void afficherMessagesEnvoyes() {
    System.out.println("----- Messages envoyés -----");
    for (Message message : outbox.getMessagesEnvoyes()) {
        System.out.println("Destinataire: " + message.getNumeroDestinataire());
        System.out.println("Contenu: " + message.getContenu());
        System.out.println("------------------------------");
    }
}

private void afficherMessagesEnAttente() {
    System.out.println("----- Messages en attente -----");
    for (Message message : buffer.getMessages()) {
        System.out.println("Destinataire: " + message.getNumeroDestinataire());
        System.out.println("Contenu: " + message.getContenu());
        System.out.println("------------------------------");
    }
}

public static void main(String[] args) {
    CommunitySMS communitySMS = new CommunitySMS();
    communitySMS.demarrer();
}
}