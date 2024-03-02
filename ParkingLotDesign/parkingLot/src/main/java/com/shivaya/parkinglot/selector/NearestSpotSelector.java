package com.shivaya.parkinglot.selector;

import com.shivaya.parkinglot.model.parking.EntryGate;
import com.shivaya.parkinglot.model.parking.ParkingSpot;

import java.util.List;

public class NearestSpotSelector implements ParkingSpotSelector{
    private final EntryGate entryGate;

    public NearestSpotSelector(EntryGate entryGate) {
        this.entryGate = entryGate;
    }

    @Override
    public ParkingSpot selectSpot(List<ParkingSpot> parkingSpots) {
        return null;
    }
}
