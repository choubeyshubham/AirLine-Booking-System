package in.choubeyshubham.flightOpsService.service;



public interface FlightInstanceService {

    FlightInstanceResponse createFlightInstance(
            Long userId,
            FlightInstanceRequest request
    ) throws Exception;

    FlightInstanceResponse getFlightInstanceById(Long id) throws Exception;

    Page<FlightInstanceResponse> getByAirlineId(Long userId,
                                                Long departureAirportId,
                                                Long arrivalAirportId,
                                                Long flightId,
                                                LocalDate onDate,
                                                Pageable pageable);

    FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest request) throws Exception;
    void deleteFlightInstance(Long id) throws Exception;
}

