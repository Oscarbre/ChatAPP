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
        String heure = (tempsActuel.getHour() < 10) ? "0" + String.valueOf(tempsActuel.getHour()) : String.valueOf(tempsActuel.getHour());
        String minute = (tempsActuel.getMinute() < 10) ? "0" + String.valueOf(tempsActuel.getMinute()) : String.valueOf(tempsActuel.getMinute());
        this.time = "[" + heure + ":" + minute + "]";
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
