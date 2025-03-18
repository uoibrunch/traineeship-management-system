package com.SoftwareEngineering.TraineeshipApp.unit_tests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.TestPropertySource;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.EvaluationType;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.mappers.EvaluationMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.ProfessorMapper;
import com.SoftwareEngineering.TraineeshipApp.services.professor.ProfessorServiceImpl;



@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application.properties")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProfessorServiceImplTest {

   

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private ProfessorServiceImpl professorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void retrieveProfileShouldReturnProfessor() {

        Professor storedProfessor = professorMapper.findByUsername("zarras");
        assertNotNull(storedProfessor);
        assertEquals(1, storedProfessor.getProfessorId());
        assertEquals("Coding", storedProfessor.getInterests());
    }

    @Test
    void saveProfileShouldSaveProfessor() {
     
       User user = new User("papapetrou", "123", new ArrayList<>());
       

        Professor professor = new Professor();
        professor.setUsername(user.getUsername());
        professor.setInterests("Diktya");
        professor.setProfessorName("mr.Papapetrou");

        professorService.saveProfile(professor);
        
        Professor retrievedProfessor = professorMapper.findByUsername("papapetrou");
        assertNotNull(retrievedProfessor);
        assertEquals("mr.Papapetrou", retrievedProfessor.getProfessorName());
        assertEquals("Diktya", retrievedProfessor.getInterests());

    }


    @Test
    void saveEvaluationShouldUpdateExistingEvaluation(){

        Evaluation papapetrouEvaluation = new Evaluation();
        EvaluationType professorEvaluation = EvaluationType.PROFESSOR;

        papapetrouEvaluation.setEffectiveness(4);
        papapetrouEvaluation.setEfficiency(4);
        papapetrouEvaluation.setFacility(4);
        papapetrouEvaluation.setMotivation(5);
        papapetrouEvaluation.setId(10);
        papapetrouEvaluation.setEvaluationType(professorEvaluation);
        papapetrouEvaluation.setGuidance(2);
        
        evaluationMapper.save(papapetrouEvaluation);

       
        Optional<Evaluation> retrievedEvaluation = evaluationMapper.findById(10);
        assertNotNull(retrievedEvaluation);
        assertEquals(5, retrievedEvaluation.get().getMotivation());
        assertEquals(10, retrievedEvaluation.get().getId());

    }


}
