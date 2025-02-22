package mappers;

import org.springframework.stereotype.Repository;
import domainmodel.User;

//interface created by Marios might not be necesary 
@Repository

public interface UserMapper {
    User findByUsername(String username);
}
