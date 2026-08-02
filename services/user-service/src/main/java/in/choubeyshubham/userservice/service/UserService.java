package in.choubeyshubham.userservice.service;

import in.choubeyshubham.exception.UserException;
import in.choubeyshubham.userservice.model.User;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException;
    List<User> getUsers() throws UserException;
}
