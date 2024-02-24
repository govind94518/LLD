package com.shivaya.parkinglot.services;

import com.shivaya.parkinglot.exceptions.InvalidParkingLotException;
import com.shivaya.parkinglot.exceptions.InvlaidParkingFloorException;
import com.shivaya.parkinglot.model.parking.*;
import com.shivaya.parkinglot.model.vehicle.VehicleType;
import lombok.SneakyThrows;

import java.util.*;


public class ParkingLotService {
    public static Map<String, ParkingLot> parkingLotMap = new HashMap<>();
    public static List<ParkingLot> parkingLots = new ArrayList<>();
    ParkingFloorService parkingFloorService;
    public ParkingLotService(){
        parkingFloorService = new ParkingFloorService();
    }

    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        parkingLotMap.putIfAbsent(parkingLot.getParkingLotId(), parkingLot);
        parkingLots.add(parkingLot);
        return parkingLot;
    }

    public ParkingLot getParkingLot(String parkingLotId) {
        return parkingLotMap.get(parkingLotId);
    }

    @SneakyThrows
    public ParkingFloor addParkingFloor(String parkingLotId, ParkingFloor parkingFloor) {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");

        //Idempotency
        Optional<ParkingFloor> floor = parkingLot.getParkingFloors().stream()
                .filter(pFloor -> pFloor.getFloorId()
                        .equalsIgnoreCase(parkingFloor.getFloorId())).findFirst();
        if (floor.isPresent())
            return floor.get();
        parkingLot.getParkingFloors().add(parkingFloor);

        return parkingFloor;
    }

    @SneakyThrows
    public ParkingSpot addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot) {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        Optional<ParkingFloor> floor = parkingLot.getParkingFloors().stream()
                .filter(pFloor -> pFloor.getFloorId()
                        .equalsIgnoreCase(parkingFloorId)).findFirst();
        if (!floor.isPresent()) {
            throw new InvlaidParkingFloorException("Invalid parking floor");
        }
        Optional<ParkingSpot> spot =
                floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType())
                        .stream().filter(pSpot ->
                                pSpot.getParkingSpotId()
                                        .equalsIgnoreCase(parkingSpot.getParkingSpotId())).findFirst();
        if (spot.isPresent())
            return spot.get();

        floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType()).add(parkingSpot);
        return parkingSpot;
    }



    public EntrancePanel addEntryPanel(String parkingLotId, EntrancePanel entrancePanel) throws InvalidParkingLotException {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        Optional<EntrancePanel> ePanel =
                parkingLotMap.get(parkingLotId)
                        .getEntrancePanels().stream().filter(ep ->
                                ep.getId().equalsIgnoreCase(entrancePanel.getId())).findFirst();
        if (ePanel.isPresent())
            return entrancePanel;
        parkingLotMap.get(parkingLotId)
                .getEntrancePanels().add(entrancePanel);
        return entrancePanel;
    }

    public ExitPanel addExitPanel(String parkingLotId, ExitPanel exitPanel)
            throws InvalidParkingLotException {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        Optional<EntrancePanel> ePanel =
                parkingLotMap.get(parkingLotId)
                        .getEntrancePanels().stream().filter(ep ->
                                ep.getId().equalsIgnoreCase(exitPanel.getId())).findFirst();
        if (ePanel.isPresent())
            return exitPanel;
        parkingLotMap.get(parkingLotId)
                .getExitPanels().add(exitPanel);
        return exitPanel;
    }


    public boolean isParkingLotFull(String parkingLotId) throws InvalidParkingLotException {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        return !parkingLot.getParkingFloors()
                .stream().anyMatch(parkingFloor -> parkingFloorService.isFloorFull(parkingFloor));
    }


    @SneakyThrows
    public boolean canPark(String parkingLotId, VehicleType vehicleType)   {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        return parkingLot.getParkingFloors()
                .stream().anyMatch(parkingFloor -> parkingFloorService.canPark(parkingFloor,getSpotTypeForVehicle(vehicleType)));
    }


    public static ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return ParkingSpotType.CAR;
            case ELECTRIC:
                return ParkingSpotType.ELECTRIC;
            case MOTORBIKE:
                return ParkingSpotType.MOTORBIKE;
            default:
                return ParkingSpotType.LARGE;
        }
    }



    @SneakyThrows
    public ParkingSpot getParkingSpot(String parkingLotId, VehicleType vehicleType)   {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        ParkingSpotType parkingSpotType = getSpotTypeForVehicle(vehicleType);
        return parkingLot.getParkingFloors()
                .stream()
                .map(parkingFloor -> parkingFloor.getParkingSpots().get(parkingSpotType))
                .flatMap(
                        parkingSpot -> parkingSpot.stream()
                )
                .filter(parkingSpot-> parkingSpot.isFree()).findFirst().orElse(null);
    }

    @SneakyThrows
    public ParkingSpot vacateParkingSpot(String parkingLotId, String parkingSpotId)   {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");
                Optional<ParkingSpot> vacantSpot = parkingLot.getParkingFloors().stream()
                .map(parkingFloor -> parkingFloor.getParkingSpots().values())
                .flatMap(Collection::stream)
                  .flatMap(parkingSpots1 -> parkingSpots1.stream())
                        .filter(parkingSpot1 -> parkingSpot1.getParkingSpotId().equals(parkingSpotId))
                                .findFirst();

              if(vacantSpot.isPresent())
                  vacantSpot.get().unParkCar();
                return  vacantSpot.orElse(null);
    }

}
