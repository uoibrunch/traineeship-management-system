package com.SoftwareEngineering.TraineeshipApp.assignments.professor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AssignmentBasedOnLoadTest {

    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private ProfessorMapper professorMapper;

    @InjectMocks
    private AssignmentBasedOnLoad assignmentStrategy;

    private TraineeshipPosition position;
    private Professor professor1;
    private Professor professor2;
    private Professor professor3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        position = new TraineeshipPosition();
        position.setId(10);

        professor1 = new Professor();
        professor1.setProfessorId(24);
        professor1.setSupervisedPositions(new ArrayList<>(Arrays.asList(new TraineeshipPosition(), new TraineeshipPosition())));

        professor2 = new Professor();
        professor2.setProfessorId(15);
        professor2.setSupervisedPositions(new ArrayList<>(Arrays.asList(new TraineeshipPosition()))); 

        professor3 = new Professor();
        professor3.setProfessorId(30);
        professor3.setSupervisedPositions(new ArrayList<>(Arrays.asList(new TraineeshipPosition(), new TraineeshipPosition(), new TraineeshipPosition()))); 
    }

    @Test
    void testAssignProfessorWithLeastLoad() {

        when(positionsMapper.findById(Integer.valueOf(10))).thenReturn(Optional.of(position));

        when(professorMapper.findAll()).thenReturn(Arrays.asList(professor1, professor2, professor3));

        assignmentStrategy.assign(10);

        verify(positionsMapper, times(1)).save(position);

        assertNotNull(position.getSupervisor());

        assertEquals(professor2, position.getSupervisor()); 

        assertTrue(position.isSupervised());
    }

    @Test
    void testAssign_ThrowsExceptionForInvalidPositionId() {
        
        when(positionsMapper.findById(Integer.valueOf(99))).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            assignmentStrategy.assign(99);
        });

        assertEquals("Traineeship Position not found with ID: 99", exception.getMessage());

        verify(positionsMapper, never()).save(any());
    }
}
