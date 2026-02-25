package com.hcl.TicketBookingSystem.Service;

import com.hcl.TicketBookingSystem.Exceptions.ResourceNotFoundException;
import com.hcl.TicketBookingSystem.Repository.TrainRepository;
import com.hcl.TicketBookingSystem.Repository.TrainRouteRepository;
import com.hcl.TicketBookingSystem.model.Train;
import com.hcl.TicketBookingSystem.model.TrainRoute;
import org.springframework.stereotype.Service;

@Service
public class TrainRouteService {

    private final TrainRouteRepository trainRouteRepository;
    private final TrainRepository trainRepository;

    public TrainRouteService(TrainRouteRepository trainRouteRepository,
                             TrainRepository trainRepository) {
        this.trainRouteRepository = trainRouteRepository;
        this.trainRepository = trainRepository;
    }

    public TrainRoute addRoute(Long trainId, TrainRoute route) {

        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found"));

        route.setTrain(train);
        return trainRouteRepository.save(route);
    }
}