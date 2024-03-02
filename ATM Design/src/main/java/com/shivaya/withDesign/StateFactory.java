package com.shivaya.ATM.withDesign;

import com.shivaya.ATM.ATM;
import com.shivaya.ATM.withDesign.ATM;
import main.java.com.shivaya.ATM.withDesign.validation.ATMState;

public class StateFactory {
    public static main.java.com.shivaya.ATM.withDesign.State getState(ATMState atmState, ATM atm) {
         main.java.com.shivaya.ATM.withDesign.State state = null;
        if (atmState.equals(ATMState.READY)) return new   ReadyState(atm);
        if (atmState.equals(ATMState.CARD_READING_STATE)) return new CardReadingState(atm);
        if (atmState.equals(ATMState.WITHDRAWAL_DETAILS_READING)) return  new WithdrawalDetailsReadingState(atm);
        if (atmState.equals(ATMState.CASH_DISPENSING)) return new CardDispensingState(atm);
        if (atmState.equals(ATMState.CARD_EJECTING)) return new CardEjectingState(atm);
        throw new IllegalArgumentException("No state is provided");
    }
}
