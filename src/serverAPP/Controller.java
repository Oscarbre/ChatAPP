package serverAPP;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Controller {
    

    public Controller() {

    }

    public void createClient(String username) {
        try {
            Socket socket = new Socket("localhost", 1234);
            Client client = new Client(socket, username);
            System.out.println("SERVER : Vous avez rejoint la conversation. ");
            client.listenForMessage();
            // client.sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
