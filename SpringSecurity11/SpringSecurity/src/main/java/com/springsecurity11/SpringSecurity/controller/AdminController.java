package com.springsecurity11.SpringSecurity.controller;

import com.springsecurity11.SpringSecurity.entity.User;
import com.springsecurity11.SpringSecurity.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userServices.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //create a admin
    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        userServices.saveAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
