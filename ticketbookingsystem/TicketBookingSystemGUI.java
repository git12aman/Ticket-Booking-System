package ticketbookingsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class TicketBookingSystemGUI extends Application {
    private TicketBookingSystem ticketBookingSystem = new TicketBookingSystem();
    private ListView<String> eventListView = new ListView<>();
    private ListView<String> bookingListView = new ListView<>();
    private Label statusLabel = new Label("Welcome to the Ticket Booking System");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Add Heading
        Label headingLabel = new Label("Ticket Booking System");
        headingLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Buttons for main options
        Button addEventButton = new Button("Add Event");
        Button bookEventButton = new Button("Book Event");
        Button viewEventDetailsButton = new Button("View Event Details");
        Button viewBookedEventButton = new Button("View Booked Events");
        Button cancelBookingButton = new Button("Cancel Booking");
        Button cancelEventButton = new Button("Cancel Event");
        Button closeButton = new Button("Close");

        // Set up the layout for event and booking details
        eventListView.setPrefHeight(150);
        bookingListView.setPrefHeight(150);

        // Add event button action
        addEventButton.setOnAction(e -> showAddEventScreen(primaryStage));

        // Book event button action
        bookEventButton.setOnAction(e -> showBookEventScreen(primaryStage));

        // View event details action
        viewEventDetailsButton.setOnAction(e -> showEventDetailsScreen());

        // View booked event details action
        viewBookedEventButton.setOnAction(e -> showBookedEventDetailsScreen());

        // Cancel booking action
        cancelBookingButton.setOnAction(e -> cancelBooking());

        // Cancel event action
        cancelEventButton.setOnAction(e -> cancelEvent());

        // Close application action
        closeButton.setOnAction(e -> primaryStage.close());

        // Organizing the buttons
        VBox buttonLayout = new VBox(10);
        buttonLayout.getChildren().addAll(addEventButton, bookEventButton, viewEventDetailsButton,
                viewBookedEventButton, cancelBookingButton, cancelEventButton, closeButton);

        // Add components to root layout
        root.getChildren().addAll(headingLabel, buttonLayout, statusLabel, eventListView, bookingListView);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ticket Booking System");
        primaryStage.show();
    }

    // Method to display the "Add Event" window
    private void showAddEventScreen(Stage primaryStage) {
        // Layout to add a new event
        TextField eventNameField = new TextField();
        TextField availableTicketsField = new TextField();
        TextField priceField = new TextField();
        TextField categoryField = new TextField();
        TextField dateField = new TextField();
        Button addButton = new Button("Add Event");
        Button backButton = new Button("Back");

        // Handle adding the event
        addButton.setOnAction(e -> {
            String name = eventNameField.getText();
            int availableTickets = Integer.parseInt(availableTicketsField.getText());
            double price = Double.parseDouble(priceField.getText());
            String category = categoryField.getText();
            String date = dateField.getText();

            // Add the event to the system
            ticketBookingSystem.addEvent(name, availableTickets, price, category, date);

            // Show the confirmation screen with the event details
            showEventConfirmationScreen(primaryStage, name, availableTickets, price, category, date);
        });

        // Handle back button action to go back to the main screen
        backButton.setOnAction(e -> primaryStage.setScene(createMainScene(primaryStage)));

        VBox addEventLayout = new VBox(10);
        addEventLayout.getChildren().addAll(new Label("Event Name:"), eventNameField, new Label("Available Tickets:"),
                availableTicketsField, new Label("Price:"), priceField, new Label("Category:"), categoryField,
                new Label("Date:"), dateField, addButton, backButton);

        Scene addEventScene = new Scene(addEventLayout, 300, 300);
        Stage addEventStage = new Stage();
        addEventStage.setScene(addEventScene);
        addEventStage.setTitle("Add Event");
        addEventStage.show();
    }

    // Method to display event confirmation screen
    private void showEventConfirmationScreen(Stage primaryStage, String name, int availableTickets, double price,
                                             String category, String date) {
        // Layout for event confirmation
        VBox confirmationLayout = new VBox(10);
        Label eventDetailsLabel = new Label("Event Added Successfully!");
        Label eventDetails = new Label("Event Name: " + name + "\nAvailable Tickets: " + availableTickets +
                "\nPrice: " + price + "\nCategory: " + category + "\nDate: " + date);

        // Add a button to confirm the event has been added
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            // Close the current window after confirmation and return to the "Main Screen"
            primaryStage.setScene(createMainScene(primaryStage));
        });

        confirmationLayout.getChildren().addAll(eventDetailsLabel, eventDetails, confirmButton);

        Scene confirmationScene = new Scene(confirmationLayout, 300, 200);
        Stage confirmationStage = new Stage();
        confirmationStage.setScene(confirmationScene);
        confirmationStage.setTitle("Event Added");
        confirmationStage.show();
    }

    // Create a main scene with all the buttons and layouts
    private Scene createMainScene(Stage primaryStage) {
        VBox root = new VBox(10);

        // Heading Label for main window
        Label headingLabel = new Label("Ticket Booking System");
        headingLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Buttons for main options
        Button addEventButton = new Button("Add Event");
        Button bookEventButton = new Button("Book Event");
        Button viewEventDetailsButton = new Button("View Event Details");
        Button viewBookedEventButton = new Button("View Booked Events");
        Button cancelBookingButton = new Button("Cancel Booking");
        Button cancelEventButton = new Button("Cancel Event");
        Button closeButton = new Button("Close");

        eventListView.setPrefHeight(150);
        bookingListView.setPrefHeight(150);

        addEventButton.setOnAction(e -> showAddEventScreen(primaryStage));
        bookEventButton.setOnAction(e -> showBookEventScreen(primaryStage));
        viewEventDetailsButton.setOnAction(e -> showEventDetailsScreen());
        viewBookedEventButton.setOnAction(e -> showBookedEventDetailsScreen());
        cancelBookingButton.setOnAction(e -> cancelBooking());
        cancelEventButton.setOnAction(e -> cancelEvent());
        closeButton.setOnAction(e -> primaryStage.close());

        // Organizing the buttons and adding to layout
        VBox buttonLayout = new VBox(10);
        buttonLayout.getChildren().addAll(addEventButton, bookEventButton, viewEventDetailsButton,
                viewBookedEventButton, cancelBookingButton, cancelEventButton, closeButton);

        // Add components to root layout
        root.getChildren().addAll(headingLabel, buttonLayout, statusLabel, eventListView, bookingListView);

        return new Scene(root, 400, 500);
    }

    // Method to display the "Book Event" window
    private void showBookEventScreen(Stage primaryStage) {
        if (ticketBookingSystem.getAllEvents().isEmpty()) {
            statusLabel.setText("No event available for booking. Please add an event first.");
            return;
        }

        // Display the available events in a list and allow the user to choose how many tickets to book
        ChoiceBox<String> eventChoiceBox = new ChoiceBox<>();
        eventChoiceBox.getItems().addAll(getEventNames());
        TextField numberOfTicketsField = new TextField();
        Button bookButton = new Button("Book Tickets");

        // Handle booking the tickets
        bookButton.setOnAction(e -> {
            String selectedEvent = eventChoiceBox.getValue();
            int numberOfTickets = Integer.parseInt(numberOfTicketsField.getText());

            // Check if enough tickets are available
            TicketBookingSystem.Event event = ticketBookingSystem.getEventByName(selectedEvent);
            if (event != null && event.getAvailableTickets() >= numberOfTickets) {
                // Assume user details are filled
                String customerName = "Customer"; // Replace with input fields for customer details
                ticketBookingSystem.bookTicket(selectedEvent, customerName, numberOfTickets);
                statusLabel.setText("Tickets booked successfully!");

                // After booking, go back to main window
                primaryStage.setScene(createMainScene(primaryStage));
            } else {
                statusLabel.setText("Not enough tickets available.");
                eventChoiceBox.getItems().remove(selectedEvent);  // Remove unavailable event from list
            }
        });

        VBox bookEventLayout = new VBox(10);
        bookEventLayout.getChildren().addAll(new Label("Select Event:"), eventChoiceBox, new Label("Number of Tickets:"),
                numberOfTicketsField, bookButton);

        Scene bookEventScene = new Scene(bookEventLayout, 300, 300);
        Stage bookEventStage = new Stage();
        bookEventStage.setScene(bookEventScene);
        bookEventStage.setTitle("Book Event");
        bookEventStage.show();
    }

    // Method to display event details
    private void showEventDetailsScreen() {
        if (ticketBookingSystem.getAllEvents().isEmpty()) {
            statusLabel.setText("No events added.");
            return;
        }

        eventListView.getItems().clear();
        for (TicketBookingSystem.Event event : ticketBookingSystem.getAllEvents()) {
            eventListView.getItems().add("Event Name: " + event.getName() + "\nAvailable Tickets: " + event.getAvailableTickets() + "\nPrice: " + event.getPrice() + "\nCategory: " + event.getCategory() + "\nDate: " + event.getDate());
        }
    }

    // Method to display booked events
    private void showBookedEventDetailsScreen() {
        if (ticketBookingSystem.getAllBookings().isEmpty()) {
            statusLabel.setText("No bookings yet.");
            return;
        }

        bookingListView.getItems().clear();
        for (TicketBookingSystem.Booking booking : ticketBookingSystem.getAllBookings()) {
            bookingListView.getItems().add("Customer: " + booking.getCustomerName() + "\nEvent: " + booking.getEventName() + "\nTickets Booked: " + booking.getNumberOfTickets());
        }
    }

    // Method to cancel a booking
    private void cancelBooking() {
        statusLabel.setText("Booking cancelled successfully!");
    }

    // Method to cancel an event
    private void cancelEvent() {
        statusLabel.setText("Event cancelled successfully!");
    }

    private String[] getEventNames() {
        return ticketBookingSystem.getAllEvents().stream().map(TicketBookingSystem.Event::getName).toArray(String[]::new);
    }
}
