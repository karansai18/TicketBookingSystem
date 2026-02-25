package com.hcl.TicketBookingSystem.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hcl.TicketBookingSystem.model.Train;
import com.hcl.TicketBookingSystem.Service.TrainService;
import java.util.*;

@RestController
@RequestMapping("/trains")
public class TrainController 
{
        
        private final TrainService trainService;
       
    
        public TrainController(TrainService trainService) 
        {
            this.trainService = trainService;
        } 
       
    
        // ✅ Add Train
        @PostMapping
        public ResponseEntity<Train> addTrain(@RequestBody Train train) {
            return ResponseEntity.ok(trainService.addTrain(train));
        }
    
        // ✅ Get All Trains
        @GetMapping
        public ResponseEntity<List<Train>> getAllTrains() {
            return ResponseEntity.ok(trainService.getAllTrains());
        }
    
        // ✅ Get Train By Id
        @GetMapping("/{id}")
        public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
            return ResponseEntity.ok(trainService.getTrainById(id));
        }
    
        // ✅ Update Train
        @PutMapping("/{id}")
        public ResponseEntity<Train> updateTrain(@PathVariable Long id,
                                                    @RequestBody Train train) {
            return ResponseEntity.ok(trainService.updateTrain(id, train));
        }
    
        // ✅ Delete Train
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteTrain(@PathVariable Long id) {
            trainService.deleteTrain(id);
            return ResponseEntity.ok("Train deleted successfully");
        }
    
        // 🔎 Search Train
        @GetMapping("/search")
        public ResponseEntity<List<Map<String,Object>>> searchTrains(@RequestParam String from,@RequestParam String to,@RequestParam String date)
        {

            java.time.LocalDate travelDate = java.time.LocalDate.parse(date);

            return ResponseEntity.ok(
                trainService.searchTrains(from,to,travelDate));
        }
}
    



