package com.SoftwareEngineering.TraineeshipApp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Logbook;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.services.committee.CommitteeService;

@SpringBootTest
@AutoConfigureMockMvc
public class CommitteeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommitteeService committeeService;

    @Test
    @WithMockUser(username = "testCommittee", authorities =  "COMMITTEE_MEMBER")
    public void getCommitteeDashboard_ShouldReturnDashboardView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/committee/dashboard").with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/dashboard"));
    }

    @Test
    @WithMockUser(username = "testCommittee", authorities =  "COMMITTEE_MEMBER")
    public void listTrainseeshipApplications_ShouldReturnStudentsList() throws Exception {

        List<Student> mockStudents = Arrays.asList(new Student(), new Student());
        when(committeeService.retrieveTraineeshipApplications()).thenReturn(mockStudents); 

        mockMvc.perform(MockMvcRequestBuilders.get("/committee/showTraineeshipApplications"))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/students-list"))
            .andExpect(model().attributeExists("students"))
            .andExpect(model().attribute("students", mockStudents));
    }

    /*@Test
    @WithMockUser(username = "testCommittee", authorities =  "COMMITTEE_MEMBER")
    public void assignProfessor_ShouldReturnErrorMessageWhenPositionNotFound() throws Exception {
        Integer positionId = null;
        String strategy = "strategy1";

        mockMvc.perform(MockMvcRequestBuilders.get("/committee/assignProfessor").with(csrf())
                .param("traineeshipId", String.valueOf(positionId))
                .param("strategy", strategy))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/assignment_error"))
            .andExpect(model().attribute("errorMessage", "Traineeship ID is missing."));
    }

    @Test
    @WithMockUser(username = "testCommittee", authorities =  "COMMITTEE_MEMBER")
    public void completeAssignedTraineeships_ShouldReturnCompleteForm() throws Exception {
        Integer positionId = 1;
        TraineeshipPosition position = new TraineeshipPosition();
        List<Logbook> logbooks = Arrays.asList(new Logbook());
        Evaluation evaluation = new Evaluation();
        Student student = new Student();
        Company company = new Company();

        when(committeeService.findPositionById(positionId)).thenReturn(position);

        mockMvc.perform(MockMvcRequestBuilders.get("/committee/completeTraineeship"). with(csrf())
                .param("traineeshipId", String.valueOf(positionId)))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/complete-form"))
            .andExpect(model().attribute("position", position))
            .andExpect(model().attribute("evaluation", evaluation))
            .andExpect(model().attribute("student", student))
            .andExpect(model().attribute("logbooks", logbooks))
            .andExpect(model().attribute("company", company));
    }

    @Test
    @WithMockUser(username = "testCommittee", authorities =  "COMMITTEE_MEMBER")
    public void updateGrade_ShouldUpdateGradeAndReturnResult() throws Exception {
        Integer positionId = 1;
        boolean grade = true;
        TraineeshipPosition position = new TraineeshipPosition();
        when(committeeService.completeAssignedTraineeships(positionId, grade)).thenReturn(position);
    
        mockMvc.perform(MockMvcRequestBuilders.post("/committee/updateGrade/{id}", positionId) .with(csrf())
                .param("grade", String.valueOf(grade)))
               .andExpect(status().isOk())
               .andExpect(view().name("/committee/traineeshipResult"))
               .andExpect(model().attribute("position", position));
    }
               */
    
}
