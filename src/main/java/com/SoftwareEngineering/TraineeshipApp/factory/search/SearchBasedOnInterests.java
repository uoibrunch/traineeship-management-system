package com.SoftwareEngineering.TraineeshipApp.factory.search;

import com.SoftwareEngineering.TraineeshipApp.mappers.StudentMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;


@Service
public class SearchBasedOnInterests implements PositionsSearchStrategy {

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;

    @Autowired
    private StudentMapper studentMapper;

    
    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {

        Student student = studentMapper.findByUsername(applicantUsername);

        Double threshold = 0.7;

        if (student == null || student.getSkills() == null || student.getInterests() == null) {
            return new ArrayList<>();
        }

        List<String> studentSkills = Arrays.stream(student.getSkills().split(","))
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toList());

        Set<String> studentInterests = Arrays.stream(student.getInterests().split(","))
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toSet());

        List<TraineeshipPosition> allPositions = positionsMapper.findAll(); // Get all traineeship positions
        List<TraineeshipPosition> matchingPositions = new ArrayList<>();

        for (TraineeshipPosition position : allPositions) {
            if (position.isAssigned()) {
                continue;  
            }

            List<String> positionSkills = Arrays.stream(position.getSkills().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

            if (!studentSkills.containsAll(positionSkills)) {
                continue; 
            }

            Set<String> positionTopics = Arrays.stream(position.getTopics().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

            double jaccardSimilarity = calculateJaccardSimilarity(studentInterests, positionTopics);

            if (jaccardSimilarity >= threshold) {
                matchingPositions.add(position);
            }
        }

        return matchingPositions;
    }

    private double calculateJaccardSimilarity(Set<String> set1, Set<String> set2) {
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }
}

