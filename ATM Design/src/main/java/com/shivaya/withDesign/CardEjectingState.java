package main.java.com.shivaya.ATM.withDesign;


import com.shivaya.ATM.ATM;
import com.shivaya.ATM.withDesign.CardDetails;
import main.java.com.shivaya.ATM.withDesign.validation.ATMState;

public class CardEjectingState implements State {
    private final ATM atm;

    public CardEjectingState(ATM atm) {
        this.atm = atm;
    }
    @Override
    public int init() {
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }

    @Override
    public boolean  cancelTransaction(int transId) {
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }

    @Override
    public boolean  readCard(CardDetails cardDetails) {
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }

    @Override
   public boolean readWithDrawlDetails(CardDetails cardDetails, float amount, int transId){
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }

    @Override
    public float dispenseCash(int transId) {
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }

    @Override
    public void ejectCard() {
        atm.changeState(new ReadyState(atm));
    }
    @Override
    public ATMState getStateName() {
        return  ATMState.CARD_EJECTING;
    }
}
