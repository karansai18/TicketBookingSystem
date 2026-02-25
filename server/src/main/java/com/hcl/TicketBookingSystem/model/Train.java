package com.hcl.TicketBookingSystem.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private double fare;

    @JsonIgnore
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<TrainSchedule> schedules;

    @JsonIgnore
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<TrainRoute> routes;

    public Train() {}

    // getters and setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTrainNumber() { return trainNumber; }

    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public String getTrainName() { return trainName; }

    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getSource() { return source; }

    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }

    public void setDestination(String destination) { this.destination = destination; }

    public int getTotalSeats() { return totalSeats; }

    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public double getFare() { return fare; }

    public void setFare(double fare) { this.fare = fare; }

    public List<TrainSchedule> getSchedules() { return schedules; }

    public void setSchedules(List<TrainSchedule> schedules) { this.schedules = schedules; 
        
    }
}
