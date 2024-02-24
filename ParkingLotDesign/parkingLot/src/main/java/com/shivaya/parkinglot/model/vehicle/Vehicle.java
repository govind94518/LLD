package com.shivaya.parkinglot.model.vehicle;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    private String licenseNumber;
    private final VehicleType vehicleType;
    private  ParkingTicket ticket;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }
}
