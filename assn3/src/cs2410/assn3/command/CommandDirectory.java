package cs2410.assn3.command;

import cs2410.assn3.controller.Controller;
import java.util.Scanner;

/**
 * @author Ember Fairbanks
 * @version 1.0
 */
public class CommandDirectory {

    /**
     * Create private instance of Scanner to get input from the user.
     */
    private Scanner scan = new Scanner(System.in);
    /**
     * Private integer used to store the user's choice.
     */
    private int selection;
    /**
     * Create private instance of Controller to call methods from Controller.
     */
    private Controller controller = new Controller();

    /**
     * Displays main menu by calling displayMenu() from the Controller.
     */
    public void getMenu(){
        String menu=controller.displayMenu();
        System.out.print(menu);
    }
    /**
     * Collects the user's selection and returns it.
     * @return user's selection
     */
    public int getSelection(){
        selection = scan.nextInt();
        return selection;
    }

    /**
     * Displays the contents of the directory.
     * Calls listDirectory() from Controller.
     */
    public void getDirectoryList(){
        String directory = controller.listDirectory();
        System.out.print(directory);
        System.out.println();
    }
    /**
     * Prompts the user to enter a name, age, and phone number and calls the
     * addStudent method from Controller.
     */
    public void addNewStudent(){
        System.out.print("First Name: ");
        String firstName = scan.next();
        System.out.print("Last Name: ");
        String lastName = scan.next();
        System.out.print("Age: ");
        int age = scan.nextInt();
        System.out.print("Phone Number: ");
        String phoneNumber = scan.next();
        controller.addStudent(firstName, lastName, age, phoneNumber);
        System.out.println("The following student has been added to the directory:");
        System.out.println(firstName + " " + lastName + ", age:" + age + ", phone number:" + phoneNumber);
        System.out.println();
    }

    /**
     * Calls computeAverage() from Controller and displays the average student age.
     */
    public void getAverage(){
        double average=controller.computeAverage();
        System.out.print("Average age of students: ");
        System.out.printf("%.1f",average);
        System.out.println("\n");
    }

    /**
     * Default Constructor.
     * Calls class methods and continues to loop until the user chooses
     * to quit the program.
     */
    public CommandDirectory() {
        while (true) {
            getMenu();
            selection = getSelection();
            if (selection == 1) {
                getDirectoryList();
            }
            else if (selection == 2) {
                addNewStudent();
            }
            else if(selection == 3){
                getAverage();
            }
            else if(selection == 4){
                System.exit(0);
            }
            else {
                System.out.println("Invalid selection. Please enter a number 1-4");
            }
        }
    }

    /**
     * Creates an instance of CommandDirectory.
     * @param args
     */
    public static void main(String args[]){
        new CommandDirectory();
    }
}
