package com.SoftwareEngineering.TraineeshipApp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Logbook;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.LogbookMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.StudentMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;
import com.SoftwareEngineering.TraineeshipApp.services.student.StudentServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceImplTest {
    @Mock
    private StudentMapper studentMapper;
    
    @Mock
    private TraineeshipPositionsMapper positionsMapper;
    
    @Mock
    private LogbookMapper logbookMapper;
    
    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveProfile_ShouldReturnStudent() {
        // Arrange
        Student student = new Student();
        student.setUsername("testUser");
        when(studentMapper.findByUsername("testUser")).thenReturn(student);

        // Act
        Student result = studentService.retrieveProfile("testUser");

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void applyForATraineeship_ShouldUpdateLookingForTraineeship() {
        // Arrange
        Student student = new Student();
        student.setUsername("testUser");
        when(studentMapper.findByUsername("testUser")).thenReturn(student);

        // Act
        studentService.applyForATraineeship(student);

        // Assert
        assertTrue(student.isLookingForTraineeship());
        verify(studentMapper).save(student);
    }

    @Test
    void saveProfile_ShouldSaveStudentWithExistingId() {
        // Arrange
        Student student = new Student();
        student.setUsername("testUser");
        student.setStudentId(1);

        when(studentMapper.findByUsername("testUser")).thenReturn(student);

        // Act
        studentService.saveProfile(student);

        // Assert
        verify(studentMapper).save(student);
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"STUDENT"})
    void saveLogbook_ShouldSaveLogbookIfTraineeshipExists() {
        // Arrange
        Student student = new Student();
        TraineeshipPosition position = new TraineeshipPosition();
        Logbook logbook = new Logbook();
        
        student.setAssignedTraineeship(position);
        student.getAssignedTraineeship().setDescription("Hello, this a description");

        // Act
        studentService.saveLogbook(logbook, student);

        // Assert
        assertEquals(position, logbook.getTraineeshipPosition());
        verify(logbookMapper).save(logbook);
        verify(positionsMapper).save(position);
    }

    @Test
    void saveLogbook_ShouldThrowExceptionIfNoTraineeship() {
        // Arrange
        Student student = new Student();
        Logbook logbook = new Logbook();

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            studentService.saveLogbook(logbook, student);
        });
        assertEquals("Student does not have an assigned traineeship position.", exception.getMessage());
    }

    @Test
    void saveUsernameAndId_ShouldSetUsernameAndId() {
        // Arrange
        Student student = new Student();
        UserDetails userDetails = new User("testUser", "password", new ArrayList<>());
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication().getPrincipal()).thenReturn(userDetails);

        // Act
        studentService.saveUsernameAndId(student);

        // Assert
        assertEquals("testUser", student.getUsername());
    }
}
