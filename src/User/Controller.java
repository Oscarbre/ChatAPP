package User;

import java.io.IOException;
import java.net.Socket;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
            this.client = new Client(socket, username, this);
            client.sendStream(new Message(username,"NONE","NONE")); //pour initialiser le clienHandler
            client.listenForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message messageToSend){
        client.sendStream(messageToSend);
    }

//////////////////////////////////////////////////////////             TO GUI            ///////////////////////////////////////////////


    public void updateConversation(Message msgFromServer){
        Platform.runLater(() -> {;                                          // Pour se séparer du thread principal de javafx
            VBox conversationContainer = gui.getConversationContainer();
            HBox messageBox = new HBox(5);
            messageBox.setPadding(new Insets(5, 0, 5, 5));      // haut droite bas gauche
            messageBox.setAlignment(Pos.CENTER_LEFT);

            Text senderText = new Text(msgFromServer.getTime() + " " + msgFromServer.getSender() + " :");
            Text msgText = new Text(msgFromServer.getData());
            msgText.setFont(Font.font("Arial", 14));

            if (msgFromServer.getSender() == "SERVEUR") {
                messageBox.setStyle("-fx-background-color: #D3D3D3;");
                msgText.setFont(Font.font("Arial", FontWeight.BOLD,14));
            } else if (msgFromServer.getSender() == client.getUsername()) {
                messageBox.setStyle("-fx-background-color: #87CEEB;");
                senderText = new Text(msgFromServer.getTime() + " MOI :");
            } else {
                messageBox.setStyle("-fx-background-color: #ADD8E6;");
            }

            senderText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            
            messageBox.getChildren().addAll(senderText, msgText);

            conversationContainer.getChildren().add(messageBox);
        });
        
        
    }

    public void closeEverything() {
        Platform.runLater(() -> {;
            client.closeEverything();
        });
    }

    
    
}
