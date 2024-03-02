package com.shivaya;

import com.shivaya.VendingStates.Item;
import com.shivaya.VendingStates.ItemShelf;
import com.shivaya.VendingStates.ItemType;
import com.shivaya.VendingStates.State;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class VendingMachineMainApplication {



		public static void main(String args[]){

			VendingMachine vendingMachine = new VendingMachine();
			try {

				System.out.println("|");
				System.out.println("filling up the inventory");
				System.out.println("|");

				fillUpInventory(vendingMachine);
				displayInventory(vendingMachine);

				System.out.println("|");
				System.out.println("clicking on InsertCoinButton");
				System.out.println("|");

				vendingMachine.clickOnInsertCoinButton();


				vendingMachine.insertCoins(Coin.NICKEL);
				vendingMachine.insertCoins(Coin.QUARTER);
				// vendingState.insertCoin(vendingMachine, Coin.NICKEL);

				System.out.println("|");
				System.out.println("clicking on ProductSelectionButton");
				System.out.println("|");
				vendingMachine.clickOnSelectProductButton();
				vendingMachine.chooseProduct( 102);
				vendingMachine.dispenseProduct( 102);

				displayInventory(vendingMachine);

			}
			catch (Exception e){
				displayInventory(vendingMachine);
			}


		}

		private static void fillUpInventory(VendingMachine vendingMachine){
			ItemShelf[] slots = vendingMachine.getInventory().getInventory();
			for (int i = 0; i < slots.length; i++) {
				Item newItem = new Item();
				if(i >=0 && i<3) {
					newItem.setType(ItemType.COKE);
					newItem.setPrice(12);
				}else if(i >=3 && i<5){
					newItem.setType(ItemType.PEPSI);
					newItem.setPrice(9);
				}else if(i >=5 && i<7){
					newItem.setType(ItemType.JUICE);
					newItem.setPrice(13);
				}else if(i >=7 && i<10){
					newItem.setType(ItemType.SODA);
					newItem.setPrice(7);
				}
				slots[i].setItem(newItem);
				slots[i].setSoldOut(false);
			}
		}

		private static void displayInventory(VendingMachine vendingMachine){

			ItemShelf[] slots = vendingMachine.getInventory().getInventory();
			for (int i = 0; i < slots.length; i++) {

				System.out.println("CodeNumber: " + slots[i].getCode() +
						" Item: " + slots[i].getItem().getType().name() +
						" Price: " + slots[i].getItem().getPrice() +
						" isAvailable: " + !slots[i].isSoldOut());
			}
		}
	}


