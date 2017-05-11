package cs2410.assn7.model;

/**
 * Abstract class that holds a method to return an IQ
 * and an abstract method to get a Smarty's income.
 * @author Ember Fairbanks
 * @version 1.0
 */
public abstract class Smarty implements Simpleton, PersonType {
    /**
     * Integer to hold an IQ
     */
    public Integer iq;

    /**
     * Method to get the IQ of an Hourly or Contract worker
     * @return iq
     */
    public Integer getIQ(){
        return iq;
    }

    /**
     * Abstract method that returns the income from
     * Hourly and Contract workers.
     * @return Double income
     */
    public abstract Double getIncome();

}

