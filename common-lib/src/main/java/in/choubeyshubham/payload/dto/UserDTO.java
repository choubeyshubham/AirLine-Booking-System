package in.choubeyshubham.payload.dto;


import in.choubeyshubham.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private UserRole role;
//    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;



}
