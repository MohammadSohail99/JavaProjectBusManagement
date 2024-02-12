package BookingService;

import java.time.LocalDateTime;
import java.util.List;

interface TicketBooking {
    List<Bus> searchAvailableBuses(String departureCity, String destinationCity, LocalDateTime travelDateTime);
    Booking bookTicket(Bus selectedBus, String travelerName, String contactInfo, int numTickets);
    int checkAvailableSeats(Bus selectedBus);
}
