package domainmodel;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table(name = "Company" )
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyId;
    
    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_location")
    private String companyLocation;
    
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List <TraineeshipPosition> positions;

    public String getUsername() {
        return username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public List<TraineeshipPosition> getPositions() {
        return positions;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public void setPositions(List<TraineeshipPosition> positions) {
        this.positions = positions;
    }
}
