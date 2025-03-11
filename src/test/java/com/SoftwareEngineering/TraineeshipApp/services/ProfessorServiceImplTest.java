package com.SoftwareEngineering.TraineeshipApp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.EvaluationMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.ProfessorMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;
import com.SoftwareEngineering.TraineeshipApp.services.professor.ProfessorServiceImpl;



@AutoConfigureMockMvc
@SpringBootTest
public class ProfessorServiceImplTest {

    @Mock
    private ProfessorMapper professorMapper;

    @Mock
    private TraineeshipPositionsMapper traineeshipPositionMapper;

    @Mock
    private EvaluationMapper evaluationMapper;

    @InjectMocks
    private ProfessorServiceImpl professorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        

    }

    @Test
    void retrieveProfile_ShouldReturnProfessor() {
        // Arrange
        Professor professor = new Professor();
        professor.setUsername("testProfessor");
        when(professorMapper.findByUsername("testProfessor")).thenReturn(professor);

        // Act
        Professor result = professorService.retrieveProfile("testProfessor");

        // Assert
        assertNotNull(result);
        assertEquals("testProfessor", result.getUsername());
    }

    @Test
    void saveProfile_ShouldSaveProfessor() {
        // Arrange
        Professor professor = new Professor();
        professor.setUsername("testProfessor");
        when(professorMapper.findByUsername("testProfessor")).thenReturn(null);

        // Act
        professorService.saveProfile(professor);

        // Assert
        verify(professorMapper).save(professor);
    }

    /*@Test
    void retrieveAssignedPositions_ShouldReturnAssignedPositions() {
        // Arrange
        Professor professor = new Professor();
        List<TraineeshipPosition> positions = Arrays.asList(new TraineeshipPosition());
        professor.setSupervisedPositions(positions);
        when(professorMapper.findByUsername("testProfessor")).thenReturn(professor);

        // Act
        List<TraineeshipPosition> result = professorService.retrieveAssignedPositions();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void saveEvaluation_ShouldSaveEvaluation() {

    }

    @Test
    void saveEvaluation_ShouldUpdateExistingEvaluation() {
        // Arrange
    }
    */
    @Test
    void getTraineeshipPositionById_ShouldReturnPosition() {
        // Arrange
        TraineeshipPosition traineeshipPosition = new TraineeshipPosition();
        traineeshipPosition.setId(10);

        when(traineeshipPositionMapper.findById(10)).thenReturn((traineeshipPosition));

        TraineeshipPosition result = professorService.getTraineeshipPositionById(10);
        
        assertNotNull(result);

        assertEquals(traineeshipPosition, result.getId());
    }

    
}
