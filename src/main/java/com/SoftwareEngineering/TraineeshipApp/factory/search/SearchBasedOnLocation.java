package com.SoftwareEngineering.TraineeshipApp.factory.search;

import com.SoftwareEngineering.TraineeshipApp.mappers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;

@Service
public class SearchBasedOnLocation implements PositionsSearchStrategy {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {

        Student student = studentMapper.findByUsername(applicantUsername);

        if (student == null || student.getPreferredLocation() == null || student.getSkills() == null) {
            return new ArrayList<>();
        }
        
        List<String> studentSkills = Arrays.asList(student.getSkills().split(","));

        studentSkills = studentSkills.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
            

        List<Company> companiesBasedOnPrefferredLocation = companyMapper.findByCompanyLocation(student.getPreferredLocation());
        List<TraineeshipPosition> matchingPositions = new ArrayList<>();

        for (Company company : companiesBasedOnPrefferredLocation) {
            for (TraineeshipPosition position : company.getPositions()) {
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
        }

        return matchingPositions;
    }

}
