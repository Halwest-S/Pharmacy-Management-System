package controller.Client;


import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Client Started");
            Socket so = new Socket("localhost", 4940);
        }
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to sever.");
            System.exit(1);
        }

    }
}