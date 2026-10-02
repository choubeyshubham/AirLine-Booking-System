package in.choubeyshubham.seatservice.controller;

import in.choubeyshubham.payload.response.SeatResponse;
import in.choubeyshubham.seatservice.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seats")
public class SeatController {
    private final SeatService seatService;

    @GetMapping
    public ResponseEntity<List<SeatResponse>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAll());
    }

}