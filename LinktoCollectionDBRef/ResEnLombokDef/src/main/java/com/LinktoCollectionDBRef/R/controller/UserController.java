package com.LinktoCollectionDBRef.R.controller;

import com.LinktoCollectionDBRef.R.entity.User;
import com.LinktoCollectionDBRef.R.repository.UserRepository;
import com.LinktoCollectionDBRef.R.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<User> getAllUser(){
        return userServices.getAll();
    }

    @PostMapping
    public boolean createUser(@RequestBody User user){
        userServices.saveEntry(user);
        return true;
    }
    @PutMapping("/id/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userServices.findByUserName(userName);
        if(userInDb!=null){
//            userInDb.setUserName(user.getUserName());
//            userInDb.setPassword(user.getPassword());
//            userServices.saveEntry(userInDb);

            userInDb.setUserName(user.getUserName() != null && !user.getUserName().isEmpty() ? user.getUserName() : userInDb.getUserName());
            userInDb.setPassword(user.getPassword() != null && !user.getPassword().isEmpty() ? user.getPassword() : userInDb.getPassword());
            userServices.saveEntry(userInDb);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
