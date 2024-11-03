package controller.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                LOGGER.info("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create and start a new thread for each client connection
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread clientThread = new Thread(clientHandler); // Since ClientHandler implements Runnable
                clientThread.start();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Server error: " + e.getMessage(), e);
        }
    }
}
