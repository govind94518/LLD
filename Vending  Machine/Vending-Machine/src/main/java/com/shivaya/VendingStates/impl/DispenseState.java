package com.shivaya.VendingStates.impl;

import com.shivaya.VendingMachine;
import com.shivaya.VendingStates.Item;
import com.shivaya.VendingStates.State;

public class DispenseState implements State {
    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine,int codeNumber) throws Exception {
        this.vendingMachine = vendingMachine;
        System.out.println("Currently Vending machine is in DispenseState");
    }
    @Override
    public void clickOnInsertCoinButton() {

    }

    @Override
    public void clickOnSelectProductButton() {

    }

    @Override
    public void chooseProduct(int codeNumber) throws Exception {

    }

    @Override
    public void dispenseProduct(int codeNumber) throws Exception {
        System.out.println("Product has been dispensed");
        Item item =  vendingMachine.getInventory().getItem(codeNumber);
        vendingMachine.getInventory().updateSoldOutItem(codeNumber);
        vendingMachine.changeVendingMachineState(new IdleState(vendingMachine));
    }


    @Override
    public void refundFullMoney() {

    }

    @Override
    public int getChange() {
        return 0;
    }

    @Override
    public void updateInventory() throws Exception {

    }
}
