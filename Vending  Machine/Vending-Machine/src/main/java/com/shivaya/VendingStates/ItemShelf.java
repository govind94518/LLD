package com.shivaya.VendingStates;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ItemShelf {

    int code;
    Item item;
    boolean soldOut;


}
