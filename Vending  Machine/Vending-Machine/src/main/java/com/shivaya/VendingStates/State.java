package com.shivaya.VendingStates;

import com.shivaya.VendingMachine;

public interface State {
    void clickOnInsertCoinButton() throws Exception;


    void clickOnSelectProductButton() throws Exception;

    void chooseProduct(int codeNumber) throws Exception;



    void refundFullMoney();
    public void dispenseProduct(int codeNumber) throws Exception;

    int getChange();

    public void updateInventory() throws Exception;
}
