package com.shivaya.parkinglot.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ParkingTicket {
    private String ticketNumber;
    private String licenseNumber;
    private String parkingSpotId;
    private LocalDateTime issuedAt;
    private LocalDateTime vacatedAt;
    private double charges;
    private TicketStatus ticketStatus;

}
