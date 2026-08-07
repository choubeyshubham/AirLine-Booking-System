package in.choubeyshubham.userservice.service.Impl;


import in.choubeyshubham.exception.UserException;
import in.choubeyshubham.payload.dto.UserDTO;
import in.choubeyshubham.userservice.mapper.UserMapper;
import in.choubeyshubham.userservice.model.User;
import in.choubeyshubham.userservice.repository.UserRepository;
import in.choubeyshubham.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found with email: " + email);
        }
return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws UserException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<User> getUsers() throws UserException {
        return userRepository.findAll();
    }
}
