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

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Student;
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



}
