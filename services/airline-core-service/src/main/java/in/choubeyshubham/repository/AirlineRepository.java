package in.choubeyshubham.repository;

import in.choubeyshubham.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

    Optional<Airline> findAirlineByOwnerId(Long id);


}
