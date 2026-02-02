package com.ivanbasic.learnspring.security;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Custom implementation of JdbcUserDetailsManager that loads employee_id
 * from the users table and creates EmployeeLinkedUserDetails objects.
 */
public class EmployeeLinkedUserDetailsManager extends JdbcUserDetailsManager {

    public EmployeeLinkedUserDetailsManager(DataSource dataSource) {
        super(dataSource);
        // Override the default query to include employee_id
        setUsersByUsernameQuery(
            "SELECT username, password, enabled, employee_id FROM users WHERE username = ?"
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> users = loadUsersByUsername(username);
        
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        
        return users.get(0); // Contains EmployeeLinkedUserDetails
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(
            getUsersByUsernameQuery(),
            new String[]{username},
            new CustomUserDetailsRowMapper()
        );
    }

    private class CustomUserDetailsRowMapper implements RowMapper<UserDetails> {
        @Override
        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            String username = rs.getString(1);
            String password = rs.getString(2);
            boolean enabled = rs.getBoolean(3);
            Integer employeeId = (Integer) rs.getObject(4);  // Handles NULL properly
            
            List<GrantedAuthority> authorities = loadUserAuthorities(username);
            
            return new EmployeeLinkedUserDetails(
                username, 
                password, 
                enabled, 
                true,  // accountNonExpired
                true,  // credentialsNonExpired
                true,  // accountNonLocked
                authorities, 
                employeeId
            );
        }
    }
}