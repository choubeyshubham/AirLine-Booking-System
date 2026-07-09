package in.choubeyshubham.userservice.service;

import in.choubeyshubham.payload.dto.UserDTO;
import in.choubeyshubham.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String email, String password);
    AuthResponse signup(UserDTO req);



}
