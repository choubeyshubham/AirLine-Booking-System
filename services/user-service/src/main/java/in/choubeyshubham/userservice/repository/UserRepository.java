package in.choubeyshubham.userservice.repository;

import in.choubeyshubham.enums.UserRole;
import in.choubeyshubham.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String username);

    Set<User> findByRole(UserRole role);

}
