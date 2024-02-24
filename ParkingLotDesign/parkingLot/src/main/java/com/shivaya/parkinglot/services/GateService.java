package com.shivaya.parkinglot.services;

import com.shivaya.parkinglot.exceptions.InvalidParkingLotException;
import com.shivaya.parkinglot.model.parking.ParkingSpot;
import com.shivaya.parkinglot.model.parking.ParkingSpotType;
import com.shivaya.parkinglot.model.vehicle.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class GateService {
    ParkingLotService parkingLotService;

    public GateService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public ParkingTicket getParkingTicket(String parkingLotId,   Vehicle vehicle)  {
        if(!parkingLotService.canPark(parkingLotId,vehicle.getVehicleType()))
            return null;
        ParkingSpot parkingSpot = parkingLotService.getParkingSpot(parkingLotId, vehicle.getVehicleType());
        return buildTicket(vehicle.getLicenseNumber(),parkingSpot.getParkingSpotId());
    }

    private ParkingTicket buildTicket(String licenseNumber, String parkingSpotId) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setParkingSpotId(parkingSpotId);
        parkingTicket.setLicenseNumber(licenseNumber);
        parkingTicket.setTicketNumber(UUID.randomUUID().toString());
        parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
        return parkingTicket;
    }

    public ParkingTicket scanAndVacate(String parkingLotId, ParkingTicket parkingTicket){
       ParkingSpot parkingSpot = parkingLotService.vacateParkingSpot(parkingLotId,parkingTicket.getParkingSpotId());
       parkingTicket.setCharges(calculateCost(parkingTicket,parkingSpot.getParkingSpotType()));
       return parkingTicket;
    }

    private double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        Duration between = Duration.between(parkingTicket.getIssuedAt(),LocalDateTime.now());
        long hours = between.toHours();
        if(hours == 0)
            hours+=1;
        double amount = hours*new HourlyCost().getCost(parkingSpotType);
        return hours;
    }

}
