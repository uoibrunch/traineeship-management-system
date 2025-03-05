package com.SoftwareEngineering.TraineeshipApp.search.assignment;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy{

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;
    
    @Autowired
    private ProfessorMapper professorMapper;

    public void assign(Integer positionId){

        Optional<TraineeshipPosition> selectedPosition = positionsMapper.findById(positionId);

        if (selectedPosition.isPresent()) {
            
            TraineeshipPosition position = selectedPosition.get();

            List<Professor> professors = professorMapper.findAll();

            Professor selectedProfessor = professors.get(0);

            for (Professor professor : professors) {
                if (professor.getSupervisedPositions().size() < selectedProfessor.getSupervisedPositions().size()) {
                    selectedProfessor = professor;
                }
            }

            position.setSupervisor(selectedProfessor);
            position.setIsSupervised(true);

            positionsMapper.save(position);

        } else {
            throw new EntityNotFoundException("Traineeship Position not found with ID: " + positionId);
        }

    }

}
