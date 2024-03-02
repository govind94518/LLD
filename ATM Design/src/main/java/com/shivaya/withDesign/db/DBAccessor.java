package main.java.com.shivaya.ATM.withDesign.db;


import main.java.com.shivaya.ATM.withDesign.validation.ATMState;

import com.shivaya.ATM.withDesign.CardDetails;
import main.java.com.shivaya.ATM.withDesign.TransactionStatus;

public class DBAccessor {

    public static ATMState getATMState(String machineId) {
        return ATMState.READY;
    }
    public static int createTransactionID(String machineId) {
        return 1;
    }

    public static void updateATMState(String machineId,ATMState atmState) {

    }
    public static void persistCardDetails(CardDetails cardDetails , String machineId){

    }
    public static void disapproveTransaction(String transId){

    }
    public static void  cancelTransaction(int transId) {

    }
    public static void persistWithDetails(int transId, Float amount, TransactionStatus transactionStatus) {

    }
    public static float markAsExecuted(int transId) {
    return 0.0F;
    }

}
