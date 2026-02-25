package com.hcl.TicketBookingSystem.Controller;

import com.hcl.TicketBookingSystem.Service.TrainScheduleService;
import com.hcl.TicketBookingSystem.model.TrainSchedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class TrainScheduleController {

    private final TrainScheduleService trainScheduleService;

    public TrainScheduleController(TrainScheduleService trainScheduleService) {
        this.trainScheduleService = trainScheduleService;
    }

    // ✅ Add Schedule (Admin)
    @PostMapping("/{trainId}")
    public ResponseEntity<TrainSchedule> addSchedule(@PathVariable Long trainId,
                                                     @RequestBody TrainSchedule schedule) 
    {

        return ResponseEntity.ok(
                trainScheduleService.addSchedule(trainId, schedule));
    }

    // ✅ Get ALL schedules
    @GetMapping
    public ResponseEntity<List<TrainSchedule>> getAllSchedules() {
        return ResponseEntity.ok(
                trainScheduleService.getAllSchedules());
    }

    // ✅ Get schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<TrainSchedule> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(
                trainScheduleService.getScheduleById(id));
    }

    // ✅ Get schedules of a train
    @GetMapping("/train/{trainId}")
    public ResponseEntity<List<TrainSchedule>> getSchedulesByTrain(@PathVariable Long trainId) {
        return ResponseEntity.ok(
                trainScheduleService.getSchedulesByTrain(trainId));
    }

    // ✅ Get schedules by date
    @GetMapping("/date")
    public ResponseEntity<List<TrainSchedule>> getSchedulesByDate(@RequestParam String date) {

        LocalDate travelDate = LocalDate.parse(date);

        return ResponseEntity.ok(
                trainScheduleService.getSchedulesByDate(travelDate));
    }
}