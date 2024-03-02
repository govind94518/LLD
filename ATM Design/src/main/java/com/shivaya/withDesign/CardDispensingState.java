package main.java.com.shivaya.ATM.withDesign;




import com.shivaya.ATM.ATM;
import com.shivaya.ATM.withDesign.CardDetails;
import main.java.com.shivaya.ATM.withDesign.db.DBAccessor;


public class CardDispensingState implements State {
    private final ATM atm;

    public  CardDispensingState( ATM atm) {
        this.atm = atm;
    }

    @Override
    public int init() {
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }

    @Override
    public boolean cancelTransaction(int transId) {
        DBAccessor.cancelTransaction(transId);
        atm.changeState(new  CardEjectingState(atm));
        return true;
    }

    @Override
    public boolean  readCard(CardDetails cardDetails) {
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }
    public boolean readWithDrawlDetails(CardDetails cardDetails, float amount, int transId){
        throw  new IllegalArgumentException("Old transaction is in card reading state");
    }

    @Override
    public float dispenseCash(int transId) {
        CardType cardType = null;
        CardManagerFactory.getCardManager(cardType)
                .executeWithdrawal(transId);
        this.atm.changeState(new CardEjectingState(atm));
        return DBAccessor.markAsExecuted(transId);

    }

    @Override
    public void ejectCard() {

    }

    @Override
    public main.java.com.shivaya.ATM.WithoutDesign.ATMState getStateName() {
        return  main.java.com.shivaya.ATM.WithoutDesign.ATMState.CARD_READING_STATE;
    }
}
