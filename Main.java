import controller.Client.Client;
import model.Employee;
import model.Item;
import view.StartView;
import controller.itemController;
import controller.employeeController;

public class Main {
    public static void main(String []args){
        Client.startConnection();


        StartView StartView=new StartView();
        StartView.display();
    }
}
