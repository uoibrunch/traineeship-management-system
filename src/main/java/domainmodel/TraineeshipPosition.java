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
    

}
