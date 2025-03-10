package com.SoftwareEngineering.TraineeshipApp.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.services.company.CompanyService;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired    
    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    @WithMockUser(username = "testCompany", authorities = "COMPANY")
    void getCompanyDashboard_ShouldReturnDashboardView() throws Exception {
        Company mockCompany = new Company();
        when(companyService.extractUsernameFromUser()).thenReturn("testCompany");
        when(companyService.retrieveProfile("testCompany")).thenReturn(mockCompany);

        mockMvc.perform(MockMvcRequestBuilders.get("/company/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("company/dashboard"))
                .andExpect(model().attributeExists("company"));
    }

    @Test
    @WithMockUser(username = "testCompany", authorities = "COMPANY")
    void showPositionForm_ShouldReturnPositionFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/company/showPositionForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("company/position-form"))
                .andExpect(model().attributeExists("position"));
    }

    @Test
    @WithMockUser(username = "testCompany", authorities = "COMPANY")
    void listAvailablePositions_ShouldReturnPositionsListView() throws Exception {
        when(companyService.extractUsernameFromUser()).thenReturn("testCompany");
        when(companyService.retrieveAvailablePositions("testCompany")).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/company/positions"))
                .andExpect(status().isOk())
                .andExpect(view().name("company/positions-list"))
                .andExpect(model().attributeExists("positions"));
    }

    @Test
    @WithMockUser(username = "testCompany", authorities = "COMPANY")
    void deletePosition_ShouldRedirectToPositionsList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/company/delete").param("positionId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/company/positions"));

        verify(companyService, times(1)).deleteById(1);
    }
}
