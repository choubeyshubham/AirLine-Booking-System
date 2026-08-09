package in.choubeyshubham.service;

import in.choubeyshubham.enums.AirlineStatus;
import in.choubeyshubham.model.Airline;
import in.choubeyshubham.payload.request.AirlineRequest;
import in.choubeyshubham.payload.response.AirlineDropdownItem;
import in.choubeyshubham.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.util.List;

public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest airlineRequest,Long ownerId);

    AirlineResponse getAirlineByOwner(Long ownerId);

    AirlineResponse getAirlineById(Long id);

    Page<AirlineResponse> getAllAirlines(Pageable pageable);

    AirlineResponse updateAirline(AirlineRequest airlineRequest,Long ownerId);

    void deleteAirline(Long id,Long ownerId);

    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status);

    List<AirlineDropdownItem> getAirlineDropdown();



}
