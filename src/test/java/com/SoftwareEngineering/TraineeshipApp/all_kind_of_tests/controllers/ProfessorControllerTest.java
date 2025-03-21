package com.SoftwareEngineering.TraineeshipApp.all_kind_of_tests.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.SoftwareEngineering.TraineeshipApp.controllers.ProfessorController;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.services.professor.ProfessorService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProfessorService professorService;

    @Mock
    private Model model;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(professorController).build();
    }

    @Test
    public void getProfessorDashboard_ShouldReturnDashboardView() throws Exception {
        Professor professor = new Professor();
        when(professorService.extractUsernameFromUser()).thenReturn("professorUser");
        when(professorService.retrieveProfile("professorUser")).thenReturn(professor);

        mockMvc.perform(MockMvcRequestBuilders.get("/professor/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/dashboard"));

        verify(professorService, times(1)).retrieveProfile("professorUser");
    }

    @Test
    public void retrieveProfile_ShouldReturnDashboardViewWithProfessor() throws Exception {
        Professor professor = new Professor();
        when(professorService.extractUsernameFromUser()).thenReturn("professorUser");
        when(professorService.retrieveProfile("professorUser")).thenReturn(professor);

        mockMvc.perform(MockMvcRequestBuilders.get("/professor/retrieveProfile"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/dashboard"));
    }

    @Test
    public void showFormForUpdate_ShouldReturnProfessorFormView() throws Exception {
        Professor professor = new Professor();
        when(professorService.extractUsernameFromUser()).thenReturn("professorUser");
        when(professorService.retrieveProfile("professorUser")).thenReturn(professor);

        mockMvc.perform(MockMvcRequestBuilders.get("/professor/showFormForUpdate"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/professor-form"));
    }

    @Test
    public void listAssignedTraineeships_ShouldReturnAssignedTraineeshipsView() throws Exception {
        List<TraineeshipPosition> traineeships = Collections.emptyList();
        when(professorService.retrieveAssignedPositions()).thenReturn(traineeships);

        mockMvc.perform(MockMvcRequestBuilders.get("/professor/assignedTraineeships"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/assigned-traineeships"));
    }

    @Test
    public void evaluateAssignedTraineeship_ShouldReturnEvaluationFormView() throws Exception {
        TraineeshipPosition position = new TraineeshipPosition();
        when(professorService.getTraineeshipPositionById(anyInt())).thenReturn(position);

        mockMvc.perform(MockMvcRequestBuilders.get("/professor/evaluatePosition").param("positionId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/evaluation-form"));
    }

    @Test
    public void saveEvaluation_ShouldReturnShowEvaluationView() throws Exception {
        //Evaluation evaluation = new Evaluation();

        mockMvc.perform(MockMvcRequestBuilders.post("/professor/saveEvaluation")
                        .param("positionId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/show-evaluation"));

        verify(professorService, times(1)).saveEvaluation(anyInt(), any(Evaluation.class));
    }
}
