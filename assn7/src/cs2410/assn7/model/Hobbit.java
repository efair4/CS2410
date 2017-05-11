package cs2410.assn7.model;

/**
 * Defines implemented methods as well as the getCarrotsPicked method.
 * Contains a constructor to create a new Hobbit object.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Hobbit implements Simpleton, PersonType {
    /**
     * /**
     * String to contain the name of a Hobbit
     */
    private String m_name;
    /**
     * String to contain the math problem of a Hobbit
     */
    private String m_math;
    /**
     * String to contain the statement of a Hobbit
     */
    private String m_say;
    /**
     * Integer to contain the number of carrots picked by a Hobbit
     */
    private Integer m_carrots;

    /**
     * Constructor to create a new Hobbit
     * @param name
     * @param math
     * @param say
     * @param carrots
     */
    public Hobbit(String name, String math, String say, Integer carrots){
        this.m_name=name;
        this.m_math=math;
        this.m_say=say;
        this.m_carrots=carrots;
    }
    @Override
    public String getName(){
        return m_name;
    }

    @Override
    public String doMath(){
        return m_math;
    }

    @Override
    public String saySomethingSmart(){
        return m_say;
    }

    @Override
    public String getPersonType() {
        return "Hobbit";
    }

    /**
     * Method to get the number of carrots picked
     * @return m_carrots
     */
    public Integer getCarrotsPicked(){
        return m_carrots;
    }

}
