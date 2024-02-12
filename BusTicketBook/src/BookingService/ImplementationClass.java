package BookingService;

import java.util.Scanner;

public class ImplementationClass {
    
    public static void main(String[] args) {
        BusTicketBookingService bookingService = new BusTicketBookingService();
        Scanner scanner = new Scanner(System.in);
        //Admin admin=new Admin();

        while (true) {
            System.out.println("-----------------------------");
            System.out.println("      Bus Ticket Booking     ");
            System.out.println("-----------------------------");
            System.out.println("1. If New Please Register");
            System.out.println("2. Already Registered Please Login");
            System.out.println("3. Search Available Buses you want");
            System.out.println("4. Book Ticket for available Busses");
            System.out.println("5. Display User's Bookings");
            System.out.println("6. Display Registered Users");
            System.out.println("7. Display All Bookings");
            System.out.println("8. Add Bus [Can add By Admin]");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookingService.registerUser();
                    break;

                case 2:
                   bookingService.loginUser();
                    break;

                case 3:
                    bookingService.searchAndDisplayAvailableBuses();
                    break;

                case 4:
                   bookingService.bookTicketProcess();
                    break;

                case 5:
                    if (bookingService.userDatabase.isLoggedIn()) {
                        String username = bookingService.userDatabase.getCurrentLoggedInUser();
                        bookingService.displayUserBookings(username);
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 6:
                    bookingService.displayRegisteredUsers();
                    break;

                case 7:
                    bookingService.displayAllBookings();
                    break;
                case 8:
                    bookingService.addNewBus();
                    break;

                case 9:
                    System.out.println("Exiting.");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    break;
            }
        }
    }
}
