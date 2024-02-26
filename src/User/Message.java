package User;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message{

    @JsonProperty("sender")     // Permet d'indiquer les propriétés JSON à désérialiser
    String sender;

    @JsonProperty("receiver")
    String receiver;

    @JsonProperty("data")
    String data;

    @JsonProperty("time")
    String time;

    public  Message(String sender, String receiver, String data) {
        this.sender = sender;
        this.receiver = receiver;
        this.data = data;

        LocalTime tempsActuel = LocalTime.now();
        this.time = "[" + String.valueOf(tempsActuel.getHour()) + ":" + String.valueOf(tempsActuel.getMinute()) + "]";
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

    public String getTime() {
        return time;
    }

}
