package in.choubeyshubham.userservice.service.Impl;

import in.choubeyshubham.enums.UserRole;
import in.choubeyshubham.userservice.model.User;
import in.choubeyshubham.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String @NonNull ... args) {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        String adminUsername = "shubhamchoubeymv@live.com";

        if (userRepository.findByEmail(adminUsername)==null) {
            User adminUser = new User();

            adminUser.setPassword(passwordEncoder.encode("shubham96"));
            adminUser.setFullName("Shubham");
            adminUser.setEmail(adminUsername);
            adminUser.setRole(UserRole.ROLE_SYSTEM_ADMIN);

            User admin=userRepository.save(adminUser);
        }
    }




}
