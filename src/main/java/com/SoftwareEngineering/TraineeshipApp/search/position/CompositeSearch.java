package com.SoftwareEngineering.TraineeshipApp.search.position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.TraineeshipPosition;

@Service
public class CompositeSearch implements PositionsSearchStrategy {

    @Autowired
    private SearchBasedOnLocation searchBasedOnLocation;

    @Autowired
    private SearchBasedOnInterests searchBasedOnInterests;

    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {
        
        List<TraineeshipPosition> byLocation = searchBasedOnLocation.search(applicantUsername);
        
        List<TraineeshipPosition> byInterests = searchBasedOnInterests.search(applicantUsername);

        Set<TraineeshipPosition> combinedResults = new HashSet<>(byLocation);
        combinedResults.addAll(byInterests);
        
        return new ArrayList<>(combinedResults);
    }
}

