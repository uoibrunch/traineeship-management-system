package com.SoftwareEngineering.TraineeshipApp.services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;
import com.SoftwareEngineering.TraineeshipApp.services.company.CompanyServiceImpl;


class CompanyServiceImplTest {

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private TraineeshipPositionsMapper traineeshipPositionMapper;

    @Mock
    private EvaluationMapper evaluationMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private Company mockCompany;
    private TraineeshipPosition position1;
    private TraineeshipPosition position2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        mockCompany = new Company();
        mockCompany.setUsername("Apple");

        
        position1 = new TraineeshipPosition();
        position1.setId(1);
        position2 = new TraineeshipPosition();
        position2.setId(2);
    }

    @Test
    void testRetrieveProfile() {
        String username = "Apple";
        
        
        when(companyMapper.findByUsername(username)).thenReturn(mockCompany);
        
        
        Company result = companyService.retrieveProfile(username);
        
       
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        
        
        verify(companyMapper, times(1)).findByUsername(username);
    }

    @Test
    void testSaveProfile_UpdateExistingCompany() {
        String username = "Apple";
        Company inputCompany = new Company();
        inputCompany.setUsername(username);
        inputCompany.setCompanyName("Apple");
        inputCompany.setCompanyLocation("SKG");

        
        Company existingCompany = new Company();
        existingCompany.setUsername(username);
        existingCompany.setCompanyName("Nvidia");
        existingCompany.setCompanyLocation("Athens");
        
        when(companyMapper.findByUsername(username)).thenReturn(existingCompany);
        
       
        companyService.saveProfile(inputCompany);
        
        
        assertEquals("Apple", existingCompany.getCompanyName());
        assertEquals("SKG", existingCompany.getCompanyLocation());
        
       
        verify(companyMapper, times(1)).save(existingCompany);
    }

    @Test
    void testAddPosition() {
        String username = "companyUsername";
        TraineeshipPosition position = new TraineeshipPosition();
        position.setId(1);

        
        Company company = new Company();
        company.setUsername(username);
        company.setPositions(new ArrayList<>()); 

       
        when(companyMapper.findByUsername(username)).thenReturn(company);

       
        companyService.addPosition(username, position);

       
        assertTrue(company.getPositions().contains(position));

        
        verify(traineeshipPositionMapper, times(1)).save(position);
    }


    @Test
    void testRetrieveAvailablePositions() {
        String username = "companyUsername";
        
        
        Company company = new Company();
        company.setUsername(username);
        
        List<TraineeshipPosition> availablePositions = Arrays.asList(position1, position2);
        
      
        when(companyMapper.findByUsername(username)).thenReturn(company);
        when(traineeshipPositionMapper.findByCompanyAndIsAssignedFalse(company)).thenReturn(availablePositions);
        
        
        List<TraineeshipPosition> result = companyService.retrieveAvailablePositions(username);
        
       
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(position1));
        assertTrue(result.contains(position2));
        
       
        verify(companyMapper, times(1)).findByUsername(username);
        verify(traineeshipPositionMapper, times(1)).findByCompanyAndIsAssignedFalse(company);
    }


}
