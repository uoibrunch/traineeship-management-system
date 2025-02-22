package services.user;
import domainmodel.User;

import org.springframework.stereotype.Service;
@Service
public interface UserService {


    void saveUser(User user);
    boolean isUserPresent(User user);
    User findById(String username);

}
