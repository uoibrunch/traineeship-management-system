package com.SoftwareEngineering.TraineeshipApp.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
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


    @Test
    @WithMockUser(username = "testCommittee", authorities = "COMMITTEE_MEMBER")
    public void findPositions_ShouldReturnPositionsList() throws Exception {
        String studentUsername = "Marios";
        String strategy = "both";

        Student mockStudent = new Student();
        mockStudent.setUsername(studentUsername);

        Company mockCompany = new Company();
        mockCompany.setCompanyName("Apple");

        TraineeshipPosition position1 = new TraineeshipPosition();
        position1.setCompany(mockCompany);  

        TraineeshipPosition position2 = new TraineeshipPosition();
        position2.setCompany(mockCompany);  

        List<TraineeshipPosition> mockPositions = Arrays.asList(position1, position2);

        when(committeeService.findStudentByUsername(studentUsername)).thenReturn(mockStudent);
        when(committeeService.retrievePositionsForApplicant(studentUsername, strategy)).thenReturn(mockPositions);

        mockMvc.perform(MockMvcRequestBuilders.get("/committee/findPositions")
                .param("studentUsername", studentUsername)
                .param("strategy", strategy))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/positions-list"))
            .andExpect(model().attributeExists("student", "positions"))
            .andExpect(model().attribute("student", mockStudent))
            .andExpect(model().attribute("positions", mockPositions));
    }


    @Test
    @WithMockUser(username = "testCommittee", authorities = "COMMITTEE_MEMBER")
    public void selectStudent_ShouldReturnSelectedStudentView() throws Exception {
        int studentId = 1;
    
        Student mockStudent = new Student();
        mockStudent.setStudentId(studentId);
    
        Company mockCompany = new Company();
        mockCompany.setCompanyName("Apple");
    
        TraineeshipPosition position1 = new TraineeshipPosition();
        position1.setCompany(mockCompany);
    
        TraineeshipPosition position2 = new TraineeshipPosition();
        position2.setCompany(mockCompany);
    
        List<TraineeshipPosition> mockUnassignedPositions = Arrays.asList(position1, position2);
    
        when(committeeService.findStudentById(studentId)).thenReturn(mockStudent);
        when(committeeService.listUnassignedTraineeships()).thenReturn(mockUnassignedPositions);
    
        mockMvc.perform(MockMvcRequestBuilders.get("/committee/selectStudent")
                .param("studentId", String.valueOf(studentId)))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/selected-student"))
            .andExpect(model().attributeExists("student", "positions"))
            .andExpect(model().attribute("student", mockStudent))
            .andExpect(model().attribute("positions", mockUnassignedPositions));
    }

    @Test
    @WithMockUser(username = "testCommittee", authorities = "COMMITTEE_MEMBER")
    public void assignPosition_ShouldRedirectToDashboardWithSuccessMessage() throws Exception {
        int positionId = 5;
        String studentUsername = "Marios";

        doNothing().when(committeeService).assignPosition(positionId, studentUsername);

        mockMvc.perform(MockMvcRequestBuilders.post("/committee/assignPosition")
                .param("positionId", String.valueOf(positionId))
                .param("studentUsername", studentUsername)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/dashboard"))
            .andExpect(model().attributeExists("successMessage"));
    }


    @Test
    @WithMockUser(username = "testCommittee", authorities = "COMMITTEE_MEMBER")
    public void assignProfessor_ShouldReturnAssignedProfessorView() throws Exception {
        int positionId = 10;
        String strategy = "Interests";

        Professor mockProfessor = new Professor();
        mockProfessor.setProfessorId(1);
        
        TraineeshipPosition mockPosition = new TraineeshipPosition();
        mockPosition.setId(positionId);
        mockPosition.setSupervisor(mockProfessor);

        when(committeeService.findPositionById(positionId)).thenReturn(mockPosition);
        doNothing().when(committeeService).assignSupervisor(positionId, strategy);

        mockMvc.perform(MockMvcRequestBuilders.post("/committee/assignProfessor")
                .param("traineeshipId", String.valueOf(positionId))
                .param("strategy", strategy)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/assigned_professor"))
            .andExpect(model().attributeExists("professor", "position"))
            .andExpect(model().attribute("professor", mockProfessor))
            .andExpect(model().attribute("position", mockPosition));
    }


    @Test
    @WithMockUser(username = "testCommittee", authorities = "COMMITTEE_MEMBER")
    public void completeAssignedTraineedships_ShouldReturnCompleteForm() throws Exception {
        int traineeshipId = 10;

        Student mockStudent = new Student();
        Company mockCompany = new Company();
        List<Logbook> mockLogbooks = Arrays.asList(new Logbook(), new Logbook());
        TraineeshipPosition mockPosition = new TraineeshipPosition();
        mockPosition.setStudent(mockStudent);
        mockPosition.setCompany(mockCompany);
        mockPosition.setStudentLogbook(mockLogbooks);

        when(committeeService.findPositionById(traineeshipId)).thenReturn(mockPosition);

        mockMvc.perform(MockMvcRequestBuilders.get("/committee/completeTraineeship")
                .param("traineeshipId", String.valueOf(traineeshipId)))
            .andExpect(status().isOk())
            .andExpect(view().name("committee/complete-form"))
            .andExpect(model().attributeExists("position", "evaluation", "student", "logbooks", "company"))
            .andExpect(model().attribute("position", mockPosition))
            .andExpect(model().attribute("student", mockStudent))
            .andExpect(model().attribute("company", mockCompany))
            .andExpect(model().attribute("logbooks", mockLogbooks));
    }

    @Test
    @WithMockUser(username = "testCommittee", authorities = "COMMITTEE_MEMBER")
    public void updateGrade_ShouldReturnTraineeshipResultView() throws Exception {
        int traineeshipId = 10;
        boolean grade = true;

        TraineeshipPosition mockPosition = new TraineeshipPosition();
        mockPosition.setPassFailGrade(grade);  

        when(committeeService.completeAssignedTraineeships(traineeshipId, grade)).thenReturn(mockPosition);

        mockMvc.perform(MockMvcRequestBuilders.post("/committee/updateGrade/{id}", traineeshipId)
                .param("grade", String.valueOf(grade))
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("/committee/traineeshipResult"))
            .andExpect(model().attributeExists("position"))
            .andExpect(model().attribute("position", mockPosition));
    }

}
