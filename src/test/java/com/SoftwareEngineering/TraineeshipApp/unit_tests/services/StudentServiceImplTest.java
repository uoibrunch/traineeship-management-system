package com.SoftwareEngineering.TraineeshipApp.unit_tests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.TestPropertySource;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.mappers.LogbookMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.StudentMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;
import com.SoftwareEngineering.TraineeshipApp.services.student.StudentServiceImpl;

@SpringBootTest
@TestPropertySource( locations = "classpath:application.properties")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentServiceImplTest {

    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private TraineeshipPositionsMapper positionsMapper;
    
    @Autowired
    private LogbookMapper logbookMapper;
    
    @Autowired
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Student student = new Student();
        student.setUsername("testUserNick");
        student.setStudentId(2);
        student.setStudentName("testNick");
        student.setAM("5155");
        student.setPreferredLocation("Xanthi");
        student.setInterests("Coding");
        student.setSkills("Pentesting");
        student.setLookingForTraineeship(true);

        studentService.saveProfile(student);

    }

    @Test
    void retrieveProfile_ShouldReturnStudent() {
        
         //User user = new User("testUserNick", "123", new ArrayList<>());

        Student retrievedProfile = studentMapper.findByUsername("testUserNick");

        assertEquals("testNick", retrievedProfile.getStudentName());
        assertEquals("5155", retrievedProfile.getAM());
        assertEquals("Xanthi", retrievedProfile.getPreferredLocation());
        assertEquals("Coding", retrievedProfile.getInterests());
        assertEquals("Pentesting", retrievedProfile.getSkills());


    
    }

    @Test
    void applyForATraineeship_ShouldUpdateLookingForTraineeship() {
        // Arrange


        Student retrievedProfile = studentMapper.findByUsername("testUserNick");

        assertEquals(true, retrievedProfile.isLookingForTraineeship());

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

    // @Test
    // @WithMockUser(username = "testUser", roles = {"STUDENT"})
    // void saveLogbook_ShouldSaveLogbookIfTraineeshipExists() {
    //     // Arrange
    //     Student student = new Student();
    //     TraineeshipPosition position = new TraineeshipPosition();
    //     Logbook logbook = new Logbook();
        
    //     student.setAssignedTraineeship(position);
    //     student.getAssignedTraineeship().setDescription("Hello, this a description");

    //     // Act
    //     studentService.saveLogbook(logbook, student);

    //     // Assert
    //     assertEquals(position, logbook.getTraineeshipPosition());
    //     verify(logbookMapper).save(logbook);
    //     verify(positionsMapper).save(position);
    // }

    // @Test
    // void saveLogbook_ShouldThrowExceptionIfNoTraineeship() {
    //     // Arrange
    //     Student student = new Student();
    //     Logbook logbook = new Logbook();

    //     // Act & Assert
    //     Exception exception = assertThrows(IllegalStateException.class, () -> {
    //         studentService.saveLogbook(logbook, student);
    //     });
    //     assertEquals("Student does not have an assigned traineeship position.", exception.getMessage());
    // }

    // @Test
    // void saveUsernameAndId_ShouldSetUsernameAndId() {
    //     // Arrange
    //     Student student = new Student();
    //     UserDetails userDetails = new User("testUser", "password", new ArrayList<>());
    //     SecurityContext securityContext = mock(SecurityContext.class);
    //     SecurityContextHolder.setContext(securityContext);
    //     when(securityContext.getAuthentication().getPrincipal()).thenReturn(userDetails);

    //     // Act
    //     studentService.saveUsernameAndId(student);

    //     // Assert
    //     assertEquals("testUser", student.getUsername());
    // }
}
