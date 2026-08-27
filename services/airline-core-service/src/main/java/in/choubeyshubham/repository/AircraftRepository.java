package in.choubeyshubham.repository;


import in.choubeyshubham.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    List<Aircraft> findByAirlineId(Long airlineId);
    boolean existsByCode(String code);
    Aircraft findByIdAndAirlineId(Long id, Long airlineId);
}
