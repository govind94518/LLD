package main.java.com.shivaya.ATM.withDesign;


import com.shivaya.ATM.withDesign.CardDetails;
import main.java.com.shivaya.ATM.withDesign.validation.ATMState;

public interface State {
    int init();
    boolean cancelTransaction(int transId);
    public boolean  readCard(CardDetails cardDetails) ;
    public boolean readWithDrawlDetails(CardDetails cardDetails, float amount, int transId) ;
    float dispenseCash(int transId);
    void ejectCard();
    ATMState getStateName();
}
