package mappers;

import org.springframework.stereotype.Repository;
import domainmodel.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



@Repository

public interface UserMapper extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
