package domainmodel;

import java.time.LocalDate;
import java.util.List;

public class TraineeshipPosition {

    private int id;
    private String title;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String topics;
    private String skills;
    private boolean isAssigned;
    private String studentLogbook;
    private boolean passFailGrade;
    private Student student;
    private Professor supervisor;
    private Company company;
    private List <Evaluation> evaluations;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getFromDate() {
        return fromDate;
    }
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
    public LocalDate getToDate() {
        return toDate;
    }
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
    public String getTopics() {
        return topics;
    }
    public void setTopics(String topics) {
        this.topics = topics;
    }
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    public boolean isAssigned() {
        return isAssigned;
    }
    public void setAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }
    public String getStudentLogbook() {
        return studentLogbook;
    }
    public void setStudentLogbook(String studentLogbook) {
        this.studentLogbook = studentLogbook;
    }
    public boolean isPassFailGrade() {
        return passFailGrade;
    }
    public void setPassFailGrade(boolean passFailGrade) {
        this.passFailGrade = passFailGrade;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Professor getSupervisor() {
        return supervisor;
    }
    public void setSupervisor(Professor supervisor) {
        this.supervisor = supervisor;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public List<Evaluation> getEvaluations() {
        return evaluations;
    }
    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
    

}
