package com.example.user.service;

import com.example.user.DTO.UserDTO;
import com.example.user.Enums.Status;
import com.example.user.Exceptions.NotFoundException;
import com.example.user.model.UserEntity;
import com.example.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;

    public UserDTO createUser(UserEntity user){
        return new UserDTO(repo.save(user));
    }
    //hardcode the date
    public List<UserDTO> getAllUsers(){
        return repo
                .findAll()
                .stream()
                .filter(user -> !user.isDeleted())
                .map(UserDTO::new)
                .toList();
    }
    //    show only not deleted fields
//    exception

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

        //    exception, pagination, search
    }

// jwt authentication
    public void softDelete(Integer id){
        repo.findById(id).map(user -> {
            user.setStatus(Status.INACTIVE);
            user.setDeleted(true);
            return repo.save(user);
        });
    }

    public void hardDelete(Integer id){
        repo.deleteById(id);
    }

}
