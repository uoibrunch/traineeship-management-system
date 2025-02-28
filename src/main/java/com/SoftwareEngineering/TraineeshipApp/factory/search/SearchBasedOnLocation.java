package com.SoftwareEngineering.TraineeshipApp.factory.search;

import com.SoftwareEngineering.TraineeshipApp.mappers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        if (student == null || student.getPreferredLocation() == null) {
            
            return new ArrayList<>();
        }

        List<Company> companies = companyMapper.findByCompanyLocation(student.getPreferredLocation());

        List<TraineeshipPosition> positions = new ArrayList<>();

        for (Company company : companies) {

            positions.addAll(company.getPositions());

        }

        return positions;
    }

}
