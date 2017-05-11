package cs2410.assn8.view;

import cs2410.assn8.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This application is a version of the Minesweeper game.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller=new Controller();

        primaryStage.setScene(new Scene(controller));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Minesweeperish");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> controller.endTimer());
    }

}
