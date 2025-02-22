package mappers;


import domainmodel.User;
import domainmodel.TraineeshipPosition;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeshipPositionsMapper implements UserMapper {

    @Override
    public User findByUsername(String username) {
       
        return null; 
    }

    
    public TraineeshipPosition findByTraineeshipId(Long traineeshipId) {
        
        return null;  

    }
}