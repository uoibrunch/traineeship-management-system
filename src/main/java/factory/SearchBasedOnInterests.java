package factory;

import mappers.StudentMapper;
import mappers.TraineeshipPositionsMapper;

import java.util.List;

import domainmodel.*;

public class SearchBasedOnInterests implements PositionsSearchStrategy{
    
    private TraineeshipPositionsMapper positionsMapper;
    private StudentMapper studentMapper;

    public List<TraineeshipPosition> search(String applicantUsername){
        return null;
    }


}
