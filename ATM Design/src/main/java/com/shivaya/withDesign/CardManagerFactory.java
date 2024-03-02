package main.java.com.shivaya.ATM.withDesign;

import main.java.com.shivaya.ATM.withDesign.validation.CardManager;
import main.java.com.shivaya.ATM.withDesign.validation.CreditCardManager;
import main.java.com.shivaya.ATM.withDesign.validation.DebitCardManager;

public class CardManagerFactory {
    private  CardManagerFactory(){}
    public  static CardManager getCardManager(CardType cardType){
        CardManager cardManager = null;
        if(cardType.equals(CardType.CREDIT))
            cardManager = new CreditCardManager();
        else if(cardType.equals(CardType.DEBIT))
            cardManager = new DebitCardManager();
        return cardManager;

    }
}
