package in.choubeyshubham.seatservice.controller;

import in.choubeyshubham.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {



    @GetMapping()
    public ApiResponse HomeController(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("hello everyone im Seating service of airline microservices, " +
                "Seating Service manages Seats, seat rules, " +
                "and seating policies. " +
                "It is responsible for seat location and seating rules.");
        return apiResponse;
    }
}