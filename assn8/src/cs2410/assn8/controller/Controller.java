package cs2410.assn8.controller;

import cs2410.assn8.view.Cell;
import cs2410.assn8.view.Grid;
import cs2410.assn8.view.Scoreboard;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controls game play and initiates the other classes.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Controller extends BorderPane{
    /**
     * EventHandler that is sent to the Grid class. Each cell that
     * is clicked will then call the clickCell method.
     */
    private EventHandler<MouseEvent> mouseEvent = event -> clickCell(event.getButton(), (Cell)event.getSource());

    /**
     * Integer to hold the number of cleared bombs.
     */
    private Integer cleared;

    /**
     * Instance of the Grid class.
     */
    private Grid grid;

    /**
     * Instance of the Scoreboard class.
     */
    private Scoreboard scoreboard;

    /**
     * Instance of Timer.
     */
    private Timer timer;

    /**
     * 2D array of Cells to access individual Cells.
     */
    private Cell cells[][]=new Cell[20][20];

    /**
     * Constructor for the Controller class that sets the
     * stylesheet and calls the function to initialize the game.
     */
    public Controller(){
        initControl();
        this.getStylesheets().addAll("/resource/style.css");
    }

    /**
     * This method initializes the instances of the above
     * classes and sets up the layout for the GUI.
     */
    private void initControl(){
        cleared=0;
        scoreboard=new Scoreboard();
        timer=new Timer();
        grid=new Grid(mouseEvent);
        cells=grid.getGrid();
        setTop(scoreboard);
        setCenter(grid);
        startButtonAction();
    }

    /**
     * Starts the timer when called and calls the updateTime
     * method from the Scoreboard class to keep the displayed
     * time value updated.
     */
    private void startTimer(){
        scoreboard.disableStart(true);
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        scoreboard.updateTime();
                    }
                });
            }
        },0,1000);
    }

    /**
     * Cancels the timer when called.
     */
    public void endTimer(){
        timer.cancel();
    }

    /**
     * Calls initControl() when the start button is pressed to
     * start a new game.
     */
    private void startButtonAction(){
        scoreboard.setStartAction(event->initControl());
    }

    /**
     * Controls what happens when a cell is clicked. Depending on the
     * state of that cell (whether it was a bomb, or flagged, or questioned)
     * different methods are called.
     * @param button
     * @param cell
     */
    private void clickCell(MouseButton button, Cell cell){
        if(cleared==0 && button==MouseButton.PRIMARY){
            startTimer();
        }
        if(!cell.getPressed()) {
            if (button == MouseButton.SECONDARY) {
                if (!cell.getFlagged() && !cell.getQuestion()) {
                    cell.setFlagged();
                    scoreboard.decrementBombs();
                }
                else if (cell.getFlagged()) {
                    cell.setQuestion();
                }
                else if (cell.getQuestion()) {
                    cell.setDefault();
                    scoreboard.incrementBombs();
                }
            }
            if (button == MouseButton.PRIMARY) {
                if (cell.getBomb()) {
                    loseGame();
                }
                if (!cell.getBomb() && !cell.getFlagged() && !cell.getQuestion()) {
                    cell.setNumber(cell.getBombCount());
                    ++cleared;
                    winCondition();
                    showCells(cell.xPos, cell.yPos);
                }
            }
        }
    }

    /**
     * Reveals the cells that have a bombCount of 0.
     * @param x
     * @param y
     */
    private void showCells(Integer x, Integer y){
        if(cells[x][y].getBombCount()!=0){
            return;
        }
        if (x >= 0 && x < grid.gridSize-1 && y >= 0 && y < grid.gridSize && !cells[x + 1][y].getBomb())
            clickCell(MouseButton.PRIMARY, cells[x + 1][y]);

        if (x > 0 && x < grid.gridSize && y >= 0 && y < grid.gridSize && !cells[x - 1][y].getBomb())
            clickCell(MouseButton.PRIMARY, cells[x - 1][y]);

        if (x >= 0 && x < grid.gridSize && y >= 0 && y < grid.gridSize-1 && !cells[x][y + 1].getBomb())
            clickCell(MouseButton.PRIMARY, cells[x][y + 1]);

        if (x >= 0 && x < grid.gridSize && y > 0 && y < grid.gridSize && !cells[x][y - 1].getBomb())
            clickCell(MouseButton.PRIMARY, cells[x][y - 1]);
    }

    /**
     * Controls what happens when a player clears all the non-bomb
     * cells. The bomb cells are revealed, the timer is stopped, the
     * start button is enabled, and an Alert is shown to tell the player
     * they won with their time.
     */
    private void winGame(){
        endTimer();
        for(int i=0; i < grid.gridSize; i++) {
            for (int j = 0; j < grid.gridSize; j++) {
                if (cells[i][j].getBomb()) {
                    cells[i][j].setId("winBomb");
                    cells[i][j].setText("X");
                }
            }
        }
        Image image1 = new Image("file:data/unicorn.jpg", 65,65,true,true);
        ImageView imageView1=new ImageView();
        imageView1.setImage(image1);
        Alert winAlert=new Alert(Alert.AlertType.INFORMATION);
        winAlert.setTitle("You Win!");
        winAlert.setHeaderText(null);
        winAlert.setGraphic(imageView1);
        winAlert.setContentText("You cleared all the bombs! Your time was "+(scoreboard.getTimeElapsed()-1)+" seconds");
        winAlert.showAndWait();
        scoreboard.disableStart(false);
    }

    /**
     * Checks to see if the required amount of bombs have
     * been cleared. If so, winGame() is called.
     */
    private void winCondition(){
        if(cleared==(int)grid.clearedToWin){
            winGame();
        }
    }

    /**
     * Controls what happens when a player clicks a bomb. Bomb cells are revealed,
     * the timer is stopped, the start button is enabled, and an Alert is shown to tell the player
     * they lost.
     */
    private void loseGame() {
        endTimer();
        for(int i=0; i < grid.gridSize; i++){
            for(int j=0; j < grid.gridSize; j++){
                cells[i][j].setPressed();
                if(!cells[i][j].getBomb() && cells[i][j].getFlagged()|| cells[i][j].getQuestion()){
                    cells[i][j].setId("wrongBomb");
                }
                else if(cells[i][j].getBomb() && !cells[i][j].getFlagged()&& !cells[i][j].getQuestion()){
                    cells[i][j].setId("unmarkedBomb");
                    cells[i][j].setText("X");
                }
                else if(cells[i][j].getBomb() && cells[i][j].getQuestion()|| cells[i][j].getFlagged()){
                    cells[i][j].setId("winBomb");
                }
            }
        }
        Image image1 = new Image("file:data/sad-unicorn.jpg", 65,65,true,true);
        ImageView imageView1=new ImageView();
        imageView1.setImage(image1);
        Alert loseAlert=new Alert(Alert.AlertType.INFORMATION);
        loseAlert.setTitle("Game over");
        loseAlert.setHeaderText(null);
        loseAlert.setGraphic(imageView1);
        loseAlert.setContentText("You hit a bomb!");
        loseAlert.showAndWait();
        scoreboard.disableStart(false);
    }
}
