package com.SoftwareEngineering.TraineeshipApp.domainmodel;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "logbooks")
public class Logbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logbook_id")
    private int logbookId;

    @Column(name = "description")
    private String  description;

    @Column(name = "date")
    private LocalDate date;

    
    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "traineeship_position_id") 
    private TraineeshipPosition traineeshipPosition;

    public void setTraineeshipPosition(TraineeshipPosition position){
        this.traineeshipPosition = position;
    }

    public void assignDate(LocalDate date){
        this.date = date;
    }

    public void assignDescription(String description){
        this.description = description;
    }

    public int getLogbookId() {
        return logbookId;
    }

    public void setLogbookId(int logbookId) {
        this.logbookId = logbookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
