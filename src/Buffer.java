import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private List<Message> messages;

    public Buffer() {
        this.messages = new ArrayList<>();
    }

 
    public void ajouterMessage(Message message) {
        messages.add(message);
        System.out.println("Message ajouté à la file d'attente.");
    }

    public List<Message> getMessages() {
        return messages;
    }


    public Message getMessageEnvoyeById(String idMessage) {
        for (Message message : messages) {
            if (message.getId().equals(idMessage)) {
                return message;
            }
        }
        return null;
    }

    public void setContenuMessageEnvoye(String idMessage, String nouveauContenu) {
        Message message = getMessageEnvoyeById(idMessage);
        if (message != null) {
            message.setContenu(nouveauContenu);
        }
    }

    public void supprimerMessageNonEnvoye(String idMessage) {
        Message message = getMessageEnvoyeById(idMessage);
        if (message != null) {
            messages.remove(message);
        }
    }

    

    
}