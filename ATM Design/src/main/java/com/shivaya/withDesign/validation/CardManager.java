package main.java.com.shivaya.ATM.withDesign.validation;

import com.shivaya.ATM.withDesign.CardDetails;

public interface CardManager {
    boolean validateCard(CardDetails cardDetails);
    boolean validateWithdrawal( float amount ,
                                int transId);
    void  executeWithdrawal( int transId);




}
