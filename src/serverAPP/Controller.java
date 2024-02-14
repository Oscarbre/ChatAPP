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

    public void sendMessage(String messageToSend){
        Message msg = new Message(client.getUsername(),"ALL",messageToSend);
        client.sendStream(msg);
    }

//////////////////////////////////////////////////////////             TO GUI            ///////////////////////////////////////////////


    public void updateConversation(Message msgFromServer){
        BorderPane root = gui.getRoot();
        gui.updateConversationContainer(msgFromServer);
    }

    
    
}
