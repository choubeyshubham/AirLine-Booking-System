package in.choubeyshubham.payload.response;


import in.choubeyshubham.payload.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String jwt;
    public String message;
    public String title;
    public UserDTO user;


}
