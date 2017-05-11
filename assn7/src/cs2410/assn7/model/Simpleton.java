package cs2410.assn7.model;

/**
 * Interface that holds the methods getName(), doMath(),
 * and saySomethingSmart().
 * @author Ember Fairbanks
 * @version 1.0
 */
public interface Simpleton {
    /**
     * Method to get the name of a person.
     * @return a String
     */
    String getName();

    /**
     * Method to get the math problem that a person
     * can do.
     * @return a String
     */
    String doMath();

    /**
     * Method to get the statement from a person.
     * @return a String
     */
    String saySomethingSmart();
}
