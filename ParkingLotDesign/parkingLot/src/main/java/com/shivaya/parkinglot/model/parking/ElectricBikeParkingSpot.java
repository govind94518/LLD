package com.shivaya.parkinglot.model.parking;

public class ElectricBikeParkingSpot extends ParkingSpot {
    public ElectricBikeParkingSpot(String id) {
        super(id, ParkingSpotType.EBIKE);
    }
}
