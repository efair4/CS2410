package cs2410.assn4.main;

import cs2410.assn4.controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

/**
 * This program is an image viewer that displays images
 * from a URL list in the data directory. It allows the
 * user to add and delete images as well.
 *
 * @author Ember Fairbanks
 * @version 2.0
 */

public class Main extends Application {
    /**
     * private Button to view the previoius image
     */
    private Button prev;

    /**
     * private Button to view the next image
     */
    private Button next;

    /**
     * private Button to delete an image
     */
    private Button del;

    /**
     * private Button to add an image
     */
    private Button add;

    /**
     * private int that keeps track of the current image index
     */
    private int current;

    /**
     * private Controller to access methods in Controller.java
     */
    private Controller controller = new Controller();

    /**
     * private ImageView
     */
    private ImageView imageView=new ImageView();

    /**
     * private int for storing the last index of the images ArrayList
     */
    private int maxIndex;

    /**
     * private instance of EventHandler
     */
    private EventHandler<ActionEvent> handler;


    /**
     * Calls the readFile method from Controller
     */
    public void readFile() {
        controller.readFile();
    }

    /**
     * Calls the currentImage method from Controller
     * @param index
     * @return an Image object
     */
    public Image currentImage(int index) {
        return controller.currentImage(index);
    }

    /**
     * Calls the addImage method from Controller
     */
    public Image addImage() {
        TextInputDialog input=new TextInputDialog();
        input.setTitle("Add new image URL");
        input.setHeaderText(null);
        input.setGraphic(null);
        input.setContentText("What is the new URL?");
        input.setWidth(300);
        String newUrl=input.showAndWait().get();

        disableButtons(false);

        return controller.addImage(newUrl, current+1);
    }

    /**
     * Calls the deleteImage method from Controller. If true is
     * returned from that method call then it indicates that the
     * size of the images ArrayList is 0 and disables all buttons
     * except for add.
     * @param index
     */
    public Image deleteImage(int index) {
        return controller.deleteImage(index);
    }

    /**
     * Calls the saveFile method from Controller
     */
    public void saveFile(){
        controller.saveFile();
    }

    /**
     * Calls the getMaxIndex method from Controller and
     * sets maxIndex equal to the value returned.
     */
    public void getMaxIndex(){
        maxIndex=controller.getMaxIndex();
    }

    /**
     * Disables or enables the Previous, Next, and Delete buttons
     * depending on whether the boolean value received is true
     * or false.
     * @param disable
     */
    public void disableButtons(boolean disable){
        prev.setDisable(disable);
        del.setDisable(disable);
        next.setDisable(disable);
    }


    /**
     *Here, the ImageViewer, pane, scene, and primaryStage are
     * set up and the buttonHandler method is called.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        readFile();
        current = 0;
        imageView.setImage(currentImage(current));
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(100);
        imageView.setLayoutY(75);

        prev = new Button("Previous");
        next = new Button("Next");
        del = new Button("Delete");
        add = new Button("Add");
        Pane pane1 = new Pane();
        Scene scene1 = new Scene(pane1, 600, 600);

        prev.setLayoutX(100);
        prev.setLayoutY(500);
        prev.setPrefWidth(100);
        del.setLayoutX(200);
        del.setLayoutY(500);
        del.setPrefWidth(100);
        add.setLayoutX(300);
        add.setLayoutY(500);
        add.setPrefWidth(100);
        next.setLayoutX(400);
        next.setLayoutY(500);
        next.setPrefWidth(100);

        pane1.getChildren().add(prev);
        pane1.getChildren().add(del);
        pane1.getChildren().add(add);
        pane1.getChildren().add(next);
        pane1.getChildren().add(imageView);

        buttonHandler();

        prev.setOnAction(handler);
        del.setOnAction(handler);
        add.setOnAction(handler);
        next.setOnAction(handler);

        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Image Viewer");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                saveFile();
            }
        });

        primaryStage.show();
    }

    /**
     * buttonHandler checks the event and calls the appropriate
     * method to interact with the user and the methods in Controller.
     */
    private void buttonHandler() {
        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == prev) {
                    current--;
                    getMaxIndex();
                    if(current<0){
                        current=maxIndex;
                    }
                    imageView.setImage(currentImage(current));
                }
                else if (event.getSource() == add) {
                    imageView.setImage(addImage());
                }
                else if (event.getSource() == del) {
                    getMaxIndex();
                    if(maxIndex<0){
                        disableButtons(true);
                    }
                    else {
                        imageView.setImage(deleteImage(current));
                        current--;
                        getMaxIndex();
                        if(current<0){
                            current=maxIndex;
                            if(current<0&&maxIndex<0){
                                disableButtons(true);
                            }
                        }
                    }
                }
                else if (event.getSource() == next) {
                    current++;
                    getMaxIndex();
                    if(current>maxIndex){
                        current=0;
                    }
                    imageView.setImage(currentImage(current));
                }
            }
        };
    }

}



