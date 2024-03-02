package com.shivaya.VendingStates.impl;

import com.shivaya.Coin;
import com.shivaya.VendingMachine;
import com.shivaya.VendingStates.Item;
import com.shivaya.VendingStates.State;

public class ProductSelectionState implements State {

    private VendingMachine vendingMachine;

    public ProductSelectionState  (VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
        System.out.println("Currently Vending machine is in ProductSelectionState ");
    }

    @Override
    public void clickOnInsertCoinButton() throws Exception {
        throw new Exception("insert coin button can not clicked on Dispense state");
    }


    @Override
    public void clickOnSelectProductButton() throws Exception {
        throw new Exception("insert coin button can not clicked on SelectProductButton state");

    }

    @Override
    public void chooseProduct(int codeNumber) throws Exception {
        //1. get item of this codeNumber
        Item item =  vendingMachine.getInventory().getItem(codeNumber);

        //2. total amount paid by User
        int paidByUser = 0;
        for(Coin coin :vendingMachine.getCoins()){
            paidByUser = paidByUser + coin.value;
        }

        //3. compare product price and amount paid by user
        if(paidByUser < item.getPrice()) {
            System.out.println("Insufficient Amount, Product you selected is for price: " + item.getPrice() + " and you paid: " + paidByUser);
            refundFullMoney();
            throw new Exception("insufficient amount");
        }
        else if(paidByUser >= item.getPrice()) {

            if(paidByUser > item.getPrice()) {
                getChange(paidByUser-item.getPrice());
            }
            vendingMachine.changeVendingMachineState(new DispenseState(vendingMachine,codeNumber));

        }

    }

    @Override
    public void dispenseProduct(int codeNumber) throws Exception  {
        throw new Exception("product can not be dispensed Selection state");
    }

    @Override
    public int getChange() {
        return 0;
    }

    @Override
    public void refundFullMoney( )  {
        System.out.println("Returned the full amount back in the Coin Dispense Tray");
        vendingMachine.changeVendingMachineState(new IdleState(vendingMachine));
    }


    @Override
    public void updateInventory() throws Exception {
        throw new Exception("product can not be dispensed updateInventory state");
    }
    public void getChange(int returnExtraMoney) throws Exception{
        //actual logic should be to return COINs in the dispense tray, but for simplicity i am just returning the amount to be refunded
        System.out.println("Returned the change in the Coin Dispense Tray: " + returnExtraMoney);
    }



}
