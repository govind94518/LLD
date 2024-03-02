package com.shivaya.parkinglot.exceptions;

public class ParkingSpotEmptyException extends RuntimeException{
    public ParkingSpotEmptyException(String message) {
        super(message);
    }
}
