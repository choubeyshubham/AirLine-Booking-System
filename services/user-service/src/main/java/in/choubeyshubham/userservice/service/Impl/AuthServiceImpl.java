package in.choubeyshubham.userservice.service.Impl;

import in.choubeyshubham.enums.UserRole;
import in.choubeyshubham.payload.dto.UserDTO;
import in.choubeyshubham.payload.response.AuthResponse;
import in.choubeyshubham.userservice.config.JwtProvider;
import in.choubeyshubham.userservice.mapper.UserMapper;
import in.choubeyshubham.userservice.model.User;
import in.choubeyshubham.userservice.repository.UserRepository;
import in.choubeyshubham.userservice.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;



    //---------------
    @Override
    public AuthResponse login(String email, String password) {
        return null;
    }

    //--------------


//    private final CustomUserDetailsService customUserDetailsService;

/*
    Steps:
        1. Check if email already exists
        2. Encode password using BCrypt
        3. Save user in database
        4. Generate JWT token
        5. Return token and user information
*/
    @Override
    public AuthResponse signup(UserDTO req) throws Exception {
        User existingUser = userRepository.findByEmail(req.getEmail());
        if (existingUser != null) {
            throw new Exception("Email already registered");
        }

        if (req.getRole() == UserRole.ROLE_SYSTEM_ADMIN) {
            throw new Exception("Cannot register as SYSTEM_ADMIN");
        }

        User createdUser = new User();
        createdUser.setEmail(req.getEmail());
        createdUser.setPassword(passwordEncoder.encode(req.getPassword()));
        createdUser.setPhone(req.getPhone());
        createdUser.setFullName(req.getFullName());
        createdUser.setRole(req.getRole());
        createdUser.setLastLogin(LocalDateTime.now());


        User savedUser = userRepository.save(createdUser);

        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), savedUser.getPassword()
        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication, savedUser.getId());

        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome " + savedUser.getFullName());
        response.setMessage("Registration successful");
        response.setUser(UserMapper.toDTO(savedUser));
//        response.setJwt(jwt);
        return response;
    }

  /*
    Steps:
        1. Load user by email
        2. Compare password with BCrypt
        3. Update `lastLogin` time
        4. Generate JWT token
        5. Return token and user information

    @Override
    public AuthResponse login(String email, String password) throws UserException {
        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(email);
        String token = jwtProvider.generateToken(authentication, user.getId());

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setTitle("Login successful");
        response.setMessage("Welcome back " + user.getFullName());
        response.setJwt(token);
        response.setUser(UserMapper.toDTO(user));
        return response;
    }

    private Authentication authenticate(String email, String password) throws UserException {
        UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(email);
        if (userDetails == null) {
          throw new UserException("User not found with email: " + email);
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(
                email, null, userDetails.getAuthorities());
    } */
}
