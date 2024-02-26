package Server;

import com.fasterxml.jackson.databind.ObjectMapper;

import User.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private ObjectMapper objectMapper; //sérialisation json

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.objectMapper = new ObjectMapper();
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = objectMapper.readValue(bufferedReader.readLine(),Message.class).getSender();
            clientHandlers.add(this);
            broadcastMessage(new Message("SERVER","ALL"," " + clientUsername + " a rejoint la conversation !"));
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
        // Thread qui écoute les messages envoyés par son client et qui les redirige vers les autres clients

    @Override
    public void run() {
        Message messageFromClient;

        while (socket.isConnected()) {
            try {
                String jsonClient = bufferedReader.readLine();
                messageFromClient = objectMapper.readValue(jsonClient, Message.class);
                broadcastMessage(messageFromClient);
            } catch (IOException  e) {
                e.printStackTrace();
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

        // Envoi d'un message 

    public void broadcastMessage(Message messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(objectMapper.writeValueAsString(messageToSend));
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();
                
            }
        }
    }    
    
    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage(new Message("SERVEUR","ALL", clientUsername + " a quitté la conversation."));
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
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
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientUsername() {
        return clientUsername;
    }
    
}
