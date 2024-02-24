package com.shivaya.parkinglot.services;

import com.shivaya.parkinglot.model.parking.ParkingFloor;
import com.shivaya.parkinglot.model.parking.ParkingSpot;
import com.shivaya.parkinglot.model.parking.ParkingSpotType;

import java.util.ArrayList;

public class ParkingFloorService {

    public boolean isFloorFull(ParkingFloor parkingFloor) {
        return parkingFloor.getParkingSpots()
                .values()
                .stream()
                .anyMatch(parkingSpots -> parkingSpots.stream().anyMatch(parkingSpot -> parkingSpot.isFree()));
    }


    public boolean canPark(ParkingFloor parkingFloor, ParkingSpotType parkingSpotType) {
        return parkingFloor.getParkingSpots()
                .getOrDefault(parkingSpotType,new ArrayList<>())
                .stream()
                .anyMatch(parkingSpot -> parkingSpot.isFree());
    }

    public ParkingSpot getParkingSpot(ParkingFloor parkingFloor, ParkingSpotType parkingSpotType) {
        return parkingFloor.getParkingSpots()
                .getOrDefault(parkingSpotType,new ArrayList<>())
                .stream()
                .filter(parkingSpot -> parkingSpot.isFree())
                .findFirst()
                .orElse(null);
    }


}
