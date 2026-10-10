package in.choubeyshubham.ancillaryservice.controller;

import in.choubeyshubham.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<ApiResponse> HomeController() {
        ApiResponse apiResponse = new ApiResponse("" +
                "Ancillary Service manages add-on products such as meals, " +
                "flight-specific ancillaries, and insurance coverage."
        );
        return ResponseEntity.ok(apiResponse);
    }
}
