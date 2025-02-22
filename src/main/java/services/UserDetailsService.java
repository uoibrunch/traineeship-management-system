package services;

import domainmodel.UserDetails;

public interface UserDetailsService {

    UserDetails loadUserByUsername(String username);

}
