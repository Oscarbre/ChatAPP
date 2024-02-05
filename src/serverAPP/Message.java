package serverAPP;

public class Message{

    Client sender;
    Client receiver;
    String data;

    public  Message(Client sender, Client receiver, String data) {
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
    }

    public Client getSender() {
        return this.sender;
    }

    public Client getReceiver() {
        return this.receiver;
    }

    public String getData() {
        return this.data;
    }
}
