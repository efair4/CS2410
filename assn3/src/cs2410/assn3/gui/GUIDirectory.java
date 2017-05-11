package cs2410.assn3.gui;

import cs2410.assn3.controller.Controller;
import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *GUIDirectory calls methods from Controller and interacts with the user
 * via dialog boxes.
 *
 * @Ember Fairbanks
 * @version 1.0
 */

public class GUIDirectory extends Application {

    /**
     *Create private instance of TextInputDialog. input is used by getMenu() and getSelection();
     */
    private TextInputDialog input;
    /**
     * Create private instance of Controller to call methods from Controller.
     */
    private Controller controller = new Controller();

    /**
     * Display main menu in the input TextInputDialog.
     */
    public void getMenu(){
        String menu=controller.displayMenu();
        input = new TextInputDialog("1-4");
        input.setTitle("Menu");
        input.setHeaderText(null);
        input.setGraphic(null);
        input.setContentText(menu);
    }

    /**
     * Collects user's selection.
     * @return user's selection
     */
    public String getSelection(){
        String selection = input.showAndWait().get();
        input.getEditor().clear();
        return selection;
    }

    /**
     * Displays the contents of the directory using an Alert.
     * Calls listDirectory() from Controller.
     */
    public void getDirectoryList(){
        Alert showDirectoryList = new Alert(Alert.AlertType.INFORMATION);
        String directory = controller.listDirectory();
        showDirectoryList.getDialogPane().getStylesheets().add("resource/stylesheet.css");
        showDirectoryList.getDialogPane().setPrefWidth(600);
        showDirectoryList.setTitle("Directory Contents");
        showDirectoryList.setHeaderText(null);
        showDirectoryList.setGraphic(null);
        showDirectoryList.setContentText(directory);
        showDirectoryList.showAndWait();
    }

    /**
     * Prompts the user to enter a name, age, and phone number and calls the
     * addStudent method from Controller.
     */
    public void addNewStudent(){
        TextInputDialog newStudentDialog = new TextInputDialog();
        newStudentDialog.setTitle("Add new student");
        newStudentDialog.setHeaderText(null);
        newStudentDialog.setGraphic(null);
        newStudentDialog.setContentText("First Name: ");
        String firstName = newStudentDialog.showAndWait().get();
        newStudentDialog.getEditor().clear();
        newStudentDialog.setContentText("Last Name: ");
        String lastName = newStudentDialog.showAndWait().get();
        newStudentDialog.getEditor().clear();
        newStudentDialog.setContentText("Age: ");
        String age = newStudentDialog.showAndWait().get();
        newStudentDialog.getEditor().clear();
        newStudentDialog.setContentText("Phone Number: ");
        String phoneNumber = newStudentDialog.showAndWait().get();
        newStudentDialog.getEditor().clear();
        controller.addStudent(firstName, lastName, Integer.parseInt(age), phoneNumber);
        Alert addConfirmation = new Alert(Alert.AlertType.INFORMATION);
        addConfirmation.setTitle("Student added successfully");
        addConfirmation.setHeaderText(null);
        addConfirmation.setGraphic(null);
        addConfirmation.getDialogPane().setPrefWidth(550);
        addConfirmation.setContentText("The following student has been added: \n" +
                firstName + " " + lastName + ", age:" + age + ", phone number:" + phoneNumber);
        addConfirmation.showAndWait();
    }

    /**
     * Calls computeAverage() from Controller and displays the average student age.
     */
    public void getAverage(){
        Alert showAverage = new Alert(Alert.AlertType.INFORMATION);
        double average=controller.computeAverage();
        showAverage.setTitle("Average Age");
        showAverage.setHeaderText(null);
        showAverage.setContentText("Average age of students: " + String.format("%.1f",average));
        showAverage.showAndWait();
    }

    /**
     * Calls class methods and continues to loop until the user chooses
     * to quit the program.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        while (true) {
            getMenu();
            String selectionString = getSelection();
            int selection=Integer.parseInt(selectionString);
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
                Alert wrongInput = new Alert(Alert.AlertType.ERROR);
                wrongInput.setContentText("Invalid selection. Please enter a number 1-4");
                wrongInput.showAndWait();
            }
        }


    }

}

