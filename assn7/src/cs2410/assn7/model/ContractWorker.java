package cs2410.assn7.model;

/**
 * Defines implemented methods as well as the getContractsCompleted method.
 * Contains a constructor to create a new ContractWorker object.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class ContractWorker extends Smarty implements Simpleton{
    /**
     * String to contain the name of a ContractWorker
     */
    private String m_name;
    /**
     * String to contain the math problem of a ContractWorker
     */
    private String m_math;
    /**
     * String to contain the statement of a ContractWorker
     */
    private String m_say;
    /**
     * Integer to contain the number of contracts of a ContractWorker
     */
    private Integer m_contracts;
    /**
     * Double to contain the payment per contract of a ContractWorker
     */
    private Double m_payPerCon;

    /**
     * Constructor to create a new ContractWorker
     * @param name
     * @param math
     * @param say
     * @param IQ
     * @param contracts
     * @param payPerCon
     */
    public ContractWorker(String name, String math, String say,Integer IQ, Integer contracts, Double payPerCon){
        this.iq=IQ;
        this.m_name=name;
        this.m_math=math;
        this.m_say=say;
        this.m_contracts=contracts;
        this.m_payPerCon=payPerCon;
    }
    @Override
    public Double getIncome() {
        return m_contracts*m_payPerCon;
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
        return "Contract Worker";
    }

    /**
     * Method to get the number of contracts completed.
     * @return m_contracts
     */
    public Integer getContractsCompleted(){
        return m_contracts;
    }
}
