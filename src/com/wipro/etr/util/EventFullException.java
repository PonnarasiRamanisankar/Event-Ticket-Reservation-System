package com.wipro.etr.util;

public class EventFullException extends Exception {
	 @Override
	    public String toString() {
	        return "EventFullException: No seats available for this event.";
	    }

}
