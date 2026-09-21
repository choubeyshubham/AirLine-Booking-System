package in.choubeyshubham.flightOpsService.controller;


import in.choubeyshubham.flightOpsService.service.FlightScheduleService;
import in.choubeyshubham.payload.request.FlightScheduleRequest;
import in.choubeyshubham.payload.response.ApiResponse;
import in.choubeyshubham.payload.response.FlightScheduleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight-schedules")
public class FlightScheduleController {

    private final FlightScheduleService flightScheduleService;

    @PostMapping
    public ResponseEntity<FlightScheduleResponse> createFlightSchedule(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody FlightScheduleRequest flightScheduleRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightScheduleService.createFlightSchedule(
                        userId,flightScheduleRequest
                ));

    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightScheduleResponse> getFlightScheduleById(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(flightScheduleService.getFlightScheduleById(id));
    }

    @GetMapping
    public ResponseEntity<?> getFlightSchedules(
            @RequestHeader("X-User-Id") Long userId
    ) {
        return ResponseEntity.ok(
                    flightScheduleService.getFlightScheduleByAirline(userId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightScheduleResponse> updateFlightSchedule(
            @PathVariable Long id,
            @RequestBody FlightScheduleRequest request) throws Exception {



        return ResponseEntity.ok(flightScheduleService.updateFlightSchedule(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFlightSchedule(@PathVariable Long id) throws Exception {
        flightScheduleService.deleteFlightSchedule(id);
        ApiResponse apiResponse = new ApiResponse("schedule removed success");
        return ResponseEntity.ok(apiResponse);
    }
}

