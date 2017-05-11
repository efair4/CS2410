package cs2410.assn7.model;

/**
 * Defines implemented methods as well as the getHoursWorked method.
 * Contains a constructor to create a new HourlyWorker object.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class HourlyWorker extends Smarty implements Simpleton {
    /**
     * String to contain the name of an HourlyWorker
     */
    private String m_name;
    /**
     * String to contain the math problem of an HourlyWorker
     */
    private String m_math;
    /**
     * String to contain the statement of an HourlyWorker
     */
    private String m_say;
    /**
     * Integer to contain the hours worked of an HourlyWorker
     */
    private Integer m_hours;
    /**
     * Double to contain the wage of an HourlyWorker
     */
    private Double m_wage;

    /**
     * Constructor to create a new HourlyWorker.
     * @param name
     * @param math
     * @param say
     * @param IQ
     * @param hours
     * @param wage
     */
    public HourlyWorker(String name, String math, String say, Integer IQ, Integer hours, Double wage){
        this.iq=IQ;
        this.m_name=name;
        this.m_math=math;
        this.m_say=say;
        this.m_hours=hours;
        this.m_wage=wage;
    }
    @Override
    public Double getIncome() {
        return m_hours*m_wage;
    }
    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public String doMath() {
        return m_math;
    }

    @Override
    public String saySomethingSmart() {
        return m_say;
    }

    @Override
    public String getPersonType() {
        return "Hourly Worker";
    }

    /**
     * Method to get the hours worked
     * @return m_hours
     */
    public Integer getHoursWorked(){
        return m_hours;
    }

}
