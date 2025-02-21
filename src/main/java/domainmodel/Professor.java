package domainmodel;

import java.util.List;

public class Professor {
    private String username;
    private String professoreName;
    private String interests;
    private List<TraineeshipPosition> supervisedPositions;

    public String getUsername() {
        return username;
    }
    public String getProfessoreName() {
        return professoreName;
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
    
    public void setProfessoreName(String professoreName) {
        this.professoreName = professoreName;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setSupervisedPositions(List<TraineeshipPosition> supervisedPositions) {
        this.supervisedPositions = supervisedPositions;
    }

}
