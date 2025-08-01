package com.example.user.DTO;

import com.example.user.Enums.Roles;
import com.example.user.Enums.Status;
import com.example.user.model.UserEntity;
import lombok.Data;

import java.nio.file.Paths;
import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private LocalDate createdDate;
    private Roles role;
    private Status status;

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createdDate = user.getCreatedDate();
        this.role = user.getRoles();
        this.status = user.getStatus();

    }
}
