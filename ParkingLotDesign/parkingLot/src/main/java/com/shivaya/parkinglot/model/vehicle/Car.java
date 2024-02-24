package com.shivaya.parkinglot.model.vehicle;

import lombok.Getter;

@Getter
public class Car extends Vehicle{
    public Car(String license){
        super(license,VehicleType.CAR);
    }
}
