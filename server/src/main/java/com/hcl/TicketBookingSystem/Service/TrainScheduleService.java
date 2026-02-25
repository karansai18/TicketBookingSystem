package com.hcl.TicketBookingSystem.Service;

import com.hcl.TicketBookingSystem.Exceptions.ResourceNotFoundException;
import com.hcl.TicketBookingSystem.Repository.TrainRepository;
import com.hcl.TicketBookingSystem.Repository.TrainScheduleRepository;
import com.hcl.TicketBookingSystem.model.Train;
import com.hcl.TicketBookingSystem.model.TrainSchedule;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class TrainScheduleService {

    private final TrainScheduleRepository trainScheduleRepository;
    private final TrainRepository trainRepository;

    public TrainScheduleService(TrainScheduleRepository trainScheduleRepository,
                                TrainRepository trainRepository) {
        this.trainScheduleRepository = trainScheduleRepository;
        this.trainRepository = trainRepository;
    }

    public TrainSchedule addSchedule(Long trainId, TrainSchedule schedule) {

        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found"));

        schedule.setTrain(train);

        return trainScheduleRepository.save(schedule);
    }

    public List<TrainSchedule> getAllSchedules() {
    return trainScheduleRepository.findAll();
}

public TrainSchedule getScheduleById(Long id) {
    return trainScheduleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
}

public List<TrainSchedule> getSchedulesByTrain(Long trainId) {
    return trainScheduleRepository.findByTrainId(trainId);
}

public List<TrainSchedule> getSchedulesByDate(LocalDate date) {
    return trainScheduleRepository.findByTravelDate(date);
}
}