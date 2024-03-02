package main.java.com.shivaya.ATM.withDesign;

import com.shivaya.ATM.ATM;
import com.shivaya.ATM.withDesign.CardDetails;
import main.java.com.shivaya.ATM.withDesign.validation.ATMState;
import main.java.com.shivaya.ATM.withDesign.db.DBAccessor;


public class ReadyState implements State {
    private final ATM atm;


    public ReadyState( ATM atm) {
        this.atm = atm;
    }

    @Override
    public int init() {
        int transId = DBAccessor.createTransactionID(atm.getMachineId());
        if(transId==0)  throw new RuntimeException("Unable to start transaction");
        this.atm.changeState(new  CardReadingState(atm));
        return transId;
    }

    @Override
    public boolean cancelTransaction(int transId) {
        throw  new IllegalStateException("Currently in ready state, No Transaction in progress");
    }





    @Override
    public boolean  readCard( CardDetails cardDetails) {
       throw  new IllegalStateException("Currently in ready state, can't read card ");
    }

    @Override
    public   boolean readWithDrawlDetails( CardDetails cardDetails, float amount, int transId){
        return true;
    }

    @Override
    public float dispenseCash(int transId) {
        throw  new IllegalStateException("Currently in ready state,No Transaction in progress");
    }

    @Override
    public void ejectCard() {
        throw  new IllegalStateException("Currently in ready state,No Transaction in progress");
    }
    @Override
    public ATMState getStateName() {
        return  ATMState.READY;
    }
}
