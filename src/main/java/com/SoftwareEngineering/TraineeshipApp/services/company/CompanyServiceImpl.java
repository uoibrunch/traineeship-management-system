package com.SoftwareEngineering.TraineeshipApp.services.company;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.CompanyMapper;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;

public class CompanyServiceImpl {
    private CompanyMapper companyMapper;

    // public Company retrievePofile(String usernname){
    //     return companyMapper.findByCompanyId(usernname);
    // }

    public void saveProfile(Company company){};

    public List<TraineeshipPosition> retrieveAvailablePositions(String Username){
        return null;
    }

    public void addPosition(String username, TraineeshipPosition poisition){};

    public List<TraineeshipPosition> retrieveAssignedPositions(String username){
        return null;
    }
    
    public void evaluateAssignedPosition(Integer poistionId){};

    public void saveEvaluation(Integer positionId, Evaluation evaulation){};

}
