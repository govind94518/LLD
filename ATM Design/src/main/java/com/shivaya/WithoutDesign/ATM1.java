package main.java.com.shivaya.ATM.WithoutDesign;

import main.java.com.shivaya.ATM.withDesign.validation.ATMState;

public class ATM1 {
    private ATMState state;
    private final String machineId;

    public ATM1(String machineId) {
        this.machineId = machineId;
        this.state = ATMState.READY;
    }

    public int init() {
        if (state.equals(ATMState.CARD_READING_STATE))
            throw new IllegalArgumentException("Old transaction is in card reading state");
        if (state.equals(ATMState.WITHDRAWAL_DETAILS_READING))
            throw new IllegalArgumentException("Old transaction is in WITHDRAWAL_DETAILS_READING");
        if (state.equals(ATMState.CASH_DISPENSING))
            throw new IllegalArgumentException("Old transaction is in card CASH_DISPENSING");
        if (state.equals(ATMState.CARD_EJECTING))
            throw new IllegalArgumentException("Old transaction is in card CARD_EJECTING");
        int transId = 0;
        //algo to generate transId
        this.state = ATMState.CARD_READING_STATE;
        return transId;
    }

    public boolean cancel(int transId) {
        return true;
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
    boolean dispenseCash(int transId){
        if(state.equals(ATMState.CARD_READING_STATE))
            throw  new IllegalArgumentException("Old transaction is in card reading state");
        if(state.equals(ATMState. WITHDRAWAL_DETAILS_READING))
            throw  new IllegalArgumentException("Old transaction is in WITHDRAWAL_DETAILS_READING");
        if(state.equals(ATMState.CASH_DISPENSING))
            throw  new IllegalArgumentException("Old transaction is in card CASH_DISPENSING");
        if(state.equals(ATMState.CARD_EJECTING))
            throw  new IllegalArgumentException("Old transaction is in card CARD_EJECTING");
        return true;
    }

    public void ejectCard() {
        if(state.equals(ATMState.CARD_READING_STATE))
            throw  new IllegalArgumentException("Old transaction is in card reading state");
        if(state.equals(ATMState. WITHDRAWAL_DETAILS_READING))
            throw  new IllegalArgumentException("Old transaction is in WITHDRAWAL_DETAILS_READING");
        if(state.equals(ATMState.CASH_DISPENSING))
            throw  new IllegalArgumentException("Old transaction is in card CASH_DISPENSING");
        if(state.equals(ATMState.CARD_EJECTING))
            throw  new IllegalArgumentException("Old transaction is in card CARD_EJECTING");

    }
}
