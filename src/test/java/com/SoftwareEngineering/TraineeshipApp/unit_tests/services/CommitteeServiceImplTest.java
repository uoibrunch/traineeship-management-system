package com.SoftwareEngineering.TraineeshipApp.unit_tests.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import com.SoftwareEngineering.TraineeshipApp.assignments.position.PositionsSearchFactory;
import com.SoftwareEngineering.TraineeshipApp.assignments.position.PositionsSearchStrategy;
import com.SoftwareEngineering.TraineeshipApp.assignments.professor.SupervisorAssignmentFactory;
import com.SoftwareEngineering.TraineeshipApp.assignments.professor.SupervisorAssignmentStrategy;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;
import com.SoftwareEngineering.TraineeshipApp.services.committee.CommitteeServiceImpl;

@SpringBootTest
public class CommitteeServiceImplTest {

    @Mock
    private PositionsSearchFactory positionsSearchFactory;

    @Mock
    private SupervisorAssignmentFactory supervisorAssignmentFactory;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private TraineeshipPositionsMapper positionsMapper;

    @Mock
    private ProfessorMapper professorMapper;

    @InjectMocks
    private CommitteeServiceImpl committeeService;

    private Student student;
    private TraineeshipPosition position;
    private Professor professor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setUsername("Marios");
        student.setLookingForTraineeship(true);

        position = new TraineeshipPosition();
        position.setId(1);
        position.setIsAssigned(false);

        professor = new Professor();
        professor.setProfessorId(1);
        professor.setProfessorName("Zarras");
    }



    @Test
    void testRetrieveTraineeshipApplications() {
       
        when(studentMapper.findByLookingForTraineeshipTrue()).thenReturn(Arrays.asList(student));

        List<Student> result = committeeService.retrieveTraineeshipApplications();

      
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(student));
    }

    @Test
    void testRetrievePositionsForApplicant() {
        
        TraineeshipPosition position = new TraineeshipPosition();
        position.setId(1);
        position.setSkills("Java, Spring");
        position.setTopics("AI, Machine Learning");
     
       
        String strategy = "location";
        
       
        PositionsSearchStrategy searchStrategy = mock(PositionsSearchStrategy.class);
        when(positionsSearchFactory.create(strategy)).thenReturn(searchStrategy);
        
        
        when(searchStrategy.search("Marios")).thenReturn(Arrays.asList(position));
    
    
        List<TraineeshipPosition> result = committeeService.retrievePositionsForApplicant("Marios", strategy);
    
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(position));
    
        verify(positionsSearchFactory).create(strategy); 
        verify(searchStrategy).search("Marios");  
    }
    

    @Test
    void testFindStudentById() {
        
        when(studentMapper.findById(1)).thenReturn(student);

      
        Student result = committeeService.findStudentById(1);

        
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void testAssignPosition_Success() {
        
        when(studentMapper.findByUsername("Marios")).thenReturn(student);
        when(positionsMapper.findById(Integer.valueOf(1))).thenReturn(Optional.of(position));

       
        committeeService.assignPosition(1, "Marios");

        
        assertTrue(position.isAssigned());
        assertEquals(student, position.getStudent());
        assertFalse(student.isLookingForTraineeship());
    }

    

    @Test
    void testListUnassignedTraineeships() {
        
        when(positionsMapper.findByIsAssignedFalse()).thenReturn(Arrays.asList(position));

        
        List<TraineeshipPosition> result = committeeService.listUnassignedTraineeships();

      
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(position));
    }

    @Test
    void testListProfessors() {
        
        when(professorMapper.findAll()).thenReturn(Arrays.asList(professor));

        
        List<Professor> result = committeeService.listProfessors();

       
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(professor));
    }

    @Test
    void testAssignSupervisor() {
       
        String strategy = "random";
        SupervisorAssignmentStrategy supervisorAssignmentStrategy = mock(SupervisorAssignmentStrategy.class);
        when(supervisorAssignmentFactory.create(strategy)).thenReturn(supervisorAssignmentStrategy);

      
        committeeService.assignSupervisor(1, strategy);

       
        verify(supervisorAssignmentStrategy, times(1)).assign(1);
    }

    @Test
    void testCompleteAssignedTraineeships() {
       
        when(positionsMapper.findById(1)).thenReturn(position);

      
        TraineeshipPosition result = committeeService.completeAssignedTraineeships(1, true);

        
        assertNotNull(result);
        assertTrue(result.isPassFailGrade());
        verify(positionsMapper, times(1)).save(result);
    }

}
