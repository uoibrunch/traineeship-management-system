package services.company;

import java.util.List;

import domainmodel.Company;
import domainmodel.TraineeshipPosition;
import mappers.CompanyMapper;
import domainmodel.Evaluation;

public class CompanyServiceImpl {
    private CompanyMapper companyMapper;

    public Company retrievePofile(String usernname){
        return companyMapper.findByCompanyId(usernname);
    }

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
