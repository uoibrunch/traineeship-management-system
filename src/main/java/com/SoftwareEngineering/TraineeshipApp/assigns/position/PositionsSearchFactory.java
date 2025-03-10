package com.SoftwareEngineering.TraineeshipApp.assigns.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionsSearchFactory {

    @Autowired
    private SearchBasedOnLocation searchBasedOnLocation;

    @Autowired
    private SearchBasedOnInterests searchBasedOnInterests;

    @Autowired
    private CompositeSearch compositeSearch;  
    
    
    public PositionsSearchStrategy create(String strategy) {
        if ("location".equalsIgnoreCase(strategy)) {
            return searchBasedOnLocation;
        } else if ("interests".equalsIgnoreCase(strategy)) {
            return searchBasedOnInterests;
        } else if ("both".equalsIgnoreCase(strategy)) {
            return compositeSearch;
        } else {
            throw new IllegalArgumentException("Invalid search strategy: " + strategy);
        }
    }
    

}
