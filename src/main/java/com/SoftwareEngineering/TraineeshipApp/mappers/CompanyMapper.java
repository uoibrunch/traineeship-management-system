package com.SoftwareEngineering.TraineeshipApp.mappers;


import com.SoftwareEngineering.TraineeshipApp.domainmodel.Company;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CompanyMapper extends JpaRepository<Company, Integer>{

    Company findByUsername(String username);
    
    List<Company> findByCompanyLocation(String location);
}