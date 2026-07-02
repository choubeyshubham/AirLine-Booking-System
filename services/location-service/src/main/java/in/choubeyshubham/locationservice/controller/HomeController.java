package in.choubeyshubham.locationservice.controller;


import in.choubeyshubham.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


//    ApiResponse apiResponse;

    @GetMapping()
    public ApiResponse HomeController(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Hello this is api response in home controller");


        return apiResponse;
    }




}
