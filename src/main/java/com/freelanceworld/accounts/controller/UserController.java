package com.freelanceworld.accounts.controller;

import com.freelanceworld.accounts.domain.model.User;
import com.freelanceworld.accounts.domain.repository.UserRepository;
import com.freelanceworld.accounts.resource.user.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Operation(tags = "User")
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convertToResource(user.get()), HttpStatus.OK);
    }

    private UserResource convertToResource(User entity){
       return mapper.map(entity, UserResource.class);
    }


}
