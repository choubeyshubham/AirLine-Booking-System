package in.choubeyshubham.seatservice.service;

import in.choubeyshubham.payload.response.SeatResponse;

import java.util.List;

public interface SeatService {
    void generateSeats(Long seatMapId) throws Exception;
    List<SeatResponse> getAll();
    SeatResponse updateSeats(Long seatId, SeatRequest request);
}
