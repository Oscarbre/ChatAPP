package serverAPP;

public class Message{

    Client sender;
    Client receiver;
    String data;

    public void  Message(Client sender, Client receiver, String data) {
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
    }

    public Client getSender(Message message) {
        return message.sender;
    }

    public Client getReceiver(Message message) {
        return message.receiver;
    }

    public String getText(Message message) {
        return message.data;
    }
}
