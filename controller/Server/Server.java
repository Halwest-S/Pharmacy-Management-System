package controller.Server;


import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        try{
            System.out.println("Waiting for Client");
            ServerSocket ss = new ServerSocket(4940);
            Socket soc = ss.accept();
            System.out.println("Successfully Connected.");
        }  catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
    }
}