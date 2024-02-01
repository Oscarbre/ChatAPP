package serverAPP;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Controller {
    

    public Controller() {

    }

    public void createClient() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez votre nom d'utilisateur pour cette conversation : ");
            String username = scanner.nextLine();
            Socket socket = new Socket("localhost", 1234);
            Client client = new Client(socket, username);
            System.out.println("SERVER : Vous avez rejoint la conversation. ");
            client.listenForMessage();
            client.sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
