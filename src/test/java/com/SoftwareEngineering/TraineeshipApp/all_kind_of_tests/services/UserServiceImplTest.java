package com.SoftwareEngineering.TraineeshipApp.all_kind_of_tests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.mappers.UserMapper;
import com.SoftwareEngineering.TraineeshipApp.services.user.UserServiceImpl;

@TestPropertySource( locations = "classpath:application.properties")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;


    @Test
    public void saveUser(){

        Optional <User> retrievedUser1 = userMapper.findById(3);
        userService.saveUser(retrievedUser1.get());

        Optional <User> retrievedUser2 = userMapper.findByUsername("nikou");
        assertEquals(retrievedUser1.get().getUsername(), retrievedUser2.get().getUsername());
    }

    @Test
    public void loadUserByUsername(){
        Optional <User> retrievedUser = userMapper.findByUsername("zarras");
        assertEquals("zarras", retrievedUser.get().getUsername());
    }

    @Test
    public void findById(){
        Optional<User> retrievedUser = userMapper.findById(2);
        assertEquals("zarras", retrievedUser.get().getUsername());
    }

}
