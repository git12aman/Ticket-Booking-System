package ticketbookingsystem;

public class Event {
    private String name;
    private int availableTickets;
    private double price;
    private String category;

    public Event(String name, int availableTickets, double price, String category) {
        this.name = name;
        this.availableTickets = availableTickets;
        this.price = price;
        this.category = category;
    }

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

    @Override
    public String toString() {
        return name + " - " + category + " - " + availableTickets + " tickets left";
    }
}
