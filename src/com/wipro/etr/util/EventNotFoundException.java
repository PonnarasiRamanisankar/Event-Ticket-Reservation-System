package com.wipro.etr.util;

public class EventNotFoundException extends Exception {
    @Override
    public String toString() {
        return "EventNotFoundException: Event not found with the given ID.";
    }
}