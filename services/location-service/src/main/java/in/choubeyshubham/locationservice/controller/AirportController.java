package in.choubeyshubham.locationservice.controller;

import in.choubeyshubham.locationservice.service.AirportService;
import in.choubeyshubham.payload.request.AirportRequest;
import in.choubeyshubham.payload.response.AirportResponse;
import in.choubeyshubham.payload.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(@Valid  @RequestBody AirportRequest airportRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(airportService.createAirport(airportRequest));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirport(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAllAirports() throws Exception {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AirportResponse>> getAirportByCityId( @PathVariable Long cityId) throws Exception {
        return ResponseEntity.ok(airportService.getAirportsByCityId(cityId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id, @Valid  @RequestBody AirportRequest airportRequest) throws Exception {
        return ResponseEntity.ok(airportService.updateAirportById(id, airportRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id) throws Exception {
        airportService.deleteAirportById(id);
        return ResponseEntity.ok(new ApiResponse("Airport deleted successfully"));
    }



}
