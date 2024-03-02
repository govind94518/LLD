package main.java.com.shivaya.ATM.withDesign;


import com.shivaya.ATM.ATM;
import com.shivaya.ATM.withDesign.CardDetails;
import main.java.com.shivaya.ATM.withDesign.validation.ATMState;
import main.java.com.shivaya.ATM.withDesign.db.DBAccessor;


public class WithdrawalDetailsReadingState implements State {
    private final ATM atm;

    public WithdrawalDetailsReadingState( ATM atm) {
        this.atm = atm;
    }
    @Override
    public int init() {
        throw  new IllegalArgumentException("Old transaction is in card reading state");

    }

    @Override
    public boolean  cancelTransaction(int transId) {
        DBAccessor.cancelTransaction(transId);
        this.atm.changeState(new  ReadyState(atm));
        return  true;
    }

    @Override
    public boolean  readCard(CardDetails cardDetails) {
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }

    @Override
    public   boolean readWithDrawlDetails(CardDetails cardDetails, float amount, int transId) {
        boolean result =  CardManagerFactory
                .getCardManager(cardDetails.getCardType())
                .validateWithdrawal (amount,transId);
        DBAccessor.persistCardDetails(cardDetails, atm.getMachineId());
        if(result) {
            atm.changeState(new CardDispensingState(atm));
            DBAccessor.persistWithDetails(transId,amount, TransactionStatus.APPROVED);
        }else{
            atm.changeState(new CardEjectingState(atm));
            DBAccessor.persistWithDetails(transId,amount,TransactionStatus.NOT_APPROVED);
        }
        return result;
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
        return ATMState.WITHDRAWAL_DETAILS_READING;
    }
}
