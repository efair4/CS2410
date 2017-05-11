package cs2410.assn4.controller;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;

/**
 * This is where methods are defined to show the next and
 * previous image and add or delete an image.
 *
 * @author Ember Fairbanks
 * @version 2.0
 */

public class Controller {
    /**
     * private instance of a Scanner
     */
    private Scanner scan;

    /**
     * private String to store the name of the .data file
     */
    private static final String fileName="data/images.data";

    /**
     * private ArrayList to store and manipulate the image URL's
     */
    private ArrayList<String> images=new ArrayList<String>();

    /**
     * Reads through the images.data file and stores the URL's in
     * the images ArrayList
     */
    public void readFile() {
        try {
            scan=new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scan.hasNext()){
            images.add(scan.next());
        }
    }

    /**
     * Returns the last index of the images ArrayList
     * @return images.size()-1
     */
    public int getMaxIndex(){
        if (images.isEmpty()){
            return -1;
        }
        else {
            return images.size() - 1;
        }
    }

    /**
     * Returns an Image using the ArrayList URL's depending
     * on the index sent
     * @return currentImage
     */
    public Image currentImage(int index){
        return new Image(images.get(index));
    }

    /**
     * Receives a String containing a URL that is then added to
     * the images ArrayList
     * @param url
     */
    public Image addImage(String url, int index) {
        images.add(index,url);
        return new Image(images.get(index));
    }

    /**
     * Receives an index of an image URL to be deleted from
     * the images ArrayList. If images.size() is equal to zero
     * it returns true, and false if images.size() is anything else.
     * @param index
     * @return a boolean value
     */
    public Image deleteImage(int index){
        images.remove(index);
        if(index==0&&images.isEmpty()){
            return new Image("file:data/no-image-large.jpg");
        }
        else if(index==0){
            index=images.size()-1;
            return new Image(images.get(index));
        }
        else {
            return new Image(images.get(index - 1));
        }
    }

    /**
     * This is called when the user closes the window.
     * It takes the current list of URL's in the images
     * ArrayList and saves them to images.data.
     */
    public void saveFile(){
        PrintWriter output=null;
        try {
            output=new PrintWriter(new FileOutputStream(fileName),true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0;i<images.size();i++){
            output.println(images.get(i));
        }
        output.close();
    }
}
