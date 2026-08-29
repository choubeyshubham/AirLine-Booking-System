package in.choubeyshubham.controller;


import in.choubeyshubham.payload.request.AircraftRequest;
import in.choubeyshubham.payload.response.AircraftResponse;
import in.choubeyshubham.service.AircraftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aircrafts")
public class AircraftController {
    private final AircraftService aircraftService;


    @PostMapping
    public ResponseEntity<AircraftResponse> createAircraft(
            @Valid @RequestBody AircraftRequest aircraftRequest,
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        AircraftResponse aircraft = aircraftService.createAircraft(aircraftRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                aircraft
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AircraftResponse> getAircraftById(@PathVariable Long id)
            throws Exception {
        return ResponseEntity.ok(aircraftService.getAircraftById(id));
    }

    @GetMapping
    public ResponseEntity<List<AircraftResponse>> listAllAircrafts(
            @RequestHeader("X-User-Id") Long userId) throws Exception {
        return ResponseEntity.ok(aircraftService.listAllAircraftByOwner(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AircraftResponse> updateAircraft(
            @PathVariable Long id,
            @RequestBody AircraftRequest request,
            @RequestHeader("X-User-Id") Long userId) throws Exception {
        return ResponseEntity.ok(aircraftService.updateAircraft(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Long id,
                                               @RequestHeader("X-User-Id") Long userId)
            throws Exception {
        aircraftService.deleteAircraft(id,userId);
        return ResponseEntity.noContent().build();
    }
}
