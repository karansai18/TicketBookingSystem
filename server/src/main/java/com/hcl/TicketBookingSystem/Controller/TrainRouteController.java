package com.hcl.TicketBookingSystem.Controller;

import com.hcl.TicketBookingSystem.Service.TrainRouteService;
import com.hcl.TicketBookingSystem.model.TrainRoute;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class TrainRouteController {

    private final TrainRouteService trainRouteService;

    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }

    @PostMapping("/{trainId}")
    public ResponseEntity<TrainRoute> addRoute(@PathVariable Long trainId,
                                               @RequestBody TrainRoute route) {
        return ResponseEntity.ok(
                trainRouteService.addRoute(trainId, route));
    }
}