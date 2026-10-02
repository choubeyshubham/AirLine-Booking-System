package in.choubeyshubham.seatservice.controller;


import in.choubeyshubham.payload.request.SeatMapRequest;
import in.choubeyshubham.payload.response.ApiResponse;
import in.choubeyshubham.payload.response.SeatMapResponse;
import in.choubeyshubham.seatservice.service.SeatMapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seat-maps")
public class SeatMapController {

    private final SeatMapService seatMapService;


    @PostMapping
    public ResponseEntity<SeatMapResponse>createSeatMap(
            @Valid @RequestBody SeatMapRequest seatMapRequest,
            @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        seatMapService.createSeatMap(userId,seatMapRequest)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatMapResponse> getSeatMapById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(seatMapService.getSeatMapById(id));
    }

    @GetMapping("/cabin-class/{cabinClassId}")
    public ResponseEntity<SeatMapResponse> getSeatMapsByCabinClass(
            @PathVariable Long cabinClassId) {
        SeatMapResponse responses = seatMapService.getSeatMapByCabinClass(cabinClassId);
        return ResponseEntity.ok(responses);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SeatMapResponse> updateSeatMap(
            @PathVariable Long id,
            @RequestBody SeatMapRequest request) throws Exception {
        return ResponseEntity.ok(seatMapService.updateSeatMap(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSeatMap(@PathVariable Long id) throws Exception {
        seatMapService.deleteSeatMap(id);
        ApiResponse response = new ApiResponse("Seat Map Deleted");
        return ResponseEntity.ok(response);
    }
}

