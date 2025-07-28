package com.example.user.service;

import com.example.user.model.UserEntity;
import com.example.user.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public UserEntity createUser(UserEntity user){
        return repo.save(user);
    }

    public List<UserEntity> getAllUsers(){
        return repo.findAll();
    }

    public Optional<UserEntity> getUserByID(Integer id){
        return repo.findById(id);
    }

    public UserEntity updateUser(Integer id, UserEntity newUser){
        return repo.findById(id).map(user ->{
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setRoles(newUser.getRoles());
            user.setCreatedDate(newUser.getCreatedDate());
            user.setStatus(newUser.getStatus());
            return repo.save(user);
        }).orElse(null);
    }

    public void softDelete(Integer id){
        repo.findById(id).map(user -> {
            user.setStatus(UserEntity.Status.INACTIVE);
            return repo.save(user);
        });
    }

    public void hardDelete(Integer id){
        repo.deleteById(id);
    }

}
