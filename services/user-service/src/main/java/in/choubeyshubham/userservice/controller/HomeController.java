package in.choubeyshubham.userservice.controller;


import in.choubeyshubham.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController(){
        ApiResponse apiResponse = new ApiResponse("Hello to User home Controller");
        return apiResponse;
    }



}
