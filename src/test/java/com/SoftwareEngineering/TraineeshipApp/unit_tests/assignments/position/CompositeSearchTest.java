package com.SoftwareEngineering.TraineeshipApp.unit_tests.assignments.position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.SoftwareEngineering.TraineeshipApp.assignments.position.CompositeSearch;
import com.SoftwareEngineering.TraineeshipApp.assignments.position.SearchBasedOnInterests;
import com.SoftwareEngineering.TraineeshipApp.assignments.position.SearchBasedOnLocation;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CompositeSearchTest {

    @Mock
    private SearchBasedOnLocation searchBasedOnLocation;

    @Mock
    private SearchBasedOnInterests searchBasedOnInterests;

    @InjectMocks
    private CompositeSearch compositeSearch;

    private Student student;
    private TraineeshipPosition position1;
    private TraineeshipPosition position2;
    private TraineeshipPosition position3;
    private Company company1;
    private Company company2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        student = new Student();
        student.setUsername("Marios");
        student.setSkills("Java, Spring, Python");
        student.setInterests("AI, Machine Learning, Data Science");
        student.setPreferredLocation("SKG");


        company1 = new Company();
        company1.setCompanyName("Apple");
        company1.setCompanyLocation("SKG");

        company2 = new Company();
        company2.setCompanyName("Google");
        company2.setCompanyLocation("Athens");

        position1 = new TraineeshipPosition();
        position1.setId(1);
        position1.setSkills("Java, Spring");
        position1.setTopics("AI, Machine Learning");
        position1.setIsAssigned(false);
        position1.setCompany(company1);

        position2 = new TraineeshipPosition();
        position2.setId(2);
        position2.setSkills("Python, Data Science");
        position2.setTopics("AI, Machine Learning");
        position2.setIsAssigned(false);
        position2.setCompany(company2);

        position3 = new TraineeshipPosition();
        position3.setId(3);
        position3.setSkills("Java, Spring");
        position3.setTopics("Data Science, Machine Learning");
        position3.setIsAssigned(false);
        position3.setCompany(company1);
    }

    @Test
    void testSearch_WithMatchingLocationAndInterests() {

        when(searchBasedOnLocation.search("Marios")).thenReturn(Arrays.asList(position1, position3));  
        when(searchBasedOnInterests.search("Marios")).thenReturn(Arrays.asList(position1, position2)); 
       
        List<TraineeshipPosition> result = compositeSearch.search("Marios");

      
        assertEquals(3, result.size()); 
        assertTrue(result.contains(position1));
        assertTrue(result.contains(position2));
        assertTrue(result.contains(position3));
    }

    @Test
    void testSearch_WithNoMatchingLocation() {
        
        when(searchBasedOnLocation.search("Marios")).thenReturn(new ArrayList<>()); 
        when(searchBasedOnInterests.search("Marios")).thenReturn(Arrays.asList(position1, position2));  

        List<TraineeshipPosition> result = compositeSearch.search("Marios");

        assertEquals(2, result.size());
        assertTrue(result.contains(position1));
        assertTrue(result.contains(position2));
    }

    @Test
    void testSearch_WithNoMatchingInterests() {
        
        when(searchBasedOnLocation.search("Marios")).thenReturn(Arrays.asList(position1,position2));  
        when(searchBasedOnInterests.search("Marios")).thenReturn(new ArrayList<>());  


        List<TraineeshipPosition> result = compositeSearch.search("Marios");

        
        assertEquals(2, result.size());
        assertTrue(result.contains(position1));
        assertTrue(result.contains(position2));
    }

    @Test
    void testSearch_WithNoMatchingPositions() {
       
        when(searchBasedOnLocation.search("Marios")).thenReturn(new ArrayList<>());  
        when(searchBasedOnInterests.search("Marios")).thenReturn(new ArrayList<>());  

        
        List<TraineeshipPosition> result = compositeSearch.search("Marios");

        
        assertTrue(result.isEmpty());
    }
}
