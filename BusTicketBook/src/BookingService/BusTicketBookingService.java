package BookingService;
import Admin.admin;


import UserDataBase.UserDatabase;

import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class BusTicketBookingService implements TicketBooking {

    private List<Bus> availableBuses;
    private Map<String, List<Booking>> userBookings;
    UserDatabase userDatabase;
    public admin admin;
    Scanner sc=new Scanner(System.in);
    public BusTicketBookingService() {
        availableBuses = new ArrayList<>();
        userBookings = new HashMap<>();
        userDatabase = new UserDatabase();
        admin=new admin("admin","password");
        addInitialBus();
    }
    public void  addInitialBus(){
        availableBuses.add(new Bus("AbhiBus", "zaheerabad", "nizamabad", LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3), 20));
        availableBuses.add(new Bus("Luxury", "hyderabad", "zaheerabad", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4), 25));
        availableBuses.add(new Bus("Express", "banglore", "hyderabad", LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5), 20));
        availableBuses.add(new Bus("Ordinary", "nyalkal", "zaheerabad", LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3), 40));
        availableBuses.add(new Bus("Superfast", "zaheerabad", "nizamabad", LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3), 20));
    }
    public void addBus(Bus bus){
        if(admin.isAuthenticate()){
            availableBuses.add(bus);
            System.out.println("Bus added Successfully!!!");
        }
        else {
            System.out.println("You are not an admin-Please login as admin");
        }
    }

    public void registerUser() {
        System.out.print("Enter username for registration: ");
        String username = sc.nextLine();
        System.out.print("Enter password for registration: ");
        String password = sc.nextLine();
        userDatabase.registerUser(username, password);
        System.out.println("User registered successfully.");
        System.out.println("Thank You for Registration!!!");
    }
    public boolean loginUser() {
        System.out.print("Enter username for login: ");
        String loginUsername = sc.nextLine();
        System.out.print("Enter password for login: ");
        String loginPassword = sc.nextLine();
        boolean loggedIn = userDatabase.loginUser(loginUsername, loginPassword);
        if (!loggedIn) {
            System.out.println("Login Failed-If you have not registered please register or else enter correct username and password");
        } else {
            System.out.println("Login successful.");
        }
        return loggedIn;
    }
    public void searchAndDisplayAvailableBuses() {
        System.out.print("Enter source city: ");
        String departureCity = sc.nextLine();
        System.out.print("Enter destination city: ");
        String destinationCity = sc.nextLine();
        System.out.print("Enter travel date and time (YYYY-MM-DD HH:MM): ");
        String dateTimeString = sc.nextLine();
        LocalDateTime travelDateTime = LocalDateTime.parse(dateTimeString);
        List<Bus> availableBuses = searchAvailableBuses(departureCity, destinationCity, travelDateTime);
        if (availableBuses.isEmpty()) {
            System.out.println("No buses available for the specified route and time.");
        } else {
            System.out.println("Available Buses:");
            for (int i = 0; i < availableBuses.size(); i++) {
                Bus bus = availableBuses.get(i);
                System.out.println((i + 1) + ". Bus: " + bus.getBusName() + ", Departure Time: " + bus.getDepartureTime() + ", Available Seats: " + checkAvailableSeats(bus));
            }
        }
    }
    public void bookTicketProcess() {
        if (!userDatabase.isLoggedIn()) {
            System.out.println("Please log in before booking a ticket.");
            return;
        }
        System.out.print("Enter number of tickets to book: ");
        int numTickets = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter traveler name: ");
        String travelerName = sc.nextLine();
        System.out.print("Enter contact number: ");
        String contactInfo = sc.nextLine();
        if (userDatabase.isLoggedIn()) {
            System.out.println("Logged in as: " + userDatabase.getCurrentLoggedInUser());
            System.out.print("Select a bus from above specified buses (Enter the number): ");
            int busChoice = sc.nextInt();
            sc.nextLine();
            List<Bus> buses = searchAvailableBuses("zaheerabad", "nizamabad", LocalDateTime.now());
            if (busChoice >= 1 && busChoice <= buses.size()) {
                Bus selectedBus = buses.get(busChoice - 1);
                Booking booking = bookTicket(selectedBus, travelerName, contactInfo, numTickets);
                if (booking != null) {
                    System.out.println("Ticket booked successfully!");
                    // System.out.println("Booking Reference: " + booking.getBookingReference());
                    System.out.println("Thank You for booking the seats!!");
                    System.out.println("Selected Seats: " + booking.getSelectedSeats());
                }
            } else {
                System.out.println("Invalid bus choice.");
            }
        } else {
            System.out.println("Please log in first.");
        }
    }
    public void addNewBus() {
        System.out.print("Enter admin username: ");
        String adminUsername = sc.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = sc.nextLine();
        if (admin.authenticated(adminUsername, adminPassword)) {
            System.out.print("Enter bus name: ");
            String busName = sc.nextLine();
            System.out.print("Enter source city: ");
            String source = sc.nextLine();
            System.out.print("Enter destination city: ");
            String destination = sc.nextLine();
            System.out.print("Enter start time (YYYY-MM-DD HH:MM): ");
            String departureTimeString = sc.nextLine();
            LocalDateTime starttime = LocalDateTime.parse(departureTimeString);
            System.out.print("Enter end time (YYYY-MM-DD HH:MM): ");
            String arrivalTimeString = sc.nextLine();
            LocalDateTime endtime = LocalDateTime.parse(arrivalTimeString);
            System.out.print("Enter available seats: ");
            int availableSeats = sc.nextInt();
            sc.nextLine();

            Bus newBus = new Bus(busName, source, destination, starttime, endtime, availableSeats);
            addBus(newBus);
        } else {
            System.out.println("Only admin can add buses. Please log in as admin.");
        }
    }



    @Override
    public List<Bus> searchAvailableBuses(String departureCity, String destinationCity, LocalDateTime travelDateTime) {
//        List<Bus> result = new ArrayList<>();
        /*for (Bus bus : availableBuses) {
            if (bus.getDepartureCity().equalsIgnoreCase(departureCity) &&
                    bus.getDestinationCity().equalsIgnoreCase(destinationCity) &&
                    bus.getDepartureTime().isAfter(travelDateTime)) {
                result.add(bus);
            }
        }*/
        List<Bus> result = availableBuses.stream()
                .filter(bus -> bus.getDepartureCity().equalsIgnoreCase(departureCity))
                .filter(bus -> bus.getDestinationCity().equalsIgnoreCase(destinationCity))
                .filter(bus -> bus.getDepartureTime().isAfter(travelDateTime))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Booking bookTicket(Bus selectedBus, String travelerName, String contactInfo, int numTickets) {
        // Check if there are enough available seats
        if (selectedBus.getAvailableSeats() < numTickets) {
            System.out.println("Not enough available seats in the bus.");
            return null;
        }
        List<String> selectedSeats = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < numTickets; i++) {
            System.out.print("Enter seat number for ticket booking " + (i + 1) + ": ");
            String seatNumber = sc.nextLine();
            if (!selectedBus.getBookedSeats().contains(seatNumber)) {
                selectedSeats.add(seatNumber);
                selectedBus.getBookedSeats().add(seatNumber);
            } else {
                System.out.println("Seat " + seatNumber + " already booked. Please select another seat.");
                i--;
            }
        }
        printAvailableSeats(selectedBus);
       /* IntStream.range(0, numTickets)
                .mapToObj(i -> {
                    System.out.print("Enter seat number for ticket booking " + (i + 1) + ": ");
                    return sc.nextLine();
                })
                .filter(seatNumber -> !selectedBus.getBookedSeats().contains(seatNumber))
                .forEach(seatNumber -> {
                    selectedSeats.add(seatNumber);
                    selectedBus.getBookedSeats().add(seatNumber);
                });*/

        int updatedAvailableSeats = selectedBus.getAvailableSeats() - numTickets;
        selectedBus.setAvailableSeats(updatedAvailableSeats);
        String bookingReference = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingReference, selectedBus, selectedSeats, travelerName, contactInfo, numTickets);
        String username = userDatabase.getCurrentLoggedInUser();
        List<Booking> userBookings = this.userBookings.getOrDefault(username, new ArrayList<>());
        userBookings.add(booking);

        this.userBookings.put(username, userBookings);
        return booking;
    }



    @Override
    public int checkAvailableSeats(Bus selectedBus) {
        return selectedBus.getAvailableSeats();
    }

    public void displayUserBookings(String username) {
        List<Booking> bookings = userBookings.getOrDefault(username, new ArrayList<>());
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for user: " + username);
        } else {
            System.out.println("Bookings for user: " + username);
            /*for (Booking booking : bookings) {
                System.out.println(booking);
            }*/
            bookings.stream()
                    .forEach(System.out::println);

        }
    }

    public void displayRegisteredUsers() {
        System.out.println("Registered Users:");
        /*for (String username : userDatabase.getRegisteredUsers()) {
            System.out.println(username);
        }*/
        userDatabase.getRegisteredUsers().stream()
                .forEach(System.out::println);

    }

    public void displayAllBookings() {
        System.out.println("All Bookings:");
       /* for (Map.Entry<String, List<Booking>> entry : userBookings.entrySet()) {
            System.out.println("User: " + entry.getKey());
            List<Booking> bookings = entry.getValue();
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }*/
        userBookings.entrySet().stream()
                .forEach(entry -> {
                    System.out.println("User: " + entry.getKey());
                    entry.getValue().stream()
                            .forEach(System.out::println);
                });

    }
    public static void printAvailableSeats(Bus bus) {
        List<String> bookedSeats = bus.getBookedSeats();
        int totalSeats = bus.getTotalSeats();

        System.out.println("Available seats for bus " + bus.getBusName() + ":");
        for (int i = 1; i <= totalSeats; i++) {
            String seatNumber = Integer.toString(i);
            if (!bookedSeats.contains(seatNumber)) {
                System.out.print(seatNumber + " ");
            }
        }
        System.out.println();
    }

}


