package com.example.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.hibernate.annotations.CreationTimestamp;

import java.rmi.Naming;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name can only contain letters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name can only contain letters")
    private String lastName;

    @NotBlank
    @Size(min = 4, max = 40)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(unique = true)
    private  String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")
    private String password;

    public enum Role{
        ADMIN,USER,GUEST
    }

    @NotNull
    private Role roles;

    @CreationTimestamp
    private LocalDate createdDate;

    public enum Status{
        ACTIVE,INACTIVE
    }
    @NotNull
    private Status status;

    @Builder.Default
    private boolean deleted = false;


}
