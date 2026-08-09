package in.choubeyshubham.service.impl;

import in.choubeyshubham.enums.AirlineStatus;
import in.choubeyshubham.payload.request.AirlineRequest;
import in.choubeyshubham.payload.response.AirlineDropdownItem;
import in.choubeyshubham.payload.response.AirlineResponse;
import in.choubeyshubham.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {


    @Override
    public AirlineResponse createAirline(AirlineRequest airlineRequest, Long ownerId) {
        return null;
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) {
        return null;
    }

    @Override
    public AirlineResponse getAirlineById(Long id) {
        return null;
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        return null;
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest airlineRequest, Long ownerId) {
        return null;
    }

    @Override
    public void deleteAirline(Long id, Long ownerId) {

    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) {
        return null;
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown() {
        return List.of();
    }
}
