package mappers;

import domainmodel.Student;


import org.springframework.stereotype.Repository;
import domainmodel.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public class StudentMapper {

   
    public User findByUsername(String username) {
        return null;
    }

    // You can add methods specific to StudentMapper if needed
    public Student findStudentById(Long id) {
        // Custom logic for finding a student by id
        return new Student();  // Placeholder for example
    }
}
