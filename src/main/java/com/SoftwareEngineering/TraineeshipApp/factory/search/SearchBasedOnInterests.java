package com.SoftwareEngineering.TraineeshipApp.factory.search;

import com.SoftwareEngineering.TraineeshipApp.mappers.StudentMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        if (student == null || student.getSkills() == null || student.getInterests() == null) {
            return new ArrayList<>();
        }

        List<String> studentSkills = Arrays.asList(student.getSkills().split(","));
        studentSkills = studentSkills.stream()
            .map(String::trim)          
            .map(String::toLowerCase)  
            .collect(Collectors.toList());

        List<String> studentInterests = Arrays.asList(student.getInterests().split(","));

        studentInterests = studentInterests.stream()
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toList());

        List<TraineeshipPosition> positionsBasedOnInterests = new ArrayList<>();

        List<TraineeshipPosition> matchingPositions = new ArrayList<>();

        for (String interest : studentInterests) {

            positionsBasedOnInterests.addAll(positionsMapper.findByTopicsContaining(interest));

        }

        for (TraineeshipPosition position : positionsBasedOnInterests) {
            if (position.isAssigned()) {
                continue;
            }

            List<String> positionSkills = Arrays.asList(position.getSkills().split(","));
            positionSkills = positionSkills.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
            
            if (!studentSkills.containsAll(positionSkills)) {
                continue;
            }

            matchingPositions.add(position);

        }

        return matchingPositions;
    }
}
