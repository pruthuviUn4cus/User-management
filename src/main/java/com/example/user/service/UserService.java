package com.example.user.service;

import com.example.user.DTO.UserDTO;
import com.example.user.Enums.Status;
import com.example.user.Exceptions.DuplicateValueException;
import com.example.user.Exceptions.NotFoundException;
import com.example.user.model.UserEntity;
import com.example.user.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;

    public void checkUnique(UserEntity user){
        repo.findByUsername(user.getUsername()).ifPresent(u-> {
            throw new DuplicateValueException("Username already exists");
        });
        repo.findByEmail(user.getEmail()).ifPresent(u-> {
            throw new DuplicateValueException("Email already exists");
        });
    }

    public UserDTO createUser(UserEntity user){
        checkUnique(user);
        return new UserDTO(repo.save(user));
    }

    public Page<UserDTO> getAllUsers(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return repo.findByDeletedFalse(pageable).map(UserDTO::new);
    }
//    return (Page<UserDTO>) repo
//                .findAll()
//                .stream()
//                .filter(user -> !user.isDeleted())
//                .map(UserDTO::new)
//                .toList();

    public Optional<UserDTO> getUserByID(Integer id){
        return repo
                .findById(id)
                .map(UserDTO::new);
    }

    public UserDTO updateUser(Integer id, UserEntity newUser){
        return repo.findById(id).map(user ->{
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setRoles(newUser.getRoles());
            user.setStatus(newUser.getStatus());
            return new UserDTO(repo.save(user));
        }).orElseThrow( ()-> new NotFoundException("User not found"));


    }

    public void softDelete(Integer id){
        repo.findById(id).map(user -> {
            user.setStatus(Status.INACTIVE);
            user.setDeleted(true);
            return repo.save(user);
        });
    }


}

// jwt authentication
// exception, pagination, search

