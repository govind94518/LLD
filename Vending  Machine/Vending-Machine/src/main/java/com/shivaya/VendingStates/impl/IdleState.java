package com.shivaya.VendingStates.impl;

import com.shivaya.VendingMachine;
import com.shivaya.VendingStates.State;

public class IdleState implements State {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
        System.out.println("Currently Vending machine is in IdleState");
    }

    @Override
    public void clickOnInsertCoinButton() {
        this.vendingMachine.changeVendingMachineState(new HasMoneyState(this.vendingMachine));

    }

    @Override
    public void clickOnSelectProductButton() {
        throw  new IllegalArgumentException("Currently Vending machine is in IdleState");
    }

    @Override
    public void chooseProduct(int codeNumber) {
        throw  new IllegalArgumentException("Currently Vending machine is in IdleState");
    }

    @Override
    public void dispenseProduct(int codeNumber) throws Exception  {
        throw  new IllegalArgumentException("Currently Vending machine is in IdleState");
    }

    @Override
    public void refundFullMoney() {
        throw  new IllegalArgumentException("Currently Vending machine is in IdleState");
    }

    @Override
    public int getChange() {
        return 0;
    }

    @Override
    public void updateInventory() {
        throw  new IllegalArgumentException("Currently Vending machine is in IdleState");
    }
}
