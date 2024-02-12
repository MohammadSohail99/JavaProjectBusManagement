package BookingService;

import java.util.List;

class Booking {
    private String bookingReference;
    private Bus bus;
    private List<String> selectedSeats;
    private String travelerName;
    private String contactInfo;
    private int numTickets;

    public Booking(String bookingReference, Bus bus, List<String> selectedSeats, String travelerName, String contactInfo, int numTickets) {
        this.bookingReference = bookingReference;
        this.bus = bus;
        this.selectedSeats = selectedSeats;
        this.travelerName = travelerName;
        this.contactInfo = contactInfo;
        this.numTickets = numTickets;
    }
    public String getBookingReference() {
        return bookingReference;
    }

    public Bus getBus() {
        return bus;
    }

    public List<String> getSelectedSeats() {
        return selectedSeats;
    }

    public String getTravelerName() {
        return travelerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public int getNumTickets() {
        return numTickets;
    }
}

