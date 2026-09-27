package in.choubeyshubham.pricingservice.repository;

import in.choubeyshubham.pricingservice.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FareRepository extends JpaRepository<Fare, Long> {

    boolean existsByFlightIdAndCabinClassIdAndName(
            Long flightId, Long cabinClassId, String name
    );

    List<Fare>findByFlightIdAndCabinClassId(Long flightId, Long cabinClassId);
    List<Fare> findByFlightIdInAndCabinClassId(List<Long> flightIds, Long cabinClassId);

    boolean existsByFlightIdAndCabinClassIdAndNameAndIdNot(Long flightId,
                                                           Long cabinClassId,
                                                           String name,
                                                           Long id
    );


}
