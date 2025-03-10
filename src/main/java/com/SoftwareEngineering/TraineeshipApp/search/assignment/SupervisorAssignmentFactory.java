package com.SoftwareEngineering.TraineeshipApp.search.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupervisorAssignmentFactory {

    @Autowired
    private AssignmentBasedOnLoad assignmentBasedOnLoad;

    @Autowired
    private AssignmentBasedOnInterests assignmentBasedOnInterests;



    public SupervisorAssignmentStrategy create(String strategy) {

        if ("load".equalsIgnoreCase(strategy)) {
            return assignmentBasedOnLoad;
        } else if ("interests".equalsIgnoreCase(strategy)) {
            return assignmentBasedOnInterests;
        } else {
            throw new IllegalArgumentException("Invalid search strategy: " + strategy);
        }

    }

    
}
