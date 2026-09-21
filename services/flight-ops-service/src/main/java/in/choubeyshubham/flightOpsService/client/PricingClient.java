package in.choubeyshubham.flightOpsService.client;


import in.choubeyshubham.payload.response.FareResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="pricing-service")
public interface PricingClient {

    @GetMapping("/api/fares/lowest/flight/{flightId}/cabin-class/{cabinClassId}")
    FareResponse getLowestFareForFlightAndCabinClass(
            @PathVariable Long flightId,
            @PathVariable Long cabinClassId
    );


}
