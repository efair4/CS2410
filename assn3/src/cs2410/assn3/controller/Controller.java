package cs2410.assn3.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class contains variables and methods to be called by GUIDirectory and CommandDirectory.
 *
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Controller {

    /**
     * Private instance of Scanner used in listDirectory() and computeAverage().
     */
    private Scanner scan;
    /**
     * Private instance of PrintWriter used for writing to a file.
     */
    private PrintWriter output;
    /**
     * Private double used in the computeAverage() method.
     */
    private double average;
    /**
     * Private constant String that contains the name of the file where the directory
     * contents are stored.
     */
    private final static String fileName = "data/cs2410-directory.data";

    /**
     * Returns a string with the main menu text.
     * @return menu list
     */
    public String displayMenu(){
        String menu = ("What would you like to do?\n" +
                "1. List directory contents\n" +
                "2. Add student to directory\n" +
                "3. List average age\n" +
                "4. Quit program\n" +
                "Please enter a number 1-4\n");
        return menu;
    }

    /**
     * Scans through the data file and returns a string containing
     * the contents of the directory list along with labels.
     * @return totalString
     */
    public String listDirectory(){
        String interString = String.format("%-12s%-12s%5s%-12s","First Name", "Last Name", "Age" + "   ", "Phone Number\n");
        String totalString;
        totalString = interString;
        try {
            scan = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
        }
        while (scan.hasNext()) {
            for (int i = 0; i < 4; i++) {
                String tempStr = scan.next();
                if (i == 0) {
                    interString=String.format("%-12s", tempStr);
                    totalString+=interString;
                } else if (i == 1) {
                    interString=String.format("%-12s", tempStr);
                    totalString+=interString;
                } else if (i == 2) {
                    interString=String.format("%5s", tempStr + "    ");
                    totalString+=interString;
                } else {
                    interString=String.format("%-12s", tempStr +"\n");
                    totalString+=interString;
                }

            }

        }
        return totalString;
    }

    /**
     * Accepts three Strings and an int and appends them to the data
     * file with the correct formatting.
     * @param firstName
     * @param lastName
     * @param age
     * @param phoneNumber
     */
    public void addStudent(String firstName, String lastName, int age, String phoneNumber){
        try {
            output = new PrintWriter(new FileOutputStream(fileName,true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        output.println(firstName + " " + lastName + " " + age + " " + phoneNumber);
        output.close();
    }

    /**
     * Computes the average age of students by reading through the data file
     * and collecting only the ages. This method returns a double.
     * @return average age
     */
    public double computeAverage(){
        double total=0;
        int count=0;
        try {
            scan = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
        }
        while(scan.hasNext()){
            for(int i=0;i<4;i++){
                if(i==0||i==1||i==3){
                    String junk = scan.next();
                }
                if(i==2){
                    total+=scan.nextDouble();
                }
            }
            count ++;
        }
        if(count==0){
            average=0;
        }
        average=total/count;
        return average;
    }
}
