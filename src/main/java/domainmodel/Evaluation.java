package domainmodel;

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

}
