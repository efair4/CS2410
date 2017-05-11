package cs2410.assn7.toolbar;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * This class sets up the toolbar for the assignment.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class ToolBar extends HBox {
    /**
     * Private instance of ComboBox that gives the user
     * options of what information they want to see.
     */
    private ComboBox<String> infoChoice=new ComboBox<>(FXCollections.observableArrayList(
            "Math", "Income", "Hours", "IQ", "Say", "Carrots", "Contracts"
    ));

    /**
     * Private ToggleButton to add a new Contract Worker.
     */
    private ToggleButton contract=new ToggleButton("Contract Worker");

    /**
     * Private ToggleButton to add a new Hourly Worker.
     */
    private ToggleButton hourly=new ToggleButton("Hourly Worker");

    /**
     * Private ToggleButton to add a new Hobbit.
     */
    private ToggleButton hobbit=new ToggleButton("Hobbit");

    /**
     * Constructor for ToolBar that adds the buttons and combo box
     * in HBox format.
     */
    public ToolBar(){
        this.getChildren().addAll(infoChoice, contract, hourly, hobbit);

        ToggleGroup toggleGroup=new ToggleGroup();
        toggleGroup.getToggles().addAll(contract, hourly, hobbit);

        this.setPadding(new Insets(5,5,5,5));
        this.setSpacing(5);

    }

    /**
     * Creates a setOnAction event to be executed when the contract button is selected.
     * @param event
     */
    public void setContractBtnAction(EventHandler<ActionEvent> event){
        contract.setOnAction(event);
    }

    /**
     * Creates a setOnAction event to be executed when the hourly button is selected.
     * @param event
     */
    public void setHourlyBtnAction(EventHandler<ActionEvent> event){
        hourly.setOnAction(event);
    }

    /**
     * Creates a setOnAction event to be executed when the hobbit button is selected.
     * @param event
     */
    public void setHobbitBtnAction(EventHandler<ActionEvent> event){
        hobbit.setOnAction(event);
    }
    public boolean contractBtnSelected(){return contract.isSelected();}
    public boolean hourlyBtnSelected(){return hourly.isSelected();}
    public boolean hobbitBtnSelected(){return hobbit.isSelected();}

    /**
     * Creates a setOnAction event to be executed when a choice from
     * the combo box is selected.
     * @param event
     */
    public void setComboBoxAction(EventHandler<ActionEvent> event) { infoChoice.setOnAction(event); }

    /**
     * Method to get the current choice from the comboBox
     * @return infoChoice value
     */
    public String getInfoChoice(){
        return infoChoice.getValue();
    }

}
