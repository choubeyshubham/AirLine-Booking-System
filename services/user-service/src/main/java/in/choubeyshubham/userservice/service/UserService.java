package in.choubeyshubham.userservice.service;

import in.choubeyshubham.userservice.model.User;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email) throws Exception; // UserException;
    User getUserById(Long id) throws Exception ;// UserException;
    List<User> getUsers() throws Exception ;//UserException;
}
