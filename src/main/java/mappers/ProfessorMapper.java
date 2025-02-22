package mappers;

import domainmodel.Professor;
import org.springframework.stereotype.Repository;
import domainmodel.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;




@Repository
public class ProfessorMapper  {



    
    public Professor findByProfessorId(Long professorId) {
       
        return null;  
    }

    
    public Professor findByProfessorName(String name) {
        
        return null;  
    }
}