//this will be a file to test the backend only not the UI because the UI will be a graphical user interface
import model.*;
public class App {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Food Delivery System Starting...");
        
            // Create a sample menu item
            MenuItems burger = new MenuItems("M001", "Cheeseburger", -8.99, "Main");
            System.out.println("Menu item created: " + burger);
       
        } catch(ArithmeticException e) {
            System.out.println("Arithmetic Error: " + e.getMessage());
        }
        
    }
}
