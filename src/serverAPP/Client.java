package serverAPP;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private ObjectMapper clientObjectMapper;
    private Controller controller;

    public Client(Socket socket, String username, Controller controller) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
            this.clientObjectMapper = new ObjectMapper();
            this.controller = controller;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }
    } 

    public void sendStream(Message messageToSend) {
        try {
            if (socket.isConnected()) {
                bufferedWriter.write(clientObjectMapper.writeValueAsString(messageToSend));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else {
                System.out.println("ERREUR : socket non connecté");
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msgFromServer;

                while (socket.isConnected()) {
                    try {
                        String jsonServer = bufferedReader.readLine();
                        msgFromServer = clientObjectMapper.readValue(jsonServer, Message.class);
                        controller.updateConversation(msgFromServer);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    // public static void main(String[] args) {
    //     try {
    //         Scanner scanner = new Scanner(System.in);
    //         System.out.println("Entrez votre nom d'utilisateur pour cette conversation : ");
    //         String username = scanner.nextLine();
    //         Socket socket = new Socket("localhost", 1234);
    //         Client client = new Client(socket, username);
    //         System.out.println("SERVER : Vous avez rejoint la conversation. ");
            
    //         client.listenForMessage();
    //         client.sendMessage();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

}
