package com.shivaya.parkinglot.model.parking;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

@Getter
@Setter
@ToString
public class ParkingFloor {
    private String floorId;
  //  List<ParkingSpot>parkingSpots;

    private Map<ParkingSpotType, List<ParkingSpot>> parkingSpots = new HashMap<>();
    private Map<String,ParkingSpot>usedParkingSpots = new HashMap<>();


    public ParkingFloor(String id) {
        this.floorId = id;
        parkingSpots.put(ParkingSpotType.ABLED , new ArrayList<>());
        parkingSpots.put(ParkingSpotType.CAR, new ArrayList<>());
        parkingSpots.put(ParkingSpotType.LARGE,new ArrayList<>());
        parkingSpots.put(ParkingSpotType.MOTORBIKE, new ArrayList<>());
        parkingSpots.put(ParkingSpotType.ELECTRIC, new ArrayList<>());
    }

}
