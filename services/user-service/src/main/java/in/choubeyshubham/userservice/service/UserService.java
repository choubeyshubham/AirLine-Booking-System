package in.choubeyshubham.userservice.service;

import in.choubeyshubham.exception.UserException;
import in.choubeyshubham.payload.dto.UserDTO;
import in.choubeyshubham.userservice.model.User;

import java.util.List;

public interface UserService {

    UserDTO getUserByEmail(String email) throws UserException;
    UserDTO getUserById(Long id) throws UserException;
    List<User> getUsers() throws UserException;
}
