package com.hcl.TicketBookingSystem.Controller;

import com.hcl.TicketBookingSystem.Service.BookingService;
import com.hcl.TicketBookingSystem.model.Booking;
import com.hcl.TicketBookingSystem.model.Passenger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


  @PostMapping
public ResponseEntity<Booking> createBooking(
        @RequestParam Long userId,
        @RequestParam Long scheduleId,
        @RequestParam String fromStation,
        @RequestParam String toStation,
        @RequestBody List<Passenger> passengers){

    return ResponseEntity.ok(
            bookingService.createBooking(
                    userId,
                    scheduleId,
                    fromStation,
                    toStation,
                    passengers));
}

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId){
        return ResponseEntity.ok(
                bookingService.getUserBookings(userId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
}