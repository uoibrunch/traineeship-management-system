package com.SoftwareEngineering.TraineeshipApp.services.company;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;
import com.SoftwareEngineering.TraineeshipApp.mappers.CompanyMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.Evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Company existingCompany = companyMapper.findByUsername(company.getUsername());
        if (existingCompany != null) {
            existingCompany.setCompanyName(company.getCompanyName());
            existingCompany.setCompanyLocation(company.getCompanyLocation());
            companyMapper.save(existingCompany); 
        } else {
        companyMapper.save(company); 
    }
    };

    @Override
    public List<TraineeshipPosition> retrieveAvailablePositions(String username){

        Company company = companyMapper.findByUsername(username);
        

        if (company != null) {

            return traineeshipPositionMapper.findByCompanyAndIsAssignedFalse(company);
            
        } else {
            
            throw new RuntimeException("Company with username " + username + " not found.");

        }
        
    }

    @Override
    public void addPosition(String username, TraineeshipPosition position) {

        position.setIsAssigned(false);

        position.setIsSupervised(false);
        
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
        Company company = companyMapper.findByUsername(username);
    
        if (company != null) {
            return traineeshipPositionMapper.findByCompanyAndIsAssignedTrue(company);

        } else {

            throw new RuntimeException("Company with username " + username + " not found.");
            
        }
    }

    @Override
    public void deleteById(int theId){

        traineeshipPositionMapper.deleteById(theId);

    }
    
    @Override
    public void evaluateAssignedPosition(Integer poistionId){};

    @Override
    public void saveEvaluation(Integer positionId, Evaluation evaulation){};


    @Override
    public void saveUsernameAndId(Company company){

        company.setUsername(extractUsernameFromUser());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = (User) userDetails;

        company.setCompanyId(user.getId());

    }

    @Override
    public String extractUsernameFromUser(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return username;
    }

}
