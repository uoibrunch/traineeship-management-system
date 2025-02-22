package domainmodel;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "student_name")
    private String studentName;

    @Column(name= "AM")
    private String AM;

    @Column(name = "avg_grade")
    private double avgGrade;

    @Column(name = "preferred_location")
    private String prefferedLocation;

    @Column(name = "ineterests")
    private String  interests;

    @Column(name = "skills")
    private String skills;

    @Column(name =  "looking_for_traineeship")
    private boolean lookingForTraineeship;

    @OneToOne(mappedBy = "student")
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
