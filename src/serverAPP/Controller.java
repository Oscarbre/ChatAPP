package serverAPP;

import java.io.IOException;
import java.net.Socket;

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

    public void sendMessage(Message messageToSend){
        client.sendStream(messageToSend);
    }

//////////////////////////////////////////////////////////             TO GUI            ///////////////////////////////////////////////


    public void updateConversation(Message msgFromServer){
        gui.updateConversationContainer(msgFromServer);
    }

    
    
}
