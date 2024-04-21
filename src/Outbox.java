import java.util.ArrayList;
import java.util.List;

public class Outbox {
    private List<Message> messagesEnvoyes;

    public Outbox() {
        this.messagesEnvoyes = new ArrayList<>();
    }

    public void ajouterMessageEnvoye(Message message) {
        messagesEnvoyes.add(message);
        System.out.println("Message ajouté à la boite d'envoi.");
    }

    public List<Message> getMessagesEnvoyes() {
        return messagesEnvoyes;
    }
}

