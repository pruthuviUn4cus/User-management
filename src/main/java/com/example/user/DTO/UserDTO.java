package com.example.user.DTO;

import com.example.user.model.UserEntity;
import lombok.Data;

@Data
public class UserDTO {
    private String firstName;
    private String userName;
    private UserEntity.Role role;
    private UserEntity.Status status;

    public UserDTO(UserEntity user) {
        this.firstName = user.getFirstName();
        this.userName = user.getUsername();
        this.role = user.getRoles();
        this.status = user.getStatus();
    }
}
