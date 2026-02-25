package com.hcl.TicketBookingSystem.Repository;

import com.hcl.TicketBookingSystem.model.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, Long> {

    // 🔎 Used in intermediate station search logic
    Optional<TrainSchedule> findByTrainIdAndTravelDate(Long trainId, LocalDate travelDate);

    // 📜 Optional: get all schedules of a train
    List<TrainSchedule> findByTrainId(Long trainId);

    // 📅 Optional: get schedules by date
    List<TrainSchedule> findByTravelDate(LocalDate travelDate);
}