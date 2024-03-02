package main.java.com.shivaya.ATM.withDesign;


import com.shivaya.ATM.ATM;
import com.shivaya.ATM.withDesign.CardDetails;
import main.java.com.shivaya.ATM.withDesign.validation.ATMState;
import main.java.com.shivaya.ATM.withDesign.db.DBAccessor;


public class CardReadingState implements State {
    private final ATM atm;

    public CardReadingState( ATM atm) {
        this.atm = atm;
    }
    @Override
    public int init() {
        return 0;
    }

    @Override
    public boolean  cancelTransaction(int transId) {
        DBAccessor.cancelTransaction(transId);
        this.atm.changeState(new ReadyState(atm));
        return false;
    }

    @Override
    public boolean  readCard(CardDetails cardDetails) {
      boolean result =  main.java.com.shivaya.ATM.withDesign.CardManagerFactory
              .getCardManager(cardDetails.getCardType())
              .validateCard(cardDetails);
        DBAccessor.persistCardDetails(cardDetails, atm.getMachineId());
        if(result) atm.changeState(new WithdrawalDetailsReadingState(atm));
        else {
            DBAccessor.disapproveTransaction(atm.getMachineId());
            atm.changeState(new ReadyState(atm) );
        }
        return false;
    }

    @Override
    public boolean readWithDrawlDetails(CardDetails cardDetails, float amount, int transId) {
        return false;
    }

    @Override
    public float dispenseCash(int transId) {
        return 0.0f;
    }

    @Override
    public void ejectCard() {

    }
    @Override
    public ATMState getStateName() {
        return  ATMState.CARD_READING_STATE;
    }
}
