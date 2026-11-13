package in.choubeyshubham.ancillaryservice.repository;

import in.choubeyshubham.ancillaryservice.model.FlightCabinAncillary;
import in.choubeyshubham.enums.AncillaryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightCabinAncillaryRepository extends JpaRepository<FlightCabinAncillary, Long> {

    List<FlightCabinAncillary> findByFlightIdAndCabinClassId(Long flightId,
                                                             Long cabinClassId);
    FlightCabinAncillary findByFlightIdAndCabinClassIdAndAncillary_Type(
            Long flightId, Long cabinClassId, AncillaryType ancillaryType
    );

    List<FlightCabinAncillary> findAllByFlightIdAndCabinClassIdAndAncillary_Type(
            Long flightId, Long cabinClassId, AncillaryType ancillaryType
    );
}
