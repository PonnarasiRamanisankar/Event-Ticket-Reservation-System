package com.wipro.etr.entity;

public class Event {
    private String eventId;
    private String eventName;
    private String eventDate;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;

    public Event(String eventId, String eventName, String eventDate,int totalSeats, int availableSeats, double ticketPrice) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
    }
    public String getEventId() {
    	return eventId;
    	}
    public void setEventId(String eventId) { 
    	this.eventId = eventId; 
    	}

    public String getEventName() { 
    	return eventName;
    	}
    public void setEventName(String eventName) { 
    	this.eventName = eventName; 
    	}

    public String getEventDate() { 
    	return eventDate; 
    	}
    public void setEventDate(String eventDate) { 
    	this.eventDate = eventDate;
    	}

    public int getTotalSeats() { 
    	return totalSeats;
    	}
    public void setTotalSeats(int totalSeats) {
    	this.totalSeats = totalSeats;
    	}

    public int getAvailableSeats() {
    	return availableSeats; 
    	}
    public void setAvailableSeats(int availableSeats) { 
    	this.availableSeats = availableSeats;
    	}

    public double getTicketPrice() {
    	return ticketPrice;
    	}
    public void setTicketPrice(double ticketPrice) {
    	this.ticketPrice = ticketPrice;
    	}
}