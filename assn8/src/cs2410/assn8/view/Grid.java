package cs2410.assn8.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Class to set up the grid of Cell objects.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Grid extends GridPane {
    /**
     * Integer to hold the number of rows and columns wanted
     * for the grid.
     */
    public Integer gridSize = 20;

    /**
     * int to hold the total number of Cells in the grid.
     */
    private int totalGrid = gridSize * gridSize;

    /**
     * double to hold the percentage of bombs wanted in the grid.
     */
    private double percentBombs = .10;

    /**
     * double to hold the number of  bomb cells to add to the grid.
     */
    private double numBombs = gridSize * gridSize * percentBombs;

    /**
     * double to hold the number of cleared cells it takes to win
     * the game.
     */
    public double clearedToWin=totalGrid-numBombs;

    /**
     * ArrayList to hold all of the Cell objects.
     */
    private ArrayList<Cell> gridList = new ArrayList<>();

    /**
     * 2D array of Cells to access the Cell objects.
     */
    private Cell[][] grid=new Cell[gridSize][gridSize];

    /**
     * Constructor for the Grid class. Sets the alignment, sends the
     * EventHandler to the setGrid() method, calls the updateBombs()
     * method and sets some style attributes.
     * @param event
     */
    public Grid(EventHandler<MouseEvent> event){
        this.setAlignment(Pos.CENTER);
        setGrid(event);
        updateBombs();
        setStyle("-fx-border-width: 3px;"+ "-fx-border-color: black;");
    }

    /**
     * Adds Cells objects to the ArrayList and to the 2D array.
     * A portion of the Cells are set as bombs using the setBomb()
     * method.
     * @param event
     */
    private void setGrid(EventHandler<MouseEvent> event) {
        Cell cell;
        for (int i = 0; i < totalGrid; i++) {
            if (numBombs > 0) {
                cell = new Cell();
                cell.setBomb();
                cell.setDefault();
                cell.setOnMousePressed(event);
                gridList.add(cell);
                numBombs--;
            } else {
                cell = new Cell();
                cell.setDefault();
                cell.setOnMousePressed(event);
                gridList.add(cell);
            }
        }
        Collections.shuffle(gridList);
        int index = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                    grid[i][j] = gridList.get(index);
                    this.add(gridList.get(index),i,j);
                gridList.get(index).xPos = i;
                gridList.get(index).yPos = j;
                    grid[i][j].setXY(i, j);
                    index++;
            }
        }
    }

    /**
     * Iterates through the ArrayList of Cells and calls the
     * setBombCount() method on each of them using the countBombs()
     * method.
     */
    private void updateBombs() {
        Iterator<Cell> iterator=gridList.iterator();
        Cell cell;
        while(iterator.hasNext()){
            cell=iterator.next();
            cell.setBombCount(countBombs(cell.xPos, cell.yPos));
        }
    }

    /**
     * Counts the number of surrounding bombs and returns that number.
     * @param x
     * @param y
     * @return
     */
    private int countBombs(int x, int y) {
        int bombs = 0;
        for (int i = x - 1; i <= x + 1; ++i)
            for (int j = y - 1; j <= y + 1; ++j)
                if (i >= 0 && i < gridSize && j >= 0 && j < gridSize && (i != x || j != y) && grid[i][j].getBomb())
                    ++bombs;
        return bombs;
    }

    /**
     * Returns the grid that has been set.
     * @return grid
     */
    public Cell[][] getGrid(){
        return grid;
    }
}
