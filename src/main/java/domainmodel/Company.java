package domainmodel;

import java.util.List;

public class Company {

    private String username;
    private String companyName;
    private String companyLocation;
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
