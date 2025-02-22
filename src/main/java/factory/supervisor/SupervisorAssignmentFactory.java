package factory.supervisor;

import factory.assignment.AssignmentBasedOnInterests;
import factory.assignment.AssignmentBasedOnLoad;

public class SupervisorAssignmentFactory {

    private AssignmentBasedOnLoad assignmentBasedOnLoad;
    private AssignmentBasedOnInterests assignmentBasedOnInterests;


    public SupervisorAssignmentStrategy create(String strategy){
        return null;
    }

    
}
