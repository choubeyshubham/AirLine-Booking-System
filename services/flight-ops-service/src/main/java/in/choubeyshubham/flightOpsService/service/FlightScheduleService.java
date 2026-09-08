package in.choubeyshubham.flightOpsService.service;

public interface FlightScheduleService {

    FlightScheduleResponse createFlightSchedule(Long userId,
                                                FlightScheduleRequest request
    ) throws Exception;
    FlightScheduleResponse getFlightScheduleById(Long id) throws Exception;
    List<FlightScheduleResponse> getFlightScheduleByAirline(Long userId);
    FlightScheduleResponse updateFlightSchedule(Long id,
                                                FlightScheduleRequest flightScheduleRequest) throws Exception;
    void deleteFlightSchedule(Long id) throws Exception;

}
