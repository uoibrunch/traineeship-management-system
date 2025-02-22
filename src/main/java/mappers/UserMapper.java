package mappers;
import domainmodel.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//interface created by Marios might not be necesary 

public interface UserMapper extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
