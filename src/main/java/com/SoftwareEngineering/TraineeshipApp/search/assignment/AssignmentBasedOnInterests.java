package com.SoftwareEngineering.TraineeshipApp.search.assignment;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Professor;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.*;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy {

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;

    @Autowired
    private ProfessorMapper professorMapper;
    

    public void assign(Integer positionId){

        Optional<TraineeshipPosition> selectedPosition = positionsMapper.findById(positionId);

        double threshold = 4.0;

        if (selectedPosition.isPresent()) {
            
            TraineeshipPosition position = selectedPosition.get();

            Set<String> positionTopics = Arrays.stream(position.getTopics().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

            List<Professor> professors = professorMapper.findAll();

            Random rnd = new Random();

            Professor bestProfessor = new Professor();

            if (!professors.isEmpty()) {
                int randomIndex = rnd.nextInt(professors.size()); 
                bestProfessor = professors.get(randomIndex); 
            }


            double highestSimilarity = 0.0;

            for (Professor professor : professors){

                Set<String> professorInterests = Arrays.stream(professor.getInterests().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
            
                double jaccardSimilarity = calculateJaccardSimilarity(professorInterests, positionTopics);

                if (jaccardSimilarity > threshold && jaccardSimilarity > highestSimilarity) {
                    bestProfessor = professor;
                    highestSimilarity = jaccardSimilarity;
                }
            }


            
            position.setSupervisor(bestProfessor);

            position.setIsSupervised(true);
            
            positionsMapper.save(position);
        
        } else {
            throw new EntityNotFoundException("Traineeship Position not found with ID: " + positionId);
        }
        
    }

    private double calculateJaccardSimilarity(Set<String> set1, Set<String> set2) {
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }

}
