package in.choubeyshubham.seatservice.client;

import in.choubeyshubham.payload.response.AirlineResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(name="airline-core-service")
public interface AirlineClient {
    @GetMapping("/api/airlines/admin")
    AirlineResponse getAirlineByOwner(@RequestHeader("X-User-Id") Long userId);


}
