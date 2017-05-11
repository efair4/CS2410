package cs2410.assn5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * This program is a drawing tablet for drawing ellipses
 * with a mouse drag event.
 * @author Ember Fairbanks
 * @version 1.1 Updated imported packages and made toolPane
 * a local variable.
 */

public class Main extends Application {
    /**
     * private Button to indicate that the user wants to draw
     */
    private Button draw;
    /**
     * private Button to indicate that the user wants to erase
     */
    private Button erase;
    /**
     * private EventHandler for the buttons
     */
    private EventHandler handler;
    /**
     * This pane will contain the space for the user to draw
     */
    private Pane drawPane;
    /**
     * Ellipse that is created every time the user clicks and drags
     */
    private Ellipse ell;
    /**
     * This variable stores the starting click position in the X direction
     */
    private double startX;
    /**
     * This variable store the starting click position in the Y direction
     */
    private double startY;
    /**
     * boolean that triggers whether Draw or Erase is pressed and allows
     * the code inside of the setOn methods to be executed
     */
    private boolean doDraw=true;

    /**
     * The start method defines the buttons, panes, and scene. The drawPane
     * setOn methods are also initialized.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        draw = new Button("Draw");
        erase = new Button("Erase");
        draw.setPrefWidth(100);
        draw.setLayoutX(145);
        draw.setLayoutY(40);

        erase.setPrefWidth(100);
        erase.setLayoutX(255);
        erase.setLayoutY(40);
        Pane totalPane = new Pane();

        Pane toolPane = new Pane();
        toolPane.setPrefSize(500, 100);
        toolPane.getChildren().addAll(draw, erase);
        initHandler();

        drawPane = new Pane();
        drawPane.setPrefSize(500, 500);
        drawPane.setLayoutY(100);

        Scene scene1 = new Scene(totalPane, 500, 600);
        totalPane.getChildren().addAll(drawPane, toolPane);

        Rectangle drawClip = new Rectangle(500, 500);
        drawPane.setClip(drawClip);
        draw.setOnAction(handler);
        erase.setOnAction(handler);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Somewhat Lame Drawing Tablet");
        primaryStage.setScene(scene1);
        primaryStage.show();

        drawPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(doDraw) {
                    startX = event.getX();
                    startY = event.getY();
                    ell = new Ellipse();
                    initEllipseHandle();
                }
            }
        });
        drawPane.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(doDraw) {
                    drawPane.getChildren().add(ell);
                }
            }
        });
        drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(doDraw) {
                    ell.setRadiusX(Math.abs((event.getX() - startX) / 2));
                    ell.setRadiusY(Math.abs((event.getY() - startY) / 2));
                    ell.setCenterX((event.getX() - startX) / 2 + startX);
                    ell.setCenterY((event.getY() - startY) / 2 + startY);
                    ell.setStroke(Color.SKYBLUE);
                    ell.setStrokeWidth(5);
                    ell.setFill(Color.MEDIUMSPRINGGREEN);
                }
            }
        });
    }

    /**
     * This handler is called from the two buttons in the start method.
     * If the source is the draw button, the doDraw boolean is set to true.
     * If the source is the erase button, doDraw is set to false.
     */
    private void initHandler() {
        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == draw) {
                   doDraw=true;
                }
                else if(event.getSource()==erase){
                    doDraw=false;
                }
            }
        };
    }

    /**
     * This handler allows ellipses to be erased when they are clicked.
     * It executes when doDraw is false.
     */
    private void initEllipseHandle(){
        ell.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if(!doDraw) {
                    drawPane.getChildren().remove(event.getTarget());
                }
            }
        });

    }

}



