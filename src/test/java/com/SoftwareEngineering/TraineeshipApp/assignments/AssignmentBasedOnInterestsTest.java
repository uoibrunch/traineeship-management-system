package com.SoftwareEngineering.TraineeshipApp.assignments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.SoftwareEngineering.TraineeshipApp.assigns.professor.AssignmentBasedOnInterests;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;



import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class AssignmentBasedOnInterestsTest {

    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private ProfessorMapper professorMapper;

    @InjectMocks
    private AssignmentBasedOnInterests assignmentStrategy;

    private TraineeshipPosition position;
    private Professor professor1;
    private Professor professor2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        position = new TraineeshipPosition();
        position.setId(10);
        position.setTopics("Coding, Hacking, Networking");

        professor1 = new Professor();
        professor1.setProfessorId(24);
        professor1.setInterests("Coding, AI, Verilog");

        professor2 = new Professor();
        professor2.setProfessorId(15);
        professor2.setInterests("Coding, Hacking, AI, Verilog");
    }


    @Test
    void testAssignProfessor() {
        
        when(professorMapper.findAll()).thenReturn(Arrays.asList(professor1, professor2));

        when(positionsMapper.findById(Integer.valueOf(10))).thenReturn(Optional.of(position));

        assignmentStrategy.assign(10);

        verify(positionsMapper, times(1)).save(position);

        assertNotNull(position.getSupervisor());

        assertEquals(professor2, position.getSupervisor()); 

        assertTrue(position.isSupervised());
    }

    @Test
    void testAssignProfessor_NoSuitableProfessor() {
        professor1.setInterests("History, Politics");  
        professor2.setInterests("Math, Physics");  

        when(professorMapper.findAll()).thenReturn(Arrays.asList(professor1, professor2));
        when(positionsMapper.findById(Integer.valueOf(10))).thenReturn(Optional.of(position));

        assignmentStrategy.assign(10);

        verify(positionsMapper, times(1)).save(position);
        assertNotNull(position.getSupervisor());
        assertTrue(position.isSupervised());
    }

    @Test
    void calculateJaccardSimilarity_CorrectCalculation() {
       
        Set<String> set1 = new HashSet<>(Arrays.asList("AI", "Machine Learning"));
        Set<String> set2 = new HashSet<>(Arrays.asList("AI", "Data Science"));

      
        try {
            var method = AssignmentBasedOnInterests.class.getDeclaredMethod("calculateJaccardSimilarity", Set.class, Set.class);
            method.setAccessible(true);  // Bypass private access
            double similarity = (double) method.invoke(assignmentStrategy, set1, set2);

            assertEquals(0.3333, similarity, 0.01);
        } catch (Exception e) {
            fail("Failed to invoke private method calculateJaccardSimilarity: " + e.getMessage());
        }
    }
}
