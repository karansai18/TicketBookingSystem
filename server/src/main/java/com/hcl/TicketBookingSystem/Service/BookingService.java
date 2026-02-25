package com.hcl.TicketBookingSystem.Service;

import com.hcl.TicketBookingSystem.Exceptions.ResourceNotFoundException;
import com.hcl.TicketBookingSystem.Repository.*;
import com.hcl.TicketBookingSystem.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TrainScheduleRepository trainScheduleRepository;
    private final TrainRouteRepository trainRouteRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          TrainScheduleRepository trainScheduleRepository,
                          TrainRouteRepository trainRouteRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.trainScheduleRepository = trainScheduleRepository;
        this.trainRouteRepository = trainRouteRepository;
    }

    @Transactional
public Booking createBooking(Long userId,
                             Long scheduleId,
                             String fromStation,
                             String toStation,
                             List<Passenger> passengers) {

    // ✅ Fetch User
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // ✅ Fetch Schedule
    TrainSchedule schedule = trainScheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

    Train train = schedule.getTrain();

    // ✅ Find FROM route
    TrainRoute fromRoute = trainRouteRepository
            .findByTrainAndStationNameIgnoreCase(train, fromStation)
            .orElseThrow(() -> new ResourceNotFoundException("From station not found"));

    // ✅ Find TO route
    TrainRoute toRoute = trainRouteRepository
            .findByTrainAndStationNameIgnoreCase(train, toStation)
            .orElseThrow(() -> new ResourceNotFoundException("To station not found"));

    // ✅ Validate direction
    if (fromRoute.getStopOrder() >= toRoute.getStopOrder()) {
        throw new RuntimeException("Invalid journey direction");
    }

    // ✅ Check seat availability
    if (schedule.getAvailableSeats() < passengers.size()) {
        throw new RuntimeException("Seats not available");
    }

    // ⭐ OPTIONAL ADVANCED: distance based fare
    // int distanceStops = toRoute.getStopOrder() - fromRoute.getStopOrder();

    double totalFare =
            passengers.size() *
            schedule.getTrain().getFare();

    // ✅ Create booking
    Booking booking = new Booking();
    booking.setUser(user);
    booking.setSchedule(schedule);
    booking.setSeatCount(passengers.size());
    booking.setBookingStatus("CONFIRMED");
    booking.setTotalFare(totalFare);

    // ⭐ Save journey segment
    booking.setFromStation(fromStation);
    booking.setToStation(toStation);

    // ✅ Reduce seats
    schedule.setAvailableSeats(
            schedule.getAvailableSeats() - passengers.size()
    );

    // ✅ Attach passengers
    for (Passenger p : passengers) {
        p.setBooking(booking);
    }

    booking.setPassengers(passengers);

    return bookingRepository.save(booking);
}

    public List<Booking> getUserBookings(Long userId){
        return bookingRepository.findByUserId(userId);
    }

    @Transactional
    public void cancelBooking(Long bookingId){

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setBookingStatus("CANCELLED");

        TrainSchedule schedule = booking.getSchedule();
        schedule.setAvailableSeats(
                schedule.getAvailableSeats() + booking.getSeatCount()
        );

        bookingRepository.save(booking);
    }
}