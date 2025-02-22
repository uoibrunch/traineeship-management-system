package mappers;

import domainmodel.User;
import domainmodel.Company; 
import org.springframework.stereotype.Repository;


@Repository
public class CompanyMapper implements UserMapper {

   

    @Override
    public User findByUsername(String username) {
        
        return null; 
    }

    
    public Company findByCompanyId(String companyId) {
        
        return null;
    }
}