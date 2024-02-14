package serverAPP;

import java.io.IOException;
import java.net.Socket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Controller {

    private Client client;
    private GUI gui;

    public Controller(GUI gui) {
        this.gui = gui;
    }

//////////////////////////////////////////////////////////             TO CLIENT            ///////////////////////////////////////////////

    public void createClient(String username) {
        try {
            Socket socket = new Socket("localhost", 1234);
            client = new Client(socket, username, this);
            client.sendStream(new Message(username,"NONE","NONE")); //pour initialiser le clienHandler
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String messageToSend){
        Message msg = new Message(client.getUsername(),"ALL",messageToSend);
        client.sendStream(msg);
    }

//////////////////////////////////////////////////////////             TO GUI            ///////////////////////////////////////////////


    public void updateConversation(Message msgFromServer){
        VBox conversationContainer = gui.getConversationContainer();
        HBox messageBox = new HBox(5);
        messageBox.setPadding(new Insets(5, 0, 5, 5));      // haut droite bas gauche
        messageBox.setAlignment(Pos.CENTER_LEFT);

        Text senderText = new Text(msgFromServer.getSender() + " : ");
        Text msgText = new Text(msgFromServer.getData());
        senderText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        msgText.setFont(Font.font("Arial", 14));
        
        messageBox.getChildren().addAll(senderText, msgText);

        conversationContainer.getChildren().add(messageBox);
        
        
    }

    
    
}
