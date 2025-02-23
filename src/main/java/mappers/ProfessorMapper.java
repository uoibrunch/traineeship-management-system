package mappers;

import domainmodel.Professor;
import org.springframework.stereotype.Repository;
import domainmodel.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;




@Repository
public interface ProfessorMapper extends JpaRepository<Professor,Integer> {
    
}