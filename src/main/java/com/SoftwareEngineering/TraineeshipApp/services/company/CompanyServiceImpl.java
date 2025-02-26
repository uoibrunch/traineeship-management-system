package com.SoftwareEngineering.TraineeshipApp.services.company;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.CompanyMapper;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyServiceImpl implements CompanyService {
    
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company retrieveProfile(String username){
        return companyMapper.findByUsername(username);
    }

    @Override
    public void saveProfile(Company company){
        companyMapper.save(company);
    };

    @Override
    public List<TraineeshipPosition> retrieveAvailablePositions(String Username){
        return null;
    }

    @Override
    public void addPosition(String username, TraineeshipPosition poisition){};

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username){
        return null;
    }
    
    @Override
    public void evaluateAssignedPosition(Integer poistionId){};

    @Override
    public void saveEvaluation(Integer positionId, Evaluation evaulation){};

}
