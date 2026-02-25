package com.hcl.TicketBookingSystem.Repository;

import com.hcl.TicketBookingSystem.model.Train;
import com.hcl.TicketBookingSystem.model.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TrainRouteRepository extends JpaRepository<TrainRoute, Long> {

    List<TrainRoute> findByStationNameIgnoreCase(String stationName);
    Optional<TrainRoute> findByTrainAndStationNameIgnoreCase(Train train, String stationName);
}