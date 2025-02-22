package services.user;

import domainmodel.User;
import org.springframework.security.core.userdetails.UserDetails;
import mappers.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userDAO;

   
    public void saveUser(User user) {
       
    }

    
    public boolean isUserPresent(User user) {
        return true;
    }

    
    public User findById(String username) {
        return userDAO.findByUsername(username);//will remove later
    }

    
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.findByUsername(username);
        return user; // temporary solution , will remove later
    }
    

}
