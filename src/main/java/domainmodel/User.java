package domainmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;

@Entity
@Table( name = "users")
public class User implements UserDetails{

    @Column(name="user_name", unique=true)
    private String username;
    
    @Column(name="password")
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;


   public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // should change later 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // should change later
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // should change later
    }

    @Override
    public boolean isEnabled() {
        return true; // should change later
    }

    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
