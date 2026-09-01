package in.choubeyshubham.flightOpsService.service.Impl;


import in.choubeyshubham.event.FlightInstanceCreatedEvent;
import in.choubeyshubham.flightOpsService.client.AirlineClient;
import in.choubeyshubham.flightOpsService.client.LocationClient;
import in.choubeyshubham.flightOpsService.event.FlightInstanceEventProducer;
import in.choubeyshubham.flightOpsService.mapper.FlightInstanceMapper;
import in.choubeyshubham.flightOpsService.model.Flight;
import in.choubeyshubham.flightOpsService.model.FlightInstance;
import in.choubeyshubham.flightOpsService.repository.FlightInstanceRepository;
import in.choubeyshubham.flightOpsService.repository.FlightRepository;
import in.choubeyshubham.flightOpsService.service.FlightInstanceService;
import in.choubeyshubham.payload.request.FlightInstanceRequest;
import in.choubeyshubham.payload.response.AircraftResponse;
import in.choubeyshubham.payload.response.AirlineResponse;
import in.choubeyshubham.payload.response.AirportResponse;
import in.choubeyshubham.payload.response.FlightInstanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightInstanceServiceImpl implements FlightInstanceService {

    private final FlightRepository flightRepository;
    private final FlightInstanceRepository flightInstanceRepository;
    private final AirlineClient airlineClient;
    private final LocationClient locationClient;
    private final FlightInstanceEventProducer flightInstanceEventProducer;

    @Override
    public FlightInstanceResponse createFlightInstance(Long userId, FlightInstanceRequest request) throws Exception {

//        fetch airline by owner id
        AirlineResponse airlineResponse=airlineClient.getAirlineByOwner(userId);
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(
                ()-> new Exception("Flight Not Found!")
        );

//      get aircraft data from airline core service
        AircraftResponse aircraft=airlineClient.getAircraftById(flight.getAircraftId());
        FlightInstance flightInstance= FlightInstanceMapper.toEntity(request, flight);
        flightInstance.setTotalSeats(aircraft.getTotalSeats());
        flightInstance.setAvailableSeats(aircraft.getTotalSeats());

        FlightInstance saved=flightInstanceRepository.save(flightInstance);

//        create seat instances
//        publish kafka event, seat service consume that and create seat instance
        FlightInstanceCreatedEvent event=FlightInstanceCreatedEvent.builder()
                .flightInstanceId(flightInstance.getId())
                .aircraftId(flight.getAircraftId())
                .flightId(flight.getId())
                .build();

        flightInstanceEventProducer.sendFlightInstanceCreated(event);


        return convertToFlightInstanceResponse(saved);
    }

    @Override
    public FlightInstanceResponse getFlightInstanceById(Long id) throws Exception {
        FlightInstance flightInstance=flightInstanceRepository.findById(id).orElseThrow(
                ()-> new Exception("flight instance not found with id "+id)
        );
        return convertToFlightInstanceResponse(flightInstance);
    }

    @Override
    public Page<FlightInstanceResponse> getByAirlineId(Long userId,
                                                       Long departureAirportId,
                                                       Long arrivalAirportId,
                                                       Long flightId,
                                                       LocalDate onDate, Pageable pageable) {
//      fetch by owner id
        AirlineResponse airlineResponse=airlineClient.getAirlineByOwner(userId);
        LocalDateTime start = onDate!=null? onDate.atStartOfDay():null;
        LocalDateTime end=onDate!=null? onDate.plusDays(1).atStartOfDay():null;

        return flightInstanceRepository.findByAirlineId(
                airlineResponse.getId(),
                departureAirportId,
                arrivalAirportId,
                flightId,
                start,
                end,
                pageable
        ).map(this::convertToFlightInstanceResponse);
    }

    //11L:59
    @Override
    public FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest request) throws Exception {
        FlightInstance existing =flightInstanceRepository.findById(id).orElseThrow(
                ()->new Exception("flight instance not found")
        );
        FlightInstanceMapper.updateEntity(request,existing);
        return convertToFlightInstanceResponse(flightInstanceRepository.save(existing));
    }

    @Override
    public void deleteFlightInstance(Long id) throws Exception {
        FlightInstance existing =flightInstanceRepository.findById(id).orElseThrow(
                ()->new Exception("flight instance not found")
        );
        flightInstanceRepository.delete(existing);
    }

    private FlightInstanceResponse convertToFlightInstanceResponse(FlightInstance flightInstance) {
//        service to service communication
        AirlineResponse airline= airlineClient.getAirlineById(flightInstance.getAirlineId());
        AirportResponse departureAirport= locationClient
                .getAirportById(flightInstance.getDepartureAirportId());
        AirportResponse arrivalAirport= locationClient
                .getAirportById(flightInstance.getArrivalAirportId());
        AircraftResponse aircraftResponse=airlineClient.getAircraftById(
                flightInstance.getFlight().getAircraftId());

        return FlightInstanceMapper.toResponse(
                flightInstance,
                aircraftResponse,
                airline,
                departureAirport,
                arrivalAirport
        );
    }
}
