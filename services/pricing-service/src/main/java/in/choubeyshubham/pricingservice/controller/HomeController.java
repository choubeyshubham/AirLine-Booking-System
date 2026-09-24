package in.choubeyshubham.pricingservice.controller;


import in.choubeyshubham.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @GetMapping()
    public ApiResponse HomeController(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("hello everyone im pricing service of airline microservices, " +
                "Pricing Service manages fares, fare rules, " +
                "and baggage policies. " +
                "It is responsible for price calculation and pricing rules.");
        return apiResponse;
    }
}