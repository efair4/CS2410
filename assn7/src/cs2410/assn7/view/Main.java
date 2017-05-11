package cs2410.assn7.view;

import cs2410.assn7.control.Controller;
import cs2410.assn7.toolbar.ToolBar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * This program allows a user to create different types of
 * people and view specific information about them using a GUI.
 * @author Ember Fairbanks
 * @version 1.1
 */
public class Main extends Application {
    /**
     * Instance of the Add class
     */
    private Add addClass=new Add();
    /**
     * Instance of the ToolBar class
     */
    private ToolBar toolBar=new ToolBar();

    /**
     *Creates a GUI and adds the ToolBar instance. The setOn methods
     * are defined for the 3 toggle buttons and the combo box from
     * ToolBar as well as for the Cancel and Save buttons.
     * 2 objects are created for each type of person.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane buttonPane = new AnchorPane();
        BorderPane totalPane=new BorderPane();
        toolBar.setAlignment(Pos.CENTER);
        Controller controller=new Controller();
        Button save = new Button("Save");
        Button cancel = new Button("Cancel");
        AnchorPane.setBottomAnchor(save, 10.0);
        AnchorPane.setBottomAnchor(cancel, 10.0);
        AnchorPane.setRightAnchor(save, 80.0);
        AnchorPane.setRightAnchor(cancel, 10.0);
        buttonPane.getChildren().addAll(save, cancel);
        Alert confirmation=new Alert(Alert.AlertType.INFORMATION);
        confirmation.setHeaderText("Hooray!");

        Scene scene=new Scene(totalPane, 500, 500);
        totalPane.setTop(toolBar);
        totalPane.setBottom(buttonPane);
        cancel.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addClass.name.clear();
                addClass.math.clear();
                addClass.say.clear();
                addClass.iq.clear();
                addClass.hours.clear();
                addClass.wage.clear();
                addClass.carrots.clear();
                addClass.payPerContract.clear();
                addClass.contracts.clear();
            }
        }));
        toolBar.setContractBtnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                totalPane.setCenter(addClass.getContractPane());

            }
        });
        toolBar.setHourlyBtnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                totalPane.setCenter(addClass.getHourlyPane());
            }
        });
        toolBar.setHobbitBtnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                totalPane.setCenter(addClass.getHobbitPane());
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (toolBar.contractBtnSelected()) {
                    controller.createNewConWorker(addClass.name.getText(), addClass.math.getText(),
                            addClass.say.getText(), Integer.parseInt(addClass.iq.getText()),
                            Integer.parseInt(addClass.contracts.getText()), Double.parseDouble(addClass.payPerContract.getText()));
                    confirmation.setContentText("You successfully added "+addClass.name.getText());
                    addClass.name.clear();
                    addClass.math.clear();
                    addClass.say.clear();
                    addClass.iq.clear();
                    addClass.contracts.clear();
                    addClass.payPerContract.clear();
                } else if (toolBar.hourlyBtnSelected()) {
                    controller.createNewHourWorker(addClass.name.getText(), addClass.math.getText(),
                            addClass.say.getText(), Integer.parseInt(addClass.iq.getText()),
                            Integer.parseInt(addClass.hours.getText()), Double.parseDouble(addClass.wage.getText()));
                    confirmation.setContentText("You successfully added "+addClass.name.getText());
                    addClass.name.clear();
                    addClass.math.clear();
                    addClass.say.clear();
                    addClass.iq.clear();
                    addClass.hours.clear();
                    addClass.wage.clear();
                } else if (toolBar.hobbitBtnSelected()) {
                    controller.createNewHobbit(addClass.name.getText(), addClass.math.getText(),
                            addClass.say.getText(), Integer.parseInt(addClass.carrots.getText()));
                    confirmation.setContentText("You successfully added "+addClass.name.getText());
                    addClass.name.clear();
                    addClass.math.clear();
                    addClass.say.clear();
                    addClass.carrots.clear();
                }
                confirmation.showAndWait();
            }
        });

        toolBar.setComboBoxAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label displayText=new Label();
                displayText.setAlignment(Pos. CENTER);
                String currentString="";
                String choice=toolBar.getInfoChoice();
                if(choice.equals("Math")){
                    currentString=controller.getMathList();
                }
                else if (choice.equals("Income")){
                    currentString=controller.getIncomeList();
                }
                else if (choice.equals("Hours")){
                    currentString=controller.getHoursList();
                }
                else if (choice.equals("IQ")){
                    currentString=controller.getIQList();
                }
                else if (choice.equals("Say")){
                    currentString=controller.getSayList();
                }
                else if (choice.equals("Carrots")){
                    currentString=controller.getCarrotsList();
                }
                else if (choice.equals("Contracts")){
                    currentString=controller.getContractsList();
                }
                displayText.setText(currentString);
                totalPane.setCenter(displayText);
                totalPane.setRight(null);
            }
        });

        controller.createNewConWorker("Joe","20/4=5","The moon is far",120, 12, 44.00);
        controller.createNewConWorker("Sally","36/9=4","Stars are pretty",162, 75, 118.00);
        controller.createNewHourWorker("Herbert","4*5=20","A platypus lays eggs",114, 32, 17.50);
        controller.createNewHourWorker("Helga","13*13=169","Bears are dangerous",126, 42, 23.75);
        controller.createNewHobbit("Wilma", "2+5=7", "Peas are my favorite", 67);
        controller.createNewHobbit("Pooki", "14+8=22", "Peas are gross", 193);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Polyabstraheritance");
        primaryStage.show();

    }

}
