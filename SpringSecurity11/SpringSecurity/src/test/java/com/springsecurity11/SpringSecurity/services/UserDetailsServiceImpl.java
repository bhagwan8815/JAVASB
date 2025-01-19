package com.springsecurity11.SpringSecurity.services;

import com.springsecurity11.SpringSecurity.entity.User;
import com.springsecurity11.SpringSecurity.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImpl {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void loadUserByUsernameTest(){
        Object ArrayList;
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("rana").password("xyz").roles(ArrayList<>()).build());


  UserDetails user = userDetailsService.loadUserByUserName("rana");
        Assertions.assertNotNull(user);
    }
}
