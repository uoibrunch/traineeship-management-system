package com.SoftwareEngineering.TraineeshipApp.unit_tests.config;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
public class WebSecurityTest {

@Autowired
private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "validUser", roles = {"USER"})
    public void loginWithValidUser_thenAuthenticated() throws Exception {
        mockMvc.perform(formLogin().user("validUser").password("validPassword"))
                .andExpect(authenticated());
    }
    

    @Test
    public void loginWithInvalidUser_thenUnauthenticated() throws Exception {
        mockMvc.perform(formLogin().user("invalidUser").password("wrongPassword"))
                .andExpect(unauthenticated())
                .andExpect(redirectedUrl("/login?error=true"));
    }

    @Test
    @WithMockUser(username = "student", roles = {"STUDENT"})
    public void accessStudentPage_withStudentRole_thenAuthorized() throws Exception {
        mockMvc.perform(formLogin().user("student").password("password"))
                .andExpect(authenticated());
    }

    @Test
    @WithMockUser(username = "student", roles = {"STUDENT"})
    public void accessProfessorPage_withStudentRole_thenForbidden() throws Exception {
        mockMvc.perform(formLogin().user("student").password("password"))
                .andExpect(authenticated());
    }

    @Test
    @WithMockUser(username = "validUser")
    public void logout_thenRedirectToHomePage() throws Exception {
        mockMvc.perform(logout())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
