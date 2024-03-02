package com.shivaya;

import com.shivaya.VendingStates.Inventory;
import com.shivaya.VendingStates.Item;
import com.shivaya.VendingStates.State;
import com.shivaya.VendingStates.impl.IdleState;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class VendingMachine {
    private  State state;
    private  final List<Coin> coins;
    Inventory inventory;

    public VendingMachine() {
        this.state = new IdleState(this);
        coins = new ArrayList<>();
        this.inventory = new Inventory(10);
    }

    public void clickOnInsertCoinButton() throws Exception {
        state.clickOnInsertCoinButton();
    }

    public void insertCoins(Coin coin) {
        System.out.println("added coins "+coin.getValue());
        coins.add(coin);
    }


    public void clickOnSelectProductButton() throws Exception {
        state.clickOnSelectProductButton();
    }

    public void chooseProduct(int codeNumber) throws Exception {
        state.chooseProduct(codeNumber);
    }

    public void dispenseProduct(int codeNumber) throws Exception {
        state.dispenseProduct(codeNumber);
    }



    public  void changeVendingMachineState(State newState){
        this.state = newState;
    }
}
