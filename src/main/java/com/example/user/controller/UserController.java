package com.example.user.controller;

import com.example.user.model.UserEntity;
import com.example.user.service.UserService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
        user.setId(null);
        UserEntity createdUser = service.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }
//    @PostMapping
//    public UserEntity createUser(@RequestBody UserEntity user){
//        user.setId(null);
//        return service.createUser(user);
//    }



    @GetMapping
    public List<UserEntity> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserByID(@PathVariable Integer id){
        return service.getUserByID(id);
    }

    @PutMapping
    public UserEntity updateUser(@PathVariable Integer id,@RequestBody UserEntity user){
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.softDelete(id);
    }

    public void hardDelete(@PathVariable Integer id){
        service.hardDelete(id);
    }

}

//DTO