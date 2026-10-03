package in.choubeyshubham.seatservice.repository;

import in.choubeyshubham.seatservice.model.FlightInstanceCabin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightInstanceCabinRepository extends JpaRepository<FlightInstanceCabin, Long> {

    Page<FlightInstanceCabin> findByFlightInstanceId(Long flightInstanceId, Pageable pageable);

    FlightInstanceCabin findByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId);
}
