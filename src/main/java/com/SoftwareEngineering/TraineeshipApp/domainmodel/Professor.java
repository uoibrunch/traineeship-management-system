package com.SoftwareEngineering.TraineeshipApp.domainmodel;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table( name = "professors")
public class Professor {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int professorId;

    @Column(name="user_name", unique=true)
    private String username;

    @Column(name="professor_name")
    private String professorName;

    @Column(name="interests")
    private String interests;

    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY)
    private List<TraineeshipPosition> supervisedPositions;

    public String getUsername() {
        return username;
    }
    public String getProfessorName() {
        return professorName;
    }

    public String getInterests() {
        return interests;
    }

    public List<TraineeshipPosition> getSupervisedPositions() {
        return supervisedPositions;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setProfessorName(String professoreName) {
        this.professorName = professoreName;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setSupervisedPositions(List<TraineeshipPosition> supervisedPositions) {
        this.supervisedPositions = supervisedPositions;
    }

    public void setProfessorId(int id){
        this.professorId = id;
    }

    public int getProfessorId(){
        return this .professorId;
    }
   
}
