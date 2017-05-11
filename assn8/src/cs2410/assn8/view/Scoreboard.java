package cs2410.assn8.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Scoreboard sets up the top area for the game. It contains
 * the number of bombs left, a restart button, and the time.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Scoreboard extends HBox {
    /**
     * Restart button to start a new game after you win or lose.
     */
    private Button start = new Button("Restart");

    /**
     * Integer to hold the count of the number of bombs
     * you can mark.
     */
    private Integer bombsLeft=40;

    /**
     * Integer to hold the number of seconds that have passed
     * since the game started.
     */
    private Integer timeElapsed=0;

    /**
     * IntegerProperty to help with property binding for the
     * bombsLeft variable.
     */
    private IntegerProperty bombProperty=new SimpleIntegerProperty(bombsLeft);

    /**
     * IntegerProperty to help with property binding for the
     * timeElapsed variable.
     */
    private IntegerProperty timeProperty=new SimpleIntegerProperty(timeElapsed);

    /**
     * The Scoreboard constructor adds images of a bomb, VBoxes
     * for the bomb count and time, and the restart button to
     * the HBox.
     */
    public Scoreboard(){
        Image image1 = new Image("file:data/bomb.png", 65,65,true,true);
        ImageView imageView1=new ImageView();
        Image image2 = new Image("file:data/bomb.png", 65,65,true,true);
        ImageView imageView2=new ImageView();
        imageView2.setImage(image2);
        imageView1.setImage(image1);
        Label bombLabel=new Label();
        Label timeLabel=new Label();
        bombLabel.textProperty().bind(bombProperty.asString());
        timeLabel.textProperty().bind(timeProperty.asString());
        VBox bombBox=new VBox(new Label("Bombs Left"), bombLabel);
        VBox timeBox=new VBox(new Label("Time Elapsed"), timeLabel);
        bombBox.setId("infoBox");
        timeBox.setId("infoBox");
        bombBox.setAlignment(Pos.CENTER);
        timeBox.setAlignment(Pos.CENTER);
        start.setId("startBtn");
        setAlignment(Pos. CENTER);
        setSpacing(40);
        setPadding(new Insets(10));
        setStyle("-fx-border-width: 3px;"+"-fx-border-color: black;");
        this.getChildren().addAll(imageView1, bombBox, start, timeBox, imageView2);
    }

    /**
     * Increments the timeElapsed variable by one each time
     * it is called.
     */
    public void updateTime(){
        timeProperty.set(timeElapsed++);
    }

    /**
     * Increments the bombsLeft variable by one each time
     * it is called.
     */
    public void incrementBombs(){
        bombProperty.set(++bombsLeft);
    }

    /**
     * Decrements the bombsLeft variable by one each time
     * it is called.
     */
    public void decrementBombs(){
        bombProperty.set(--bombsLeft);
    }

    /**
     * Sets up the setOnAction for the start button
     * @param event
     */
    public void setStartAction(EventHandler<ActionEvent> event){
        start.setOnAction(event);
    }

    /**
     * Returns the current value of the timeElapsed variable.
     * @return timeElapsed
     */
    public Integer getTimeElapsed(){
        return timeElapsed;
    }

    /**
     * Disables or enables the start button based on the boolean value sent.
     * @param disable
     */
    public void disableStart(boolean disable){
        start.setDisable(disable);
    }
}
