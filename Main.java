import controller.Client.Client;
import view.StartView;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();

        try {
            // Attempt to connect to the server
            if (client.connect()) {
                System.out.println("Connected to the server successfully.");

                // Start the main application view
                StartView startView = new StartView();
                startView.display();
            } else {
                System.err.println("Failed to connect to the server after multiple attempts.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred during client operations: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure disconnection from the server when application exits
            client.disconnect();
            System.out.println("Disconnected from server.");
        }
    }
}
