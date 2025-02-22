package mappers;

import domainmodel.Professor;
import org.springframework.stereotype.Repository;
import domainmodel.User;


@Repository
public class ProfessorMapper implements UserMapper {

   
    @Override
    public User findByUsername(String username) {
     
        return null;  
    }

    
    public Professor findByProfessorId(Long professorId) {
       
        return null;  
    }

    
    public Professor findByProfessorName(String name) {
        
        return null;  
    }
}