package cs2410.assn6.main;

import cs2410.assn6.toolpane.ToolPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.stage.Stage;


/**
 * This is a drawing tablet that allows the user to draw shapes
 * as well as freehand. They can choose the color of the stroke
 * and fill as well as the size of the stroke. The user will also
 * be allowed to edit the drawings.
 *
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Main extends Application {
    /**
     * This pane will contain the space for the user to draw
     */
    private Pane drawPane;
    /**
     * Ellipse that is created every time the user clicks and drags
     */
    private Ellipse ellipse;
    /**
     * Rectangle that is created every time the user clicks and drags
     */
    private Rectangle rec;
    /**
     * Path that is created every time the user clicks and drags
     */
    private Path path;
    /**
     * This variable stores the starting click position in the X direction
     */
    private double startX;
    /**
     * This variable store the starting click position in the Y direction
     */
    private double startY;
    /**
     * Instance of the ToolPane class that gives toolPane access to the
     * member functions of ToolPane and sets up the toolbar.
     */
    private ToolPane toolPane=new ToolPane();
    /**
     * Instance of Shape that is used in initColorHandler to control which
     * shape is edited.
     */
    private Shape selectedShape;

    /**
     * Start method for the program. The 3 different panes are set up
     * and the mouse events for the drawPane are defined.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        toolPane.setAlignment(Pos.CENTER);
        BorderPane totalPane = new BorderPane();

        drawPane = new Pane();
        drawPane.setPrefSize(650, 500); //500x500 was too small to show the entire toolbar
        Rectangle drawClip = new Rectangle(650, 500);
        drawClip.widthProperty().bind(primaryStage.widthProperty());
        drawClip.heightProperty().bind(primaryStage.heightProperty());
        drawPane.setClip(drawClip);

        Scene scene1 = new Scene(totalPane, 650, 550);
        totalPane.setCenter(drawPane);
        totalPane.setTop(toolPane);

        primaryStage.setTitle("Super Ultra Cool Drawing Tablet");
        primaryStage.setScene(scene1);
        primaryStage.show();



        drawPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(toolPane.ellBtnSelected()) {
                    startX = event.getX();
                    startY = event.getY();
                    ellipse = new Ellipse();
                    initShapeHandle(ellipse);
                    initColorHandler();
                }
                else if(toolPane.rectBtnSelected()){
                    startX=event.getX();
                    startY=event.getY();
                    rec=new Rectangle();
                    initShapeHandle(rec);
                    initColorHandler();
                }
                else if(toolPane.freeBtnSelected()){
                    path=new Path();
                    initShapeHandle(path);
                    initColorHandler();
                    path.getElements().add(new MoveTo(event.getX(), event.getY()));
                    drawPane.getChildren().add(path);
                }
            }
        });
        drawPane.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(toolPane.ellBtnSelected()) {
                    drawPane.getChildren().add(ellipse);
                }
                else if(toolPane.rectBtnSelected()){
                    drawPane.getChildren().add(rec);
                }
            }
        });
        drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(toolPane.ellBtnSelected()) {
                    ellipse.setRadiusX(Math.abs((event.getX() - startX) / 2));
                    ellipse.setRadiusY(Math.abs((event.getY() - startY) / 2));
                    ellipse.setCenterX((event.getX() - startX) / 2 + startX);
                    ellipse.setCenterY((event.getY() - startY) / 2 + startY);
                    shapeSettings(ellipse);
                }
                else if(toolPane.rectBtnSelected()){
                    rec.setHeight(Math.abs(event.getY()-startY));
                    rec.setWidth(Math.abs(event.getX()-startX));
                    if(event.getX()<startX){
                        rec.setX(event.getX());
                    }
                    else{
                        rec.setX(startX);
                    }
                    if(event.getY()<startY){
                        rec.setY(event.getY());
                    }
                    else{
                        rec.setY(startY);
                    }
                    shapeSettings(rec);
                }
                else if(toolPane.freeBtnSelected()){
                    path.getElements().add(new LineTo(event.getX(), event.getY()));
                    shapeSettings(path);
                    path.setFill(null);
                }
            }
        });
    }

    /**
     * Method that sets the fill, stroke, and stroke width of the
     * element that is sent to those that are currently selected in the
     * tool bar
     * @param shape
     */
    private void shapeSettings(Shape shape){
        shape.setFill(toolPane.getFillPickerValue());
        shape.setStroke(toolPane.getStrokePickerValue());
        shape.setStrokeWidth(toolPane.getStrokeSizeValue());
    }

    /**
     * Defines the setOn methods for the Shape object. These methods allow
     * the shape to be erased and moved.
     * @param shape
     */
    private void initShapeHandle(Shape shape){
        shape.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolPane.eraseBtnSelected()) {
                    drawPane.getChildren().remove(shape);
                }
                else if(toolPane.editBtnSelected()){
                    if(shape instanceof Path){
                        toolPane.setStrokePickerValue((Color) shape.getStroke());
                        toolPane.setStrokeSizeValue((int) shape.getStrokeWidth());
                    }
                    else {
                        toolPane.setFillPickerValue((Color) shape.getFill());
                        toolPane.setStrokePickerValue((Color) shape.getStroke());
                        toolPane.setStrokeSizeValue((int) shape.getStrokeWidth());
                    }
                }
            }
        });
        shape.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event){
                if(toolPane.editBtnSelected()){
                    startX=event.getX();
                    startY=event.getY();
                    selectedShape=shape;
                }
            }
        });
        shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolPane.editBtnSelected()) {
                    shape.setTranslateX(shape.getTranslateX() + event.getX() - startX);
                    shape.setTranslateY(shape.getTranslateY() + event.getY() - startY);
                }
            }
        });
    }

    /**
     * Defines the setOn methods from ToolPane. These methods allow the
     * Shape object to be edited with new choices of fill, stroke, and
     * stroke width.
     */
    private void initColorHandler(){
        toolPane.setFillPickerAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if(selectedShape != null && toolPane.editBtnSelected()) {
                    if(selectedShape instanceof Path){
                       selectedShape.setFill(null);
                    }
                    else{
                        selectedShape.setFill(toolPane.getFillPickerValue());
                    }
                }
            }
        });
        toolPane.setStrokePickerAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(selectedShape != null && toolPane.editBtnSelected()){
                    selectedShape.setStroke(toolPane.getStrokePickerValue());
                }
            }
        });
        toolPane.setStrokeSizeAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(selectedShape != null && toolPane.editBtnSelected()){
                    selectedShape.setStrokeWidth(toolPane.getStrokeSizeValue());
                }
            }
        });
    }
}
