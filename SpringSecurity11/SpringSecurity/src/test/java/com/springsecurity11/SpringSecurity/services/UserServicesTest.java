package com.springsecurity11.SpringSecurity.services;

import com.springsecurity11.SpringSecurity.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServicesTest {

    @Test
    @Disabled
    public void testAdd(){
        assertEquals(4,2+2);
    }

    @Autowired
    private UserRepository userRepository;
    @Test
    @Disabled
    public  void testFindByUserName(){
       assertNotNull(userRepository.findByUserName("rana"));
        assertTrue(3>2);
    }


    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4"
    })
    public  void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }



}
