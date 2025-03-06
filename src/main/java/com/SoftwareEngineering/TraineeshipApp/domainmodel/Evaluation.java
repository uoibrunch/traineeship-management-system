package com.SoftwareEngineering.TraineeshipApp.domainmodel;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluations")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "evaluation_type")
    private EvaluationType evaluationType;

    @Column(name = "motivation")
    private int motivation;

    @Column(name = "efficiency")
    private int efficiency;

    @Column(name = "effectiveness")
    private int effectiveness;

    @Column(name = "facility")
    private int facility;

    @Column(name = "guidance")
    private int guidance;

    @ManyToOne
    @JoinColumn(name = "traineeship_position_id")
    private TraineeshipPosition traineeshipPosition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvaluationType getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(EvaluationType evaluationType) {
        this.evaluationType = evaluationType;
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public int getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(int effectiveness) {
        this.effectiveness = effectiveness;
    }

    public TraineeshipPosition getTraineeshipPosition() {
        return traineeshipPosition;
    }

    public void setTraineeshipPosition(TraineeshipPosition traineeshipPosition) {
        this.traineeshipPosition = traineeshipPosition;
    }

    public int getFacility() {
        return facility;
    }
    
    public void setFacility(int facility) {
        this.facility = facility;
    }

    public int getGuidance() {
        return guidance;
    }
    
    public void setGuidance(int guidance) {
        this.guidance = guidance;
    }

}
