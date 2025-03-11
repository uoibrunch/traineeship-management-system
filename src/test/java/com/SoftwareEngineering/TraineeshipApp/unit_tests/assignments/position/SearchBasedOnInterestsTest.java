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

import com.SoftwareEngineering.TraineeshipApp.assignments.position.SearchBasedOnInterests;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

@SpringBootTest
public class SearchBasedOnInterestsTest {

    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private SearchBasedOnInterests searchBasedOnInterests;

    private Student student;
    private TraineeshipPosition position1;
    private TraineeshipPosition position2;
    private TraineeshipPosition position3;
    private TraineeshipPosition position4;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setUsername("Marios");
        student.setSkills("Java, Spring, Python, Data Science");
        student.setInterests("AI, Machine Learning, Data Science");

        position1 = new TraineeshipPosition();
        position1.setId(1);
        position1.setSkills("Java, Spring");
        position1.setTopics("AI, Machine Learning");
        position1.setIsAssigned(false);

        position2 = new TraineeshipPosition();
        position2.setId(2);
        position2.setSkills("Python, Data Science");
        position2.setTopics("AI, Machine Learning");
        position2.setIsAssigned(false);

        position3 = new TraineeshipPosition();
        position3.setId(3);
        position3.setSkills("Java, JavaScript");
        position3.setTopics("Web Development");
        position3.setIsAssigned(false);

        position4 = new TraineeshipPosition();
        position4.setId(4);
        position4.setSkills("Java, Spring, Python");
        position4.setTopics("Data Science, Machine Learning");
        position4.setIsAssigned(false);
    }

    @Test
    void testSearch_WithMatchingInterests_AndSkills() {
        //assuming threshold is 0.6

        when(studentMapper.findByUsername("Marios")).thenReturn(student);

        when(positionsMapper.findAll()).thenReturn(Arrays.asList(position1, position2, position3, position4));
        
        List<TraineeshipPosition> result = searchBasedOnInterests.search("Marios");

        assertEquals(3, result.size()); 

        assertTrue(result.contains(position1));

        assertTrue(result.contains(position2));

    }

    @Test
    void testSearch_WithMatchingInterests_ButMissingSkills() {
       
        student.setSkills("Java, Spring");

        
        when(studentMapper.findByUsername("Marios")).thenReturn(student);

        when(positionsMapper.findAll()).thenReturn(Arrays.asList(position1, position2, position3, position4));

        
        List<TraineeshipPosition> result = searchBasedOnInterests.search("Marios");

        
        assertEquals(1, result.size());
        assertTrue(result.contains(position1));
        assertFalse(result.contains(position2)); 
        assertFalse(result.contains(position3));
        assertFalse(result.contains(position4));
    }


    @Test
    void testSearch_WithNoMatchingInterests() {
       
        student.setInterests("Web Development, JavaScript");

       
        when(studentMapper.findByUsername("Marios")).thenReturn(student);
        when(positionsMapper.findAll()).thenReturn(Arrays.asList(position1, position2, position3, position4));

        
        List<TraineeshipPosition> result = searchBasedOnInterests.search("Marios");

        
        assertTrue(result.isEmpty());
    }

}
