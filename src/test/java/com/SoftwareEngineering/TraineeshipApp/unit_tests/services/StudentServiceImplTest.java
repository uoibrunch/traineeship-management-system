package com.SoftwareEngineering.TraineeshipApp.unit_tests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Logbook;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.mappers.StudentMapper;
import com.SoftwareEngineering.TraineeshipApp.services.student.StudentServiceImpl;

@SpringBootTest
@TestPropertySource( locations = "classpath:application.properties")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentServiceImplTest {

    @Autowired
    private StudentMapper studentMapper;
    
    
    @Autowired
    private StudentServiceImpl studentService;

    private LocalDate localDate;
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

        Student retrievedProfile = studentMapper.findByUsername("testUserNick");

        assertEquals("testNick", retrievedProfile.getStudentName());
        assertEquals("5155", retrievedProfile.getAM());
        assertEquals("Xanthi", retrievedProfile.getPreferredLocation());
        assertEquals("Coding", retrievedProfile.getInterests());
        assertEquals("Pentesting", retrievedProfile.getSkills());
    
    }

    @Test
    void applyForATraineeship_ShouldUpdateLookingForTraineeship() {
        
        Student retrievedProfile = studentMapper.findByUsername("testUserNick");

        assertEquals(true, retrievedProfile.isLookingForTraineeship());

    }

    @Test
    void saveProfile_ShouldSaveStudentWithExistingId() {
        

        Student retrievedProfile = studentMapper.findByUsername("testUserNick");
        retrievedProfile.setSkills("ChangedSkills");
      
        studentService.saveProfile(retrievedProfile);
    }

     @Test
     void saveLogbook_ShouldSaveLogbookIfTraineeshipExists() {
        
        Student retrievedProfile = studentMapper.findByUsername("nick");
        Logbook logbook= new Logbook();

        logbook.assignDate(localDate);
        logbook.assignDescription("This is a test");
        
        studentService.saveLogbook(logbook, retrievedProfile);
     }
         

}
