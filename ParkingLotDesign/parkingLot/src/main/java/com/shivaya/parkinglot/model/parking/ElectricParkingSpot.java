package com.shivaya.parkinglot.model.parking;

public class ElectricParkingSpot extends ParkingSpot {
    public ElectricParkingSpot(String id) {
        super(id, ParkingSpotType.ELECTRIC);
    }
}
