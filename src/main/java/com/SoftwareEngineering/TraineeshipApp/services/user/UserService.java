package com.SoftwareEngineering.TraineeshipApp.services.user;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.User;

import org.springframework.stereotype.Service;
@Service
public interface UserService {


    void saveUser(User user);
    boolean isUserPresent(User user);
    User findById(String username);
   

}
