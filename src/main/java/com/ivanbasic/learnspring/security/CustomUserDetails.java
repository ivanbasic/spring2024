package com.ivanbasic.learnspring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Custom UserDetails that extends Spring Security's User class
 * to include employee_id from the users table.
 */
public class CustomUserDetails extends User {
    
    private final Integer employeeId;
    
    public CustomUserDetails(String username, 
                           String password, 
                           boolean enabled,
                           boolean accountNonExpired,
                           boolean credentialsNonExpired,
                           boolean accountNonLocked,
                           Collection<? extends GrantedAuthority> authorities,
                           Integer employeeId) {
        super(username, password, enabled, accountNonExpired, 
              credentialsNonExpired, accountNonLocked, authorities);
        this.employeeId = employeeId;
    }
    
    public Integer getEmployeeId() {
        return employeeId;
    }
}
