package mappers;

import domainmodel.Student;
import domainmodel.User;

import org.springframework.stereotype.Repository;

@Repository
public class StudentMapper implements UserMapper {

   
    public User findByUsername(String username) {
        return null;
    }

    // You can add methods specific to StudentMapper if needed
    public Student findStudentById(Long id) {
        // Custom logic for finding a student by id
        return new Student();  // Placeholder for example
    }
}
