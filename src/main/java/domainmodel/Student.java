package domainmodel;

public class Student {
    private String username;
    private String studentName;
    private String AM;
    private double avgGrade;
    private String prefferedLocation;
    private String  interests;
    private String skills;
    private boolean lookingForTraineeship;
    private TraineeshipPosition assignedTraineeship;

    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getAM() {
        return AM;
    }
    public void setAM(String aM) {
        AM = aM;
    }
    public double getAvgGrade() {
        return avgGrade;
    }
    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }
    public String getPrefferedLocation() {
        return prefferedLocation;
    }
    public void setPrefferedLocation(String prefferedLocation) {
        this.prefferedLocation = prefferedLocation;
    }
    public String getInterests() {
        return interests;
    }
    public void setInterests(String interests) {
        this.interests = interests;
    }
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    public boolean isLookingForTraineeship() {
        return lookingForTraineeship;
    }
    public void setLookingForTraineeship(boolean lookingForTraineeship) {
        this.lookingForTraineeship = lookingForTraineeship;
    }
    public TraineeshipPosition getAssignedTraineeship() {
        return assignedTraineeship;
    }
    public void setAssignedTraineeship(TraineeshipPosition assignedTraineeship) {
        this.assignedTraineeship = assignedTraineeship;
    }

}
