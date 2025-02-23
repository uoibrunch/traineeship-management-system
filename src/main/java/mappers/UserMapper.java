package mappers;
import domainmodel.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserMapper extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	
}

