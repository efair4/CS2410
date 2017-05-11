package cs2410.assn7.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * This class controls the view to add a new person.
 * @author Ember Fairbanks
 * @version 1.0
 */
class Add {
    /**
     * VBox to hold the HBoxes needed for each
     * type of person
     */
    private VBox inputBox = new VBox();
    /**
     * TextField to get the name input
     */
    TextField name = new TextField();
    /**
     * TextField to get the math input
     */
    TextField math = new TextField();
    /**
     * TextField to get the say input
     */
    TextField say = new TextField();
    /**
     * TextField to get the IQ input
     */
    TextField iq = new TextField();
    /**
     * TextField to get the hours input
     */
    TextField hours = new TextField();
    /**
     * TextField to get the wage input
     */
    TextField wage = new TextField();
    /**
     * TextField to get the carrots input
     */
    TextField carrots = new TextField();
    /**
     * TextField to get the payPerContract input
     */
    TextField payPerContract = new TextField();
    /**
     * TextField to get the contracts input
     */
    TextField contracts = new TextField();
    /**
     * Label for the name TextField
     */
    private Label nameLabel=new Label("Name:");
    /**
     * Label for the math TextField
     */
    private Label mathLabel=new Label("Math:");
    /**
     * Label for the say TextField
     */
    private Label sayLabel=new Label("Say:");
    /**
     * Label for the iq TextField
     */
    private Label iqLabel=new Label("IQ:");
    /**
     * Label for the hours TextField
     */
    private Label hoursLabel=new Label("Hours:");
    /**
     * Label for the wage TextField
     */
    private Label wageLabel=new Label("Wage:");
    /**
     * Label for the carrots TextField
     */
    private Label carrotsLabel=new Label("Carrots:");
    /**
     * Label for the payPerContract TextField
     */
    private Label payPerConLabel=new Label("Pay Per Contract:");
    /**
     * Label for the contracts TextField
     */
    private Label contractsLabel=new Label("Contracts:");

    /**
     * The correct nodes are added for the ContractWorker information.
     * @return inputBox
     */
    VBox getContractPane() {
        inputBox.getChildren().clear();
        VBox conLabel=new VBox(15);
        VBox conText=new VBox(5);
        HBox conBox=new HBox();
        conLabel.setPadding(new Insets(5,5,5,5));
        conLabel.getChildren().addAll(nameLabel, mathLabel, sayLabel,iqLabel, contractsLabel, payPerConLabel);
        conText.getChildren().addAll(name, math, say, iq, contracts, payPerContract);
        conBox.getChildren().addAll(conLabel, conText);
        inputBox.getChildren().add(conBox);
        return inputBox;
    }

    /**
     * The correct nodes are added for the HourlyWorker information.
     * @return inputBox
     */
    VBox getHourlyPane() {
        inputBox.getChildren().clear();
        VBox hourlyLabel=new VBox(15);
        VBox hourlyText=new VBox(5);
        HBox hourlyBox=new HBox();
        hourlyLabel.setPadding(new Insets(5,5,5,5));
        hourlyLabel.getChildren().addAll(nameLabel, mathLabel, sayLabel,iqLabel, hoursLabel, wageLabel);
        hourlyText.getChildren().addAll(name, math, say, iq, hours, wage);
        hourlyBox.getChildren().addAll(hourlyLabel, hourlyText);
        inputBox.getChildren().add(hourlyBox);
        return inputBox;
    }

    /**
     * The correct nodes are added for the Hobbit information.
     * @return inputBox
     */
    VBox getHobbitPane() {
        inputBox.getChildren().clear();
        VBox hobbitLabels=new VBox(15);
        VBox hobbitText=new VBox(5);
        HBox hobbitBox=new HBox();
        hobbitLabels.setPadding(new Insets(5,5,5,5));
        hobbitLabels.getChildren().addAll(nameLabel, mathLabel, sayLabel, carrotsLabel);
        hobbitText.getChildren().addAll(name, math, say, carrots);
        hobbitBox.getChildren().addAll(hobbitLabels, hobbitText);
        inputBox.getChildren().add(hobbitBox);
        return inputBox;
    }
}