package com.hcl.TicketBookingSystem.Service;
import com.hcl.TicketBookingSystem.Repository.TrainRepository;
import com.hcl.TicketBookingSystem.Repository.TrainRouteRepository;
import com.hcl.TicketBookingSystem.Repository.TrainScheduleRepository;
import com.hcl.TicketBookingSystem.model.TrainRoute;
import org.springframework.stereotype.Service;

import java.util.*;
import com.hcl.TicketBookingSystem.Exceptions.ResourceNotFoundException;
import com.hcl.TicketBookingSystem.model.Train;
@Service
public class TrainService 
{

        private final TrainRepository trainRepository;

        private final TrainRouteRepository trainRouteRepository;
        private final TrainScheduleRepository trainScheduleRepository;

    // Constructor Injection
   public TrainService(TrainRepository trainRepository,TrainRouteRepository trainRouteRepository,TrainScheduleRepository trainScheduleRepository) 
   {

        this.trainRepository = trainRepository;
        this.trainRouteRepository = trainRouteRepository;
        this.trainScheduleRepository = trainScheduleRepository;
   }

    // ✅ Add Train
    public Train addTrain(Train train) 
    {
        return trainRepository.save(train);
    }

    // ✅ Get All Trains
    public List<Train> getAllTrains() 
    {
        return trainRepository.findAll();

    }

    // ✅ Get Train By Id
    public Train getTrainById(Long id) 
    {
        return trainRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Train not found with id : " + id));
    }

    // ✅ Update Train
    public Train updateTrain(Long id, Train train) 
    {

        Train existingTrain = getTrainById(id);

        existingTrain.setTrainNumber(train.getTrainNumber());
        existingTrain.setTrainName(train.getTrainName());
        existingTrain.setSource(train.getSource());
        existingTrain.setDestination(train.getDestination());
        existingTrain.setTotalSeats(train.getTotalSeats());
        existingTrain.setFare(train.getFare());

        return trainRepository.save(existingTrain);
    }

    // ✅ Delete Train
    public void deleteTrain(Long id) 
    {
        Train train = getTrainById(id);
        trainRepository.delete(train);
    }

    // 🔎 Search Trains
   public List<Map<String,Object>> searchTrains(
        String from, String to, java.time.LocalDate date) 
    {

        List<TrainRoute> fromRoutes =
                trainRouteRepository.findByStationNameIgnoreCase(from);

        List<TrainRoute> toRoutes =
                trainRouteRepository.findByStationNameIgnoreCase(to);

        List<Map<String,Object>> result = new ArrayList<>();

        for (var fromRoute : fromRoutes) 
        {
            for (var toRoute : toRoutes) 
            {

                if(fromRoute.getTrain().getId()
                        .equals(toRoute.getTrain().getId())
                        && fromRoute.getStopOrder() < toRoute.getStopOrder()) 
                {

                    var scheduleOpt =trainScheduleRepository.findByTrainIdAndTravelDate(
                                    fromRoute.getTrain().getId(), date);

                    if(scheduleOpt.isPresent()) 
                    {

                        var schedule = scheduleOpt.get();

                        Map<String,Object> data = new HashMap<>();

                        data.put("trainId", fromRoute.getTrain().getId());
                        data.put("trainName", fromRoute.getTrain().getTrainName());
                        data.put("fromStation", fromRoute.getStationName());
                        data.put("toStation", toRoute.getStationName());
                        data.put("departureTime", fromRoute.getDepartureTime());
                        data.put("arrivalTime", toRoute.getArrivalTime());
                        data.put("availableSeats", schedule.getAvailableSeats());

                        result.add(data);
                    }
                }
            }
        }
        return result;
}
}
