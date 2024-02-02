package serverAPP;

public class Message(){

    Client emetteur;
    Client destinataire;
    String data;

    public void  Message(Client emetteur, Client destinataire, String texte) {
        this.emetteur = emetteur;
        this.destinataire = destinataire;
        this.texte = texte;
    }

    public getEmetteur(Message message) {
        return message.emetteur;
    }

    public getDestinataire(Message message) {
        return message.destinataire;
    }

    public getData(Message message) {
        return message.texte;
    }
}
