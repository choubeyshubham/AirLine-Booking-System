package in.choubeyshubham.seatservice.repository;

import in.choubeyshubham.enums.SeatAvailabilityStatus;

import java.util.List;

public interface SeatInstanceService {

    Double calculateSeatPrice(List<Long> seatInstanceIds);
    SeatInstanceResponse updateSeatInstanceStatus(Long seatInstanceId,
                                                  SeatAvailabilityStatus status);
}
