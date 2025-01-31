package ticketbookingsystem;

import java.util.ArrayList;
import java.util.List;

public class TicketBookingSystem {

    // Event class inside TicketBookingSystem
    public static class Event {
        private String name;
        private int availableTickets;
        private double price;
        private String category;
        private String date;

        public Event(String name, int availableTickets, double price, String category, String date) {
            this.name = name;
            this.availableTickets = availableTickets;
            this.price = price;
            this.category = category;
            this.date = date;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public int getAvailableTickets() {
            return availableTickets;
        }

        public void setAvailableTickets(int availableTickets) {
            this.availableTickets = availableTickets;
        }

        public double getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }

        public String getDate() {
            return date;
        }
    }

    // Booking class inside TicketBookingSystem
    public static class Booking {
        private String customerName;
        private String eventName;
        private int numberOfTickets;

        public Booking(String customerName, String eventName, int numberOfTickets) {
            this.customerName = customerName;
            this.eventName = eventName;
            this.numberOfTickets = numberOfTickets;
        }

        // Getters
        public String getCustomerName() {
            return customerName;
        }

        public String getEventName() {
            return eventName;
        }

        public int getNumberOfTickets() {
            return numberOfTickets;
        }
    }

    private List<Event> events = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public void addEvent(String name, int availableTickets, double price, String category, String date) {
        events.add(new Event(name, availableTickets, price, category, date));
    }

    public void bookTicket(String eventName, String customerName, int numberOfTickets) {
        Event event = getEventByName(eventName);
        if (event != null && event.getAvailableTickets() >= numberOfTickets) {
            event.setAvailableTickets(event.getAvailableTickets() - numberOfTickets);
            bookings.add(new Booking(customerName, eventName, numberOfTickets));
        }
    }

    public Event getEventByName(String name) {
        for (Event event : events) {
            if (event.getName().equals(name)) {
                return event;
            }
        }
        return null;
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}
