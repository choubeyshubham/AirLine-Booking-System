package in.choubeyshubham.service;

import in.choubeyshubham.enums.AirlineStatus;
import in.choubeyshubham.model.Airline;
import in.choubeyshubham.payload.request.AirlineRequest;
import in.choubeyshubham.payload.response.AirlineDropdownItem;
import in.choubeyshubham.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest airlineRequest,Long ownerId);

    AirlineResponse getAirlineByOwner(Long ownerId)  throws Exception;

    AirlineResponse getAirlineById(Long id)  throws Exception;

    Page<AirlineResponse> getAllAirlines(Pageable pageable);

    AirlineResponse updateAirline(AirlineRequest airlineRequest,Long ownerId)  throws Exception;

    void deleteAirline(Long id,Long ownerId)  throws Exception;

    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status)  throws Exception;

    List<AirlineDropdownItem> getAirlineDropdown();



}
