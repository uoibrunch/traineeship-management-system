package com.SoftwareEngineering.TraineeshipApp.unit_tests.assignments.position;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.SoftwareEngineering.TraineeshipApp.assignments.position.SearchBasedOnLocation;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

@SpringBootTest
public class SearchBasedOnLocationTest {

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private SearchBasedOnLocation searchBasedOnLocation;

    private Student student;
    private Company company1;
    private Company company2;
    private TraineeshipPosition position1;
    private TraineeshipPosition position2;
    private TraineeshipPosition position3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setUsername("Marios");
        student.setSkills("Java, Spring, Python");
        student.setPreferredLocation("SKG");

        company1 = new Company();
        company1.setCompanyName("Apple");
        company1.setCompanyLocation("SKG");

        company2 = new Company();
        company2.setCompanyName("Nvidia");
        company2.setCompanyLocation("Xanthi");

        position1 = new TraineeshipPosition();
        position1.setId(1);
        position1.setSkills("Java, Spring");
        position1.setIsAssigned(false);

        position2 = new TraineeshipPosition();
        position2.setId(2);
        position2.setSkills("Python, Data Science");
        position2.setIsAssigned(false);

        position3 = new TraineeshipPosition();
        position3.setId(3);
        position3.setSkills("Java, Pascal");
        position3.setIsAssigned(false);
    }

    @Test
    void testSearch_WithMatchingLocationAndSkills() {
        
        when(studentMapper.findByUsername("Marios")).thenReturn(student);

        
        company1.setPositions(Arrays.asList(position1, position2));
        company2.setPositions(Arrays.asList(position3));

        when(companyMapper.findByCompanyLocation("SKG")).thenReturn(Arrays.asList(company1));

       
        List<TraineeshipPosition> result = searchBasedOnLocation.search("Marios");

       
        assertEquals(1, result.size());
        assertTrue(result.contains(position1));  
        assertFalse(result.contains(position2)); 
    }

    @Test
    void testSearch_WithNoMatchingLocation() {
        
        when(studentMapper.findByUsername("Marios")).thenReturn(student);
        when(companyMapper.findByCompanyLocation("SKG")).thenReturn(new ArrayList<>());  // No companies in "New York"

        
        List<TraineeshipPosition> result = searchBasedOnLocation.search("Marios");

        
        assertTrue(result.isEmpty());
    }

    @Test
    void testSearch_WithMatchingLocation_ButNoMatchingSkills() {
        
        student.setSkills("Java, Spring");

     
        company1.setPositions(Arrays.asList(position2));
        company2.setPositions(Arrays.asList(position3 , position1));

        when(studentMapper.findByUsername("Marios")).thenReturn(student);
        when(companyMapper.findByCompanyLocation("SKG")).thenReturn(Arrays.asList(company1));

        
        List<TraineeshipPosition> result = searchBasedOnLocation.search("Marios");

    
        assertTrue(result.isEmpty());
    }

}
