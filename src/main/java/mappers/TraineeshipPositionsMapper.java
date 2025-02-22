package mappers;


import domainmodel.User;
import domainmodel.TraineeshipPosition;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TraineeshipPositionsMapper extends JpaRepository<TraineeshipPosition,Integer>{

}