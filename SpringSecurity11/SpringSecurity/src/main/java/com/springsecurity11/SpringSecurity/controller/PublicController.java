package com.springsecurity11.SpringSecurity.controller;

import com.springsecurity11.SpringSecurity.entity.User;
import com.springsecurity11.SpringSecurity.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/check")
    public String checkAll(){
        return "all is okay";
    }
    @PostMapping("/create-user")
    public boolean createUser(@RequestBody User user){
        userServices.saveNewUser(user);
        return true;
    }
}
