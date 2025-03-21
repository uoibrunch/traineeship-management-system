package com.SoftwareEngineering.TraineeshipApp.all_kind_of_tests.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.services.user.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
    }

    @Test
    public void registerPage_ShouldContainUserModelAttribute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signup"))
                .andExpect(model().attributeExists("user")) 
                .andExpect(model().attribute("user", instanceOf(User.class))); 
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void registerUser_NewUser_ShouldAddSuccessMessageToModel() throws Exception {

        when(userService.isUserPresent(any(User.class))).thenReturn(false); /* If no user is found in the system, then return false */

        mockMvc.perform(MockMvcRequestBuilders.post("/save")
                .flashAttr("user", user)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signin"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attribute("successMessage", "User registered successfully!"));

        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void registerUser_ExistingUser_ShouldShowErrorMessage() throws Exception {
        when(userService.isUserPresent(any(User.class))).thenReturn(true); /* If no user is found in the system, then return true */

        mockMvc.perform(MockMvcRequestBuilders.post("/save")
                .flashAttr("user", user)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signin"))
                .andExpect(model().attributeExists("successMessage")) 
                .andExpect(model().attribute("successMessage", "User already registered!")); 

        verify(userService, never()).saveUser(any(User.class));
    }
}