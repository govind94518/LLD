package com.shivaya.ATM.withDesign;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardDetails {
    private final main.java.com.shivaya.ATM.withDesign.CardType cardType;
    private final long cardNumber;
    private final int pin;
    private final String name;



}
