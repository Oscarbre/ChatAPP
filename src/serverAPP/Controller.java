package serverAPP;

import java.io.IOException;
import java.net.Socket;

public class Controller {

    private Client client;
    private GUI gui;

    public Controller(GUI gui) {
        this.gui = gui;
    }

    public void createClient(String username) {
        try {
            Socket socket = new Socket("localhost", 1234);
            client = new Client(socket, username, this);
            System.out.println("SERVER : Vous avez rejoint la conversation. ");
            client.listenForMessage();
            client.sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateConversation(Message msgFromServer){

    }

    private void sendMessage(){

    }
    
}
