package com.shivaya.VendingStates.impl;
import com.shivaya.VendingMachine;
import com.shivaya.VendingStates.State;
public class HasMoneyState implements State {

    private VendingMachine vendingMachine;

    public HasMoneyState (VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
        System.out.println("Currently Vending machine is in HasMoneyState");
    }

    @Override
    public void clickOnInsertCoinButton() {

        throw  new IllegalArgumentException("Currently Vending machine is in HasMoneyState");

    }

    @Override
    public void clickOnSelectProductButton() {
        this.vendingMachine.changeVendingMachineState(new ProductSelectionState(this.vendingMachine));

    }

    @Override
    public void chooseProduct(int codeNumber) {
        throw  new IllegalArgumentException("Currently Vending machine is in HasMoneyState");
    }

    @Override
    public void dispenseProduct(int codeNumber) throws Exception  {
        throw  new IllegalArgumentException("Currently Vending machine is in HasMoneyState");
    }

    @Override
    public void refundFullMoney() {
        throw  new IllegalArgumentException("Currently Vending machine is in HasMoneyState");
    }

    @Override
    public int getChange() {
        return 0;
    }

    @Override
    public void updateInventory() {
        throw  new IllegalArgumentException("Currently Vending machine is in HasMoneyState");
    }
}
