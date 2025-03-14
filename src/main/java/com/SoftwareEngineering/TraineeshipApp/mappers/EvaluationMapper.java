package com.SoftwareEngineering.TraineeshipApp.mappers;

import org.springframework.stereotype.Repository;
import com.SoftwareEngineering.TraineeshipApp.domainmodel.*;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EvaluationMapper extends JpaRepository<Evaluation,Integer>{

    Optional<Evaluation> findByTraineeshipPositionAndEvaluationType(TraineeshipPosition position, EvaluationType evaluationType);
    Optional<Evaluation> findById(Integer id);
}

