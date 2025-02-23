package com.SoftwareEngineering.TraineeshipApp.factory.search;

import com.SoftwareEngineering.TraineeshipApp.mappers.StudentMapper;
import com.SoftwareEngineering.TraineeshipApp.mappers.TraineeshipPositionsMapper;

import java.util.List;

import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;
import com.SoftwareEngineering.TraineeshipApp.factory.positions.PositionsSearchStrategy;

public class SearchBasedOnInterests implements PositionsSearchStrategy{
    
    private TraineeshipPositionsMapper positionsMapper;
    private StudentMapper studentMapper;

    public List<TraineeshipPosition> search(String applicantUsername){
        return null;
    }


}
