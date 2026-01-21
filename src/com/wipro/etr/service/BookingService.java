package com.wipro.etr.service;

import com.wipro.etr.entity.Booking;
import com.wipro.etr.entity.Event;
import com.wipro.etr.entity.User;
import com.wipro.etr.util.*;

import java.util.ArrayList;
import java.util.UUID;

public class BookingService {

    private ArrayList<User> users;
    private ArrayList<Event> events;
    private ArrayList<Booking> bookings;

    public BookingService(ArrayList<User> users, ArrayList<Event> events, ArrayList<Booking> bookings) {
        this.users = users;
        this.events = events;
        this.bookings = bookings;
    }

    public boolean validateUser(String userId) throws InvalidUserException {
        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                return true;
            }
        }
        throw new InvalidUserException();
    }

    public Event findEvent(String eventId) throws EventNotFoundException {
        for (Event e : events) {
            if (e.getEventId().equals(eventId)) {
                return e;
            }
        }
        throw new EventNotFoundException();
    }

    public Booking bookTicket(String userId, String eventId, int seats) throws Exception {
        validateUser(userId);
        Event event = findEvent(eventId);

        if (seats <= 0) throw new BookingOperationException();
        if (event.getAvailableSeats() < seats) throw new EventFullException();

        double totalFare = calculateFare(event, seats);
        String bookingId = UUID.randomUUID().toString();

        Booking booking = new Booking(bookingId, userId, eventId, seats, totalFare);
        bookings.add(booking);

        event.setAvailableSeats(event.getAvailableSeats() - seats);

        return booking;
    }

    public double calculateFare(Event event, int seats) {
        return event.getTicketPrice() * seats;
    }

    public void cancelBooking(String bookingId) throws BookingNotFoundException, EventNotFoundException {
        Booking targetBooking = null;
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId)) {
                targetBooking = b;
                break;
            }
        }

        if (targetBooking == null) throw new BookingNotFoundException();

        Event event = findEvent(targetBooking.getEventId());
        event.setAvailableSeats(event.getAvailableSeats() + targetBooking.getSeatsBooked());
        bookings.remove(targetBooking);
    }

    public void printUserBookings(String userId) {
        boolean found = false;
        System.out.println("Bookings for User ID: " + userId);

        for (Booking b : bookings) {
            if (b.getUserId().equals(userId)) {
                try {
                    Event e = findEvent(b.getEventId());
                    System.out.println("Booking ID: " + b.getBookingId() +
                            ", Event: " + e.getEventName() +
                            ", Date: " + e.getEventDate() +
                            ", Seats: " + b.getSeatsBooked() +
                            ", Fare: " + b.getTotalFare());
                    found = true;
                } catch (EventNotFoundException ex) {
                    System.out.println("Event details not found for booking: " + b.getBookingId());
                }
            }
        }

        if (!found) {
            System.out.println("No bookings found for this user.");
        }
    }
}
