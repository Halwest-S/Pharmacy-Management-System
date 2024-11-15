import controller.Client.Client;
import view.StartView;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();

        try {

            if (client.connect()) {
                System.out.println("Connected to the server successfully.");

                StartView startView = new StartView();
                startView.display();
            } else {
                System.err.println("Failed to connect to the server after multiple attempts.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred during client operations: " + e.getMessage());
            e.printStackTrace();
        } finally {
            client.disconnect();
            System.out.println("Disconnected from server.");
        }
    }
}
