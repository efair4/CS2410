package cs2410.assn8.view;

import javafx.scene.control.Button;

/**
 * Sets the characteristics for each Cell.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Cell extends Button {
    /**
     * Integer to hold the number of bombs surrounding
     * a cell.
     */
    private Integer bombCount=0;

    /**
     * boolean to tell if the cell is a bomb.
     */
    private boolean isBomb=false;

    /**
     * boolean to tell if a cell has been flagged as
     * a bomb.
     */
    private boolean isFlagged=false;

    /**
     * boolean to tell if a cell is a possible bomb.
     */
    private boolean isQuestion=false;

    /**
     * boolean to tell if a cell has already been clicked.
     */
    private boolean isPressed=false;

    /**
     * Integer to hold the cell's x position.
     */
    public Integer xPos;

    /**
     * Integer to hold the cell's y position.
     */
    public Integer yPos;

    /**
     * Constructor for the Cell class. Sets the size of each
     * cell and adds the stylesheet.
     */
    Cell() {
        this.setPrefWidth(35);
        this.setPrefHeight(35);
        this.getStylesheets().addAll("/resource/style.css");
    }

    /**
     * Sets the isBomb variable to true.
     */
    void setBomb(){
        isBomb=true;
    }

    /**
     * Returns the value of the isBomb variable.
     * @return isBomb
     */
    public boolean getBomb(){
        return isBomb;
    }

    /**
     * Sets isFlagged to true and isQuestion to false.
     * The Id of the cell is set to "flagged" and text to "!".
     */
    public void setFlagged(){
        isFlagged=true;
        isQuestion=false;
        setId("flagged");
        setText("!");
    }

    /**
     * Returns the value of the isFlagged variable.
     * @return isFlagged
     */
    public boolean getFlagged(){
        return isFlagged;
    }

    /**
     * Sets isQuestion to true and isFlagged to false.
     * The Id of the cell is set to "question" and text to "?".
     */
    public void setQuestion(){
        isQuestion=true;
        isFlagged=false;
        setId("question");
        setText("?");
    }

    /**
     * Returns the value of the isQuestion variable.
     * @return isQuestion
     */
    public boolean getQuestion(){
        return isQuestion;
    }

    /**
     * Sets isFlagged to false and isQuestion to false.
     * The Id of the cell is set to "default" and text to "".
     */
    public void setDefault(){
        isFlagged=false;
        isQuestion=false;
        setId("default");
        setText("");
    }

    /**
     * Sets the value of bombCount to the sent Integer value.
     * @param count
     */
    void setBombCount(Integer count){
        bombCount=count;
    }

    /**
     * Returns the value of bombCount.
     * @return bombCount
     */
    public Integer getBombCount(){
        return bombCount;
    }

    /**
     * Sets the value of the isPressed variable to true.
     */
    public void setPressed(){
        isPressed=true;
    }

    /**
     * Returns the value of the isPressed variable.
     * @return isPressed
     */
    public boolean getPressed(){
        return isPressed;
    }

    /**
     * Sets the xPos and yPos values of a cell to the Integers sent.
     * @param x
     * @param y
     */
    void setXY(Integer x, Integer y){
        xPos=x;
        yPos=y;
    }

    /**
     * Sets the text of a cell to a certain number based on the
     * number of bombs around that cell and sets the Id to "cleared".
     * @param bomb
     */
    public void setNumber(Integer bomb){
        setPressed();
        switch(bomb){
            case 0:
                setText("0");
                setId("cleared");
                break;
            case 1:
                setText("1");
                setId("cleared");
                break;
            case 2:
                this.setText("2");
                this.setId("cleared");
                break;
            case 3:
                this.setText("3");
                this.setId("cleared");
                break;
            case 4:
                this.setText("4");
                this.setId("cleared");
                break;
            case 5:
                this.setText("5");
                this.setId("cleared");
                break;
            case 6:
                this.setText("6");
                this.setId("cleared");
                break;
            case 7:
                this.setText("7");
                this.setId("cleared");
                break;
            case 8:
                this.setText("8");
                this.setId("cleared");
                break;
        }
    }
}
