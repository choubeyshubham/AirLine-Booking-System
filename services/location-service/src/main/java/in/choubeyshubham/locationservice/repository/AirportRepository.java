package in.choubeyshubham.locationservice.repository;

import in.choubeyshubham.locationservice.model.Airport;
import in.choubeyshubham.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository  extends JpaRepository<Airport,Long> {

     Optional<Airport> findByIataCode(String iataCode);

     List<Airport> findByCityId(Long cityId);






}
