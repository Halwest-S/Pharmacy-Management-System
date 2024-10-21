import model.Employee;
import model.Item;
import view.StartView;
import controller.itemController;
import controller.employeeController;
public class Main {
    public static void main(String []args){

/* itemController itemController=new itemController();
itemController.addItem(new Item(1,"fluout","outflu","awamedica","Kurdistan","pill",1.5,2.0,100,"2021","2022"));
        itemController.addItem(new Item(2,"fluout","outflu","awamedica","usa","pill",1.5,2.0,100,"2021","2022"));
        itemController.addItem(new Item(2,"fluout","outflu","awamedica","usa","pill",1.5,2.0,100,"2021","2022"));*/


        StartView StartView=new StartView();
        StartView.display();
    }
}
