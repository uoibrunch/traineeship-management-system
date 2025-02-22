package services.user;

import domainmodel.UserDetails;

public interface UserDetailsService {

    UserDetails loadUserByUsername(String username);

}
