package com.wipro.etr.main;
import com.wipro.etr.entity.*;
import com.wipro.etr.service.BookingService;
import com.wipro.etr.util.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<User> users = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        
        System.out.print("Enter number of users to register: ");
        int userCount = sc.nextInt();
        sc.nextLine();  

        for (int i = 0; i < userCount; i++) {
            System.out.println("Enter details for User " + (i + 1));
            System.out.print("User ID: ");
            String userId = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Contact Number: ");
            String contact = sc.nextLine();
            users.add(new User(userId, name, contact));
        }

        
        System.out.print("\nEnter number of events: ");
        int eventCount = sc.nextInt();
        sc.nextLine();  

        for (int i = 0; i < eventCount; i++) {
            System.out.println("Enter details for Event " + (i + 1));
            System.out.print("Event ID: ");
            String eventId = sc.nextLine();
            System.out.print("Event Name: ");
            String eventName = sc.nextLine();
            System.out.print("Event Date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            System.out.print("Total Seats: ");
            int totalSeats = sc.nextInt();
            System.out.print("Ticket Price: ");
            double price = sc.nextDouble();
            sc.nextLine();  
            events.add(new Event(eventId, eventName, date, totalSeats, totalSeats, price));
        }

        BookingService service = new BookingService(users, events, bookings);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Event Ticket Reservation Menu ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View User Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter User ID: ");
                        String uId = sc.nextLine();
                        System.out.print("Enter Event ID: ");
                        String eId = sc.nextLine();
                        System.out.print("Enter number of seats to book: ");
                        int seats = sc.nextInt();
                        sc.nextLine();
                        Booking b = service.bookTicket(uId, eId, seats);
                        System.out.println("Booking Successful! Booking ID: " + b.getBookingId());
                        break;

                    case 2:
                        System.out.print("Enter Booking ID to cancel: ");
                        String bId = sc.nextLine();
                        service.cancelBooking(bId);
                        System.out.println("Booking Cancelled Successfully!");
                        break;

                    case 3:
                        System.out.print("Enter User ID to view bookings: ");
                        String viewId = sc.nextLine();
                        service.printUserBookings(viewId);
                        break;

                    case 4:
                        running = false;
                        System.out.println("Exiting... Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (InvalidUserException | EventFullException | EventNotFoundException |
                     BookingNotFoundException | BookingOperationException ex) {
                System.out.println(ex.toString());
            } catch (Exception ex) {
                System.out.println("Unexpected Error: " + ex);
            }
        }

        sc.close();
    }
}