package com.SoftwareEngineering.TraineeshipApp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.mappers.UserMapper;
import com.SoftwareEngineering.TraineeshipApp.services.user.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceImplTest {
    
    @Mock
    private UserMapper userDAO;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_ShouldEncodePasswordAndSaveUser() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("plainPassword");

        when(bCryptPasswordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        // Act
        userService.saveUser(user);

        // Assert
        assertEquals("encodedPassword", user.getPassword());
        verify(userDAO).save(user); // Verify that save() was called
    }

    @Test
    void isUserPresent_ShouldReturnTrue_WhenUserExists() {
        // Arrange
        User user = new User();
        user.setUsername("existingUser");

        when(userDAO.findByUsername("existingUser")).thenReturn(Optional.of(user));

        // Act
        boolean result = userService.isUserPresent(user);

        // Assert
        assertTrue(result);
    }

    @Test
    void isUserPresent_ShouldReturnFalse_WhenUserDoesNotExist() {
        // Arrange
        User user = new User();
        user.setUsername("nonExistingUser");

        when(userDAO.findByUsername("nonExistingUser")).thenReturn(Optional.empty());

        // Act
        boolean result = userService.isUserPresent(user);

        // Assert
        assertFalse(result);
    }

    @Test
    void loadUserByUsername_ShouldReturnUser_WhenUserExists() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");

        when(userDAO.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        User result = (User) userService.loadUserByUsername("testUser");

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void loadUserByUsername_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        when(userDAO.findByUsername("unknownUser")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("unknownUser"));
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");

        when(userDAO.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        User result = userService.findById("testUser");

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void findById_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        when(userDAO.findByUsername("unknownUser")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.findById("unknownUser"));
    }
}
