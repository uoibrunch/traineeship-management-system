package com.SoftwareEngineering.TraineeshipApp.services.company;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.mappers.CompanyMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyServiceImpl implements CompanyService {
    
    @Autowired
    private CompanyMapper companyMapper;
    
    @Autowired
    private TraineeshipPositionsMapper traineeshipPositionMapper;
    @Override
    public Company retrieveProfile(String username){
        return companyMapper.findByUsername(username);
    }

    @Override
    public void saveProfile(Company company){
        companyMapper.save(company);
    };

    @Override
    public List<TraineeshipPosition> retrieveAvailablePositions(String username){

        Company company = companyMapper.findByUsername(username);
        

        if (company != null) {

            return company.getPositions(); 
            
        } else {
            
            throw new RuntimeException("Company with username " + username + " not found.");

        }
        
    }

    @Override
    public void addPosition(String username, TraineeshipPosition position) {
        
        Company company = companyMapper.findByUsername(username);
        
        if (company != null) {
           
            company.getPositions().add(position);

            position.setCompany(company);

            traineeshipPositionMapper.save(position);
           
        } else {
            
            throw new RuntimeException("Company with username " + username + " not found.");

        }
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username){
        return null;    
    }

    @Override
    public void deleteById(int theId){

        traineeshipPositionMapper.deleteById(theId);

    }
    
    @Override
    public void evaluateAssignedPosition(Integer poistionId){};

    @Override
    public void saveEvaluation(Integer positionId, Evaluation evaulation){};

}
