package com.wipro.etr.util;

public class BookingNotFoundException extends Exception {
    @Override
    public String toString() {
        return "BookingNotFoundException: Booking not found or already cancelled.";
    }
}