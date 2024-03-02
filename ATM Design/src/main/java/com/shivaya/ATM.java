package com.shivaya.ATM;
import lombok.Getter;
import com.shivaya.ATM.withDesign.StateFactory;
import main.java.com.shivaya.ATM.withDesign.validation.ATMState;
import main.java.com.shivaya.ATM.withDesign.db.DBAccessor;


@Getter
public class ATM {
       main.java.com.shivaya.ATM.withDesign.State state;
    private final String machineId;

    public ATM(String machineId) {
        this.machineId = machineId;
      //  this.state = new ReadyState(this);
        this.state  = StateFactory.getState(DBAccessor.getATMState(this.machineId), this);
    }
    //s2-->init-->s4
    //s3-->init-->s5-->//        this.state = new ReadyState();-->will break


    public int init() {
        return this.state.init();
    }

    public boolean cancel(int transId) {

        return this.state.cancelTransaction(transId);
    }

    public void readCard(String cardType,
                         long cardNum, int pin, String name) {
        if (state.equals(ATMState.CARD_READING_STATE))
            throw new IllegalArgumentException("Old transaction is in card reading state");
        if (state.equals(ATMState.WITHDRAWAL_DETAILS_READING))
            throw new IllegalArgumentException("Old transaction is in WITHDRAWAL_DETAILS_READING");
        if (state.equals(ATMState.CASH_DISPENSING))
            throw new IllegalArgumentException("Old transaction is in card CASH_DISPENSING");
        if (state.equals(ATMState.CARD_EJECTING))
            throw new IllegalArgumentException("Old transaction is in card CARD_EJECTING");

    }

    public boolean readWithDrawlDetails(String cardType,
                                        long cardNum, int pin, String name,
                                        float amount, int transId) {
        if (state.equals(ATMState.CARD_READING_STATE))
            throw new IllegalArgumentException("Old transaction is in card reading state");
        if (state.equals(ATMState.WITHDRAWAL_DETAILS_READING))
            throw new IllegalArgumentException("Old transaction is in WITHDRAWAL_DETAILS_READING");
        if (state.equals(ATMState.CASH_DISPENSING))
            throw new IllegalArgumentException("Old transaction is in card CASH_DISPENSING");
        if (state.equals(ATMState.CARD_EJECTING))
            throw new IllegalArgumentException("Old transaction is in card CARD_EJECTING");

        return true;
    }

    boolean dispenseCash(int transId) {
        if (state.equals(ATMState.CARD_READING_STATE))
            throw new IllegalArgumentException("Old transaction is in card reading state");
        if (state.equals(ATMState.WITHDRAWAL_DETAILS_READING))
            throw new IllegalArgumentException("Old transaction is in WITHDRAWAL_DETAILS_READING");
        if (state.equals(ATMState.CASH_DISPENSING))
            throw new IllegalArgumentException("Old transaction is in card CASH_DISPENSING");
        if (state.equals(ATMState.CARD_EJECTING))
            throw new IllegalArgumentException("Old transaction is in card CARD_EJECTING");
        return true;
    }

    public void ejectCard() {
        if (state.equals(ATMState.CARD_READING_STATE))
            throw new IllegalArgumentException("Old transaction is in card reading state");
        if (state.equals(ATMState.WITHDRAWAL_DETAILS_READING))
            throw new IllegalArgumentException("Old transaction is in WITHDRAWAL_DETAILS_READING");
        if (state.equals(ATMState.CASH_DISPENSING))
            throw new IllegalArgumentException("Old transaction is in card CASH_DISPENSING");
        if (state.equals(ATMState.CARD_EJECTING))
            throw new IllegalArgumentException("Old transaction is in card CARD_EJECTING");

    }

    public void changeState( main.java.com.shivaya.ATM.withDesign.State newState) {
        this.state = newState;
        DBAccessor.updateATMState(getMachineId(),newState.getStateName());
    }

}
