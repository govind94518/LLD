package com.shivaya.parkinglot.model.parking;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkingSpot {
    private String parkingSpotId;
    private ParkingSpotType parkingSpotType;
    private String assignedVehicleId;

    public ParkingSpot(String parkingSpotId, ParkingSpotType parkingSpotType) {
        this.parkingSpotId = parkingSpotId;
        this.parkingSpotType = parkingSpotType;
    }

    public  void parkCar(String assignedVehicleId){
        this.assignedVehicleId = assignedVehicleId;
    }
     public  void unParkCar(){
          this.assignedVehicleId = null;
    }
    public boolean isFree(){
        return this.assignedVehicleId == null;
    }

}
