package com.example.user.controller;

import com.example.user.DTO.UserDTO;
import com.example.user.model.UserEntity;
import com.example.user.service.UserService;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody UserEntity user){
        user.setId(null);
        UserDTO createdUser = service.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }



    @GetMapping
    public List<UserDTO> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUserByID(@PathVariable Integer id){
        return service.getUserByID(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserEntity user){
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