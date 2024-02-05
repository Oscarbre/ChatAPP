package serverAPP;

import java.io.IOException;
import java.net.*;

public class Server {

    private ServerSocket serverSocket;
    public static int port = 1234;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        System.out.println("Serveur ouvert || En attente de connection des clients ");
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
                System.out.println(clientHandler.clientUsername + " a rejoint la session ! Nombre d'utilisateur actuellements connectés : " + clientHandler.clientHandlers.size());
            }
        } catch (IOException e) {

        }
    }

    public void closeServerSocket(){
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
            ServerSocket serverSocket = new ServerSocket(port);
            Server server = new Server(serverSocket);
            server.startServer();
    }
}


