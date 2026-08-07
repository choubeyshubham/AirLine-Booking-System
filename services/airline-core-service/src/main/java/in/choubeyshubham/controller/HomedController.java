package in.choubeyshubham.controller;

import in.choubeyshubham.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomedController {

    @GetMapping("/api")
    public ApiResponse msg(){
        ApiResponse apiResponse = new ApiResponse("Hello to User home Controller");
        return apiResponse;
    }







}
