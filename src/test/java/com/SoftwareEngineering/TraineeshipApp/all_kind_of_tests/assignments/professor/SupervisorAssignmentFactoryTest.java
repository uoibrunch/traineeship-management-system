package com.SoftwareEngineering.TraineeshipApp.all_kind_of_tests.assignments.professor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.SoftwareEngineering.TraineeshipApp.assignments.professor.AssignmentBasedOnInterests;
import com.SoftwareEngineering.TraineeshipApp.assignments.professor.AssignmentBasedOnLoad;
import com.SoftwareEngineering.TraineeshipApp.assignments.professor.SupervisorAssignmentFactory;
import com.SoftwareEngineering.TraineeshipApp.assignments.professor.SupervisorAssignmentStrategy;

@SpringBootTest
public class SupervisorAssignmentFactoryTest {

    @Mock
    private AssignmentBasedOnLoad assignmentBasedOnLoad;

    @Mock
    private AssignmentBasedOnInterests assignmentBasedOnInterests;

    @InjectMocks
    private SupervisorAssignmentFactory supervisorAssignmentFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_LoadStrategy() {
        SupervisorAssignmentStrategy strategy = supervisorAssignmentFactory.create("load");

        assertNotNull(strategy);
        assertEquals(assignmentBasedOnLoad, strategy); 
    }

    @Test
    void testCreate_InterestsStrategy() {
        SupervisorAssignmentStrategy strategy = supervisorAssignmentFactory.create("interests");

        assertNotNull(strategy);
        assertEquals(assignmentBasedOnInterests, strategy); 
    }

    @Test
    void testCreate_InvalidStrategy() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            supervisorAssignmentFactory.create("invalid");
        });

        assertEquals("Invalid search strategy: invalid", exception.getMessage());
    }
}
