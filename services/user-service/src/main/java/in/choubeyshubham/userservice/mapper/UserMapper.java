package in.choubeyshubham.userservice.mapper;

import in.choubeyshubham.payload.dto.UserDTO;
import in.choubeyshubham.userservice.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {}

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setLastLogin(user.getLastLogin());
        return dto;
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Set<UserDTO> toDTOSet(Set<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toSet());
    }
}
