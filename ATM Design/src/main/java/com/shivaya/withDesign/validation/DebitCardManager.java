package main.java.com.shivaya.ATM.withDesign.validation;

import com.shivaya.ATM.withDesign.CardDetails;

public class DebitCardManager implements CardManager{
    @Override
    public boolean validateCard(CardDetails cardDetails) {
        return false;
    }

    @Override
    public boolean validateWithdrawal(float amount, int transId) {
        return false;
    }

    @Override
    public void executeWithdrawal(int transId) {

    }
}
