package com.example.user.controller;

import com.example.user.model.UserEntity;
import com.example.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserEntity createUser(UserEntity user){
        return service.createUser(user);
    }

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping
    public Optional<UserEntity> getUserByID(@PathVariable Integer id){
        return service.getUserByID(id);
    }

    @PutMapping
    public UserEntity updateUser(@PathVariable Integer id, UserEntity user){
        return service.updateUser(id, user);
    }

    @DeleteMapping
    public void deleteUser(@PathVariable Integer id){
        service.softDelete(id);
    }

    public void hardDelete(@PathVariable Integer id){
        service.hardDelete(id);
    }

}
