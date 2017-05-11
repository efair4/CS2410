package cs2410.assn7.control;

import java.util.ArrayList;
import cs2410.assn7.model.HourlyWorker;
import cs2410.assn7.model.ContractWorker;
import cs2410.assn7.model.Hobbit;
import cs2410.assn7.model.Simpleton;
import cs2410.assn7.model.PersonType;
import cs2410.assn7.model.Smarty;

/**
 *This class contains an ArrayList that holds the object instances
 * from each person. It communicates with the constructors from each
 * class and compiles the information needed to display when the user
 * chooses an option from the combo box.
 * @author Ember Fairbanks
 * @version 1.0
 */
public class Controller {
    /**
     * ArrayList to hold the different person objects
     */
    private ArrayList<Simpleton> people=new ArrayList<>();

    /**
     * Calls the ContractWorker constructor to create a new ContractWorker
     * and adds it to the ArrayList.
     * @param name
     * @param math
     * @param say
     * @param IQ
     * @param contracts
     * @param payPerCon
     */
    public void createNewConWorker(String name, String math, String say, Integer IQ, Integer contracts, Double payPerCon){
        people.add(new ContractWorker(name, math, say, IQ, contracts, payPerCon));
    }

    /**
     * Calls the HourlyWorker constructor to create a new HourlyWorker
     * and adds it to the ArrayList.
     * @param name
     * @param math
     * @param say
     * @param IQ
     * @param hours
     * @param wage
     */
    public void createNewHourWorker(String name, String math, String say, Integer IQ, Integer hours, Double wage){
        people.add(new HourlyWorker(name, math, say, IQ, hours, wage));
    }

    /**
     * Calls the Hobbit constructor to create a new Hobbit
     * and adds it to the ArrayList.
     * @param name
     * @param math
     * @param say
     * @param carrots
     */
    public void createNewHobbit(String name, String math, String say, Integer carrots){
        people.add(new Hobbit(name, math, say, carrots));
    }

    /**
     * Loops through the ArrayList and concatenates the information needed
     * for the Math people and returns that String.
     * @return mathList
     */
    public String getMathList(){
        String mathList="";
        for(Simpleton person: people){
            mathList+=person.getName()+", "+((PersonType)person).getPersonType()+": "+ person.doMath()+ "\n";
        }
        return mathList;
    }

    /**
     * Loops through the ArrayList and concatenates the information needed
     * for the Income people and returns that String.
     * @return incomeList
     */
    public String getIncomeList(){
        String incomeList="";
        for(Simpleton person: people){
            if(person instanceof Smarty){
                incomeList+=person.getName()+", "+((Smarty) person).getPersonType()+", Income: $"+((Smarty) person).getIncome()+"\n";
            }
        }
        return incomeList;
    }

    /**
     * Loops through the ArrayList and concatenates the information needed
     * for the Hours people and returns that String.
     * @return hoursList
     */
    public String getHoursList(){
        String hoursList="";
        for(Simpleton person: people){
            if(person instanceof HourlyWorker){
                hoursList+=person.getName()+", "+((HourlyWorker) person).getPersonType()+ ", Hours Worked: "+((HourlyWorker) person).getHoursWorked()+"\n";
            }
        }
        return hoursList;
    }

    /**
     * Loops through the ArrayList and concatenates the information needed
     * for the IQ people and returns that String.
     * @return IQList
     */
    public String getIQList(){
        String IQList="";
        for(Simpleton person: people){
            if(person instanceof Smarty){
                IQList+=person.getName()+", "+((Smarty)person).getPersonType()+", IQ: "+((Smarty)person).getIQ()+"\n";
            }
        }
        return IQList;
    }

    /**
     * Loops through the ArrayList and concatenates the information needed
     * for the Say people and returns that String.
     * @return sayList
     */
    public String getSayList(){
        String sayList="";
        for(Simpleton person: people){
            sayList+=person.getName()+", "+((PersonType)person).getPersonType()+", Statement: "+person.saySomethingSmart()+"\n";
        }
        return sayList;

    }

    /**
     * Loops through the ArrayList and concatenates the information needed
     * for the Carrots people and returns that String.
     * @return carrotsList
     */
    public String getCarrotsList() {
        String carrotsList = "";
        for (Simpleton person : people) {
            if (person instanceof Hobbit) {
                carrotsList += person.getName() + ", "+ ((Hobbit) person).getPersonType() +
                        ", Picked "+ ((Hobbit) person).getCarrotsPicked() + " carrots\n";
            }
        }
        return carrotsList;
    }

    /**
     * Loops through the ArrayList and concatenates the information needed
     * for the Contracts people and returns that String.
     * @return contractsList
     */
    public String getContractsList(){
        String contractsList="";
        for(Simpleton person: people){
            if(person instanceof ContractWorker){
                contractsList+=person.getName()+", "+((ContractWorker)person).getPersonType()+
                        ", Completed Contracts: "+((ContractWorker)person).getContractsCompleted()+"\n";
            }
        }
        return contractsList;
    }

}
