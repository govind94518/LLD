package com.shivaya.parkinglot.model;

import com.shivaya.parkinglot.exceptions.InvalidParkingLotException;
import com.shivaya.parkinglot.exceptions.InvlaidParkingFloorException;
import com.shivaya.parkinglot.model.parking.*;
import com.shivaya.parkinglot.services.ParkingLotService;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class Admin extends Account{
    ParkingLotService parkingLotService  ;

    public Admin( ) {
        this.parkingLotService = new ParkingLotService();
    }

    public  ParkingFloor addParkingFloor(String parkingLotId , ParkingFloor parkingFloor)  {
        return parkingLotService.addParkingFloor(parkingLotId,parkingFloor);
    }


    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        return parkingLotService.addParkingLot(parkingLot);
    }


    public ParkingLot getParkingLot(String parkingLotId) {
        return  parkingLotService. getParkingLot( parkingLotId);
    }


    @SneakyThrows
    public ParkingSpot addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot) {
        return parkingLotService.addParkingSpot( parkingLotId, parkingFloorId, parkingSpot);
    }


    @SneakyThrows
    public EntrancePanel addEntrancePanel(String parkingLotId, EntrancePanel entrancePanel)
              {
        return parkingLotService.addEntryPanel(parkingLotId,entrancePanel);
    }

    @SneakyThrows

    public ExitPanel addExitPanel(String parkingLotId, ExitPanel exitPanel)
              {
        return parkingLotService.addExitPanel(parkingLotId,exitPanel);
    }

}
