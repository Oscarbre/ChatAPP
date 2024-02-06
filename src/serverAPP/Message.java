package serverAPP;

public class Message{

    String sender;
    String receiver;
    String data;

    public  Message(String sender, String receiver, String data) {
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
    }

    public String getSender() {
        return this.sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public String getData() {
        return this.data;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setData(String data) {
        this.data = data;
    }

    
}
