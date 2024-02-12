package BookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bus {
    private String busName;
    private String source;
    private String destination;
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private int totalSeats;
    private int availableSeats;
    private List<String> bookedSeats;

    public Bus(String busName, String source, String destination, LocalDateTime starttime, LocalDateTime endtime, int totalSeats) {
        this.busName = busName;
        this.source = source;
        this.destination = destination;
        this.starttime = starttime;
        this.endtime = endtime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.bookedSeats=new ArrayList<>();
    }
    public String getBusName() {
        return busName;
    }

    public String getDepartureCity() {
        return source;
    }

    public String getDestinationCity() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return starttime;
    }

    public LocalDateTime getArrivalTime() {
        return endtime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public List<String> getBookedSeats() {
        return bookedSeats;
    }
}
