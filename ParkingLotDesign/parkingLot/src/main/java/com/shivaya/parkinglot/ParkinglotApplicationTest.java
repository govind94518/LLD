package com.shivaya.parkinglot;


import com.shivaya.parkinglot.exceptions.InvalidParkingLotException;
import com.shivaya.parkinglot.model.Address;
import com.shivaya.parkinglot.model.Admin;
import com.shivaya.parkinglot.model.parking.*;
import com.shivaya.parkinglot.model.vehicle.*;
import com.shivaya.parkinglot.services.GateService;
import com.shivaya.parkinglot.services.ParkingLotService;

import java.util.UUID;

public class ParkinglotApplicationTest {

    public static void main(String[] args) throws InvalidParkingLotException {
        String parkingLotId = "123PL";
        ParkingLot parkingLot = new ParkingLot(parkingLotId);


        Address address = new Address();
        address.setAddressLine1("Ram parking Complex");
        address.setStreet("BG Road");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setPinCode("560075");

        parkingLot.setAddress(address);
        //Admin tests
        Admin adminAccount = new Admin();
        ParkingLot parkingLot1 = adminAccount.addParkingLot(parkingLot);
        System.out.println(parkingLot1);
        //Admin Case 1 - should be able to add parking floor case
        adminAccount.addParkingFloor(parkingLotId,new ParkingFloor("1FL"));
        //Admin Case 2 - should be able to add parking floor case
        adminAccount.addParkingFloor(parkingLotId,new ParkingFloor("2FL"));
        System.out.println(parkingLot1);

        //Admin Case 3 - should be able to add entrance panel
        EntrancePanel entrancePanel = new EntrancePanel("1EntrancePanel");
        EntrancePanel entrancePanel1 = adminAccount.addEntrancePanel(parkingLotId,entrancePanel);
        System.out.println(parkingLot1);

        //Admin Case 4 - should be able to add exit panel
        ExitPanel exitPanel = new ExitPanel("1ExitPanel");
        ((Admin) adminAccount).addExitPanel(parkingLotId,exitPanel);
        System.out.println(parkingLot1);
        String floorId = parkingLot.getParkingFloors().get(0).getFloorId();

        ///Admin case 5 - should be able to add car parking spot
        ParkingSpot carSpot1 = new CarParkingSpot("c1PS");
        ParkingSpot parkingSpot1 = ((Admin) adminAccount).addParkingSpot(parkingLotId,floorId, carSpot1);
        System.out.println(parkingLot1);
        ///Admin case 6 - should be able to add bike parking spot
          ParkingSpot bikeSport = new MotorBikeParkingSpot("b1");
        ParkingSpot parkingSpot2 =((Admin) adminAccount).addParkingSpot(parkingLotId,floorId, bikeSport);
        System.out.println(parkingLot1);
        ///Admin case 7 - should be able to add car parking spot
         ParkingSpot carSpot2 = new CarParkingSpot("c2");
        ParkingSpot parkingSpot3 =((Admin) adminAccount).addParkingSpot(parkingLotId,floorId, carSpot2);
        System.out.println(parkingLot1);

        // Test case 1 - check for availability of parking lot - TRUE
        System.out.println(adminAccount.getParkingLotService().canPark( parkingLotId, VehicleType.CAR));

        // Test case 2 - check for availability of parking lot - FALSE
        System.out.println(adminAccount.getParkingLotService().canPark(parkingLotId,VehicleType.EBIKE));

        // Test case 3 - check for availability of parking lot - FALSE
        System.out.println(adminAccount.getParkingLotService().canPark(parkingLotId,VehicleType.ELECTRIC));

        // TEST case 4 - Check if full
        System.out.println(adminAccount.getParkingLotService(). isParkingLotFull(parkingLotId));

        // Test case 5 - get parking spot
        Vehicle vehicle = new Car("KA05MR2311");
        ParkingSpot availableSpot = adminAccount.getParkingLotService().getParkingSpot(parkingLotId,vehicle.getVehicleType());
        System.out.println(availableSpot.getParkingSpotType());
        System.out.println(availableSpot.getParkingSpotId());

        // Test case 6 - should not be able to get spot
        Vehicle van = new Truck("KA01MR7804");
        ParkingSpot vanSpot =adminAccount.getParkingLotService().getParkingSpot(parkingLotId,vehicle.getVehicleType());
        System.out.println(null == vanSpot);

        //Test case 7 - Entrance Panel - 1
        System.out.println(parkingLot1.getEntrancePanels().size());

        // Test case - 8 - Should be able to get parking ticket
        GateService gateService = new GateService(adminAccount.getParkingLotService());
        ParkingTicket parkingTicket = gateService.getParkingTicket(parkingLotId,vehicle);
        System.out.println(parkingTicket.getParkingSpotId());

        ((Admin) adminAccount).addParkingSpot(parkingLotId,floorId, carSpot1);
        // Test case - 9 - Should be able to get parking ticket
        Vehicle car = new Car("KA02MR6355");
        ParkingTicket parkingTicket1 = gateService.getParkingTicket(parkingLotId, car);
        System.out.println(parkingTicket1);

        // Test case 10 - Should not be able to get ticket
        ParkingTicket tkt =  gateService.getParkingTicket(parkingLotId, new Car("ka04rb8458"));
        System.out.println(null == tkt);

        // Test case 11 - Should be able to get ticket
        ParkingTicket mtrTkt = gateService.getParkingTicket(parkingLotId,new MotorBike("ka01ee4901"));
        if(mtrTkt==null){
            System.out.println("No Spot for fpr this vehicle");
        }else
        System.out.println(mtrTkt.getParkingSpotId());

        //Test case 12 - vacate parking spot
        mtrTkt = gateService.scanAndVacate(parkingLotId,tkt);
        System.out.println(mtrTkt.getCharges());
        System.out.println(mtrTkt.getCharges() > 0);
        System.out.println(parkingLot1);

        // Test case 13 - park on vacated spot
        ParkingTicket mtrTkt1 = gateService.getParkingTicket(parkingLotId,new MotorBike("ka01ee7791"));
        System.out.println(mtrTkt.getParkingSpotId());

        // Test case 14 - park when spot is not available
        ParkingTicket unavaialbemTkt =
                gateService.getParkingTicket(parkingLotId,new MotorBike("ka01ee4455"));
        System.out.println(null == unavaialbemTkt);

        // Test cast 15 - vacate car
        parkingTicket =  gateService.scanAndVacate(parkingLotId,parkingTicket);
        System.out.println(parkingTicket.getCharges());
        System.out.println(parkingTicket.getCharges() > 0);

        //Test case 16 - Now should be able to park car
        System.out.println(adminAccount.getParkingLotService().canPark(parkingLotId,VehicleType.CAR));

        //Test case 17 - Should be able to vacate parked vehicle
        parkingTicket1 = gateService.scanAndVacate(parkingLotId,parkingTicket1);
        System.out.println(parkingTicket1.getCharges());
        System.out.println(parkingTicket1.getCharges() > 0);

        //Test case 18 - check for slots count

        System.out.println(parkingLot.getParkingFloors()
                .get(0).getParkingSpots().get(ParkingSpotType.CAR).size());

        //Test case 19 - Payment
        Payment payment = new Payment(UUID.randomUUID().toString(),
                parkingTicket1.getTicketNumber(), parkingTicket1.getCharges());
        payment.makePayment();
        System.out.println(payment.getPaymentStatus());

        //Test case 20 - vacate motorbike spot
        mtrTkt =  gateService.scanAndVacate(parkingLotId,mtrTkt);
        System.out.println(parkingLot.getParkingFloors()
                .get(0).getParkingSpots().get(ParkingSpotType.MOTORBIKE).size());
        System.out.println(mtrTkt.getCharges());
    }
}
