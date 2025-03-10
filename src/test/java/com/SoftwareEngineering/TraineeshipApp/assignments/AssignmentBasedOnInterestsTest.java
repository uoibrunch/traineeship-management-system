package com.SoftwareEngineering.TraineeshipApp.assignments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.SoftwareEngineering.TraineeshipApp.assigns.professor.AssignmentBasedOnInterests;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.ProfessorMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;


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

    @BeforeEach
    void setUp() {
        /*MockitoAnnotations.openMocks(this);
        assignmentStrategy = new assignmentStrategy(assignmentStrategy); */ //Ean den ekana @InjectMocks
        MockitoAnnotations.openMocks(this);

        position = new TraineeshipPosition();
        position.setId(10);
        position.setTopics("Coding,HAcking, NetwOrking,HarDware DesiGning");
        positionsMapper.save(position);

        professor1 = new Professor();
        professor1.setProfessorId(24);
        professor1.setInterests("Java, AI, Verilog");
        
    }


    @Test
    void testTraineeshipPositionTrimming(){

        assignmentStrategy.assign(10);

        //TraineeshipPosition retrievedPosition = positionsMapper.findById(10);

        System.out.println("POSITIONS TOPICS: %s" +position.getTopics());
        System.out.println("==================================");
        assertTrue(position.getTopics().equals("codinghackingnetworkinghardwaredesigning"));
    }


    @Test
    void testingProfessorTrimming(){

    }

    @Test
    void calculateJaccardSimilarity_CorrectCalculation() {
        // Private method, but we can make it package-private for testing purposes
        Set<String> set1 = new HashSet<>(Arrays.asList("AI", "Machine Learning"));
        Set<String> set2 = new HashSet<>(Arrays.asList("AI", "Data Science"));

        // Use reflection to access private method (Alternative: Make method package-private for testing)
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
