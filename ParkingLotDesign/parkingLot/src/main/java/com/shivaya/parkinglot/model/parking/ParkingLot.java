package com.shivaya.parkinglot.model.parking;
import com.shivaya.parkinglot.model.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ParkingLot {
    private String parkingLotId;
    private Address address;
    private List<ParkingFloor>parkingFloors;
    private List<EntrancePanel>entrancePanels;
    private List<ExitPanel>exitPanels;

    public ParkingLot(String parkingLotId ){
        this.parkingLotId = parkingLotId;
        parkingFloors = new ArrayList<>();
        entrancePanels = new ArrayList<>();
        exitPanels = new ArrayList<>();
    }
}
