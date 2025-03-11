package com.SoftwareEngineering.TraineeshipApp.unit_tests.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.SoftwareEngineering.TraineeshipApp.controllers.StudentController;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.services.student.StudentService;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Mock
    private Model model;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void testGetStudentDashboard() throws Exception {
        Student student = new Student();
        when(studentService.retrieveProfile(anyString())).thenReturn(student);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/students/dashboard"))
            .andExpect(status().isOk())
            .andExpect(view().name("students/dashboard"));
    }

    @Test
    void testApplyForTraineeship() throws Exception {
        Student student = new Student();
        when(studentService.retrieveProfile(anyString())).thenReturn(student);
        doNothing().when(studentService).applyForATraineeship(any(Student.class));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/students/applyForATraineeship"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/students/dashboard"));
    }

    @Test
    void testShowFormForUpdate() throws Exception {
        when(studentService.retrieveProfile(anyString())).thenReturn(new Student());
        
        mockMvc.perform(MockMvcRequestBuilders.get("/students/showFormForUpdate"))
            .andExpect(status().isOk())
            .andExpect(view().name("students/student-form"));
    }

    @Test
    void testRetrieveProfile() throws Exception {
        when(studentService.retrieveProfile(anyString())).thenReturn(new Student());
        
        mockMvc.perform(MockMvcRequestBuilders.get("/students/retrieveProfile"))
            .andExpect(status().isOk())
            .andExpect(view().name("students/dashboard"));
    }

    @Test
    void testSaveProfile() throws Exception {
        doNothing().when(studentService).saveProfile(any(Student.class));
        
        mockMvc.perform(MockMvcRequestBuilders.post("/students/save").flashAttr("student", new Student()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/students/dashboard"));
    }

    @Test
    void testFillLogbook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/fillLogbook"))
            .andExpect(status().isOk())
            .andExpect(view().name("students/fill-logbook"));
    }

    @Test
    void testSaveLogbook() throws Exception {
        when(studentService.retrieveProfile(anyString())).thenReturn(new Student());
        doNothing().when(studentService).saveLogbook(any(Logbook.class), any(Student.class));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/students/saveLogbook").flashAttr("logbook", new Logbook()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/students/dashboard"));
    }
}
