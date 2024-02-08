package serverAPP;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message{

    @JsonProperty("sender")     // Permet d'indiquer les propriétés JSON à désérialiser
    String sender;

    @JsonProperty("receiver")
    String receiver;

    @JsonProperty("data")
    String data;

    public  Message(String sender, String receiver, String data) {
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;
    }

    public Message() {}     // Constructeur pour la désérialisation JSON

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
