package in.choubeyshubham.seatservice.service;

import in.choubeyshubham.enums.SeatAvailabilityStatus;
import in.choubeyshubham.payload.response.SeatInstanceResponse;

import java.util.List;

public interface SeatInstanceService {

    Double calculateSeatPrice(List<Long> seatInstanceIds);
    SeatInstanceResponse updateSeatInstanceStatus(Long seatInstanceId,
                                                  SeatAvailabilityStatus status);
}
