package cs2410.assn2;

/**
 *This class contains functions that
 * check to see if the value sent
 * is a multiple of either 3 or 5.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Fizzy {

    /**
     * counter will be used to iterate
     * through the numbers 1-100 inclusive
     * inside the main method.
     */
    private static int counter;

    /**
     * This function checks to see if
     * val is a multiple of 3 and returns
     * true or false.
     * @param val
     * @return true or false
     */
    private static boolean isFizz(int val){
        if(val%3==0) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This function checks to see if
     * val is a multiple of 5 and returns
     * true or false.
     * @param val
     * @return true or false
     */
    private static boolean isBuzz(int val){
        if(val%5==0){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String args[]){
        for(counter=1; counter<=100; counter++){
            if(isFizz(counter) && isBuzz(counter)) {
                System.out.println("FizzBuzz");
            }
            else if(isFizz(counter)){
                System.out.println("Fizz");
            }
            else if(isBuzz(counter)){
                System.out.println("Buzz");
            }
            else{
                System.out.println(counter);
            }

        }
    }
}


