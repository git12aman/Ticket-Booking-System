package ticketbookingsystem;

public class Customer {
    private String name;
    private String email;
    private String phone;
    private int totalBookings;

    // Constructor to initialize customer details
    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.totalBookings = 0;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public void incrementBookings() {
        this.totalBookings++;
    }

    public void decrementBookings() {
        if (this.totalBookings > 0) {
            this.totalBookings--;
        }
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', email='" + email + "', phone='" + phone + "', totalBookings=" + totalBookings + "}";
    }
}
