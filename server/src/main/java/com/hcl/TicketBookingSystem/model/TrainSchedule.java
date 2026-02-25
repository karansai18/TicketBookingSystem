package com.hcl.TicketBookingSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
// import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "train_schedule")
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate travelDate;   
    // private LocalTime departureTime;
    // private LocalTime arrivalTime;

    private int availableSeats;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @JsonIgnore
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public TrainSchedule() {}

    // getters and setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getTravelDate() { return travelDate; }

    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }

    // public LocalTime getDepartureTime() { return departureTime; }

    // public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }

    // public LocalTime getArrivalTime() { return arrivalTime; }

    // public void setArrivalTime(LocalTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getAvailableSeats() { return availableSeats; }

    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public Train getTrain() { return train; }

    public void setTrain(Train train) { this.train = train; }

    public List<Booking> getBookings() { return bookings; }

    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }
}
