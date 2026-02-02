package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.model.db2.Employee;
import com.ivanbasic.learnspring.repository.db2.EmployeeRepo;
import com.ivanbasic.learnspring.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRepo employeeRepo;
    
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public String test() {
        return "Number of employees="+employeeRepo.count();
    }
    
    /**
     * Lesson 055: SQL-based filtering (better than @PostFilter)
     * 
     * Business rules:
     * - ADMIN users see all employees
     * - MANAGER users see only employees in their department
     * - Users without employee_id see nothing
     */
    @Override
    public List<Employee> getEmployeesForManager(Authentication auth) {
        // Check if user is ADMIN
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("SCOPE_ADMIN"))) {
            return employeeRepo.findAll();  // Admins see all
        }
        
        // Get CustomUserDetails - handle both Basic Auth and JWT
        CustomUserDetails user = null;
        
        if (auth.getPrincipal() instanceof CustomUserDetails) {
            // HTTP Basic Authentication - principal is CustomUserDetails
            user = (CustomUserDetails) auth.getPrincipal();
            LOG.debug("Using CustomUserDetails from Basic Auth");
        } else if (auth.getPrincipal() instanceof Jwt) {
            // JWT Authentication - principal is Jwt, need to load CustomUserDetails
            Jwt jwt = (Jwt) auth.getPrincipal();
            String username = jwt.getSubject();
            LOG.debug("Loading UserDetails for username: {}", username);
            
            UserDetails loadedUser = userDetailsService.loadUserByUsername(username);
            
            if (loadedUser instanceof CustomUserDetails) {
                user = (CustomUserDetails) loadedUser;
                LOG.debug("Loaded CustomUserDetails with employeeId: {}", user.getEmployeeId());
            } else {
                LOG.warn("UserDetailsService returned {} instead of CustomUserDetails", 
                         loadedUser.getClass().getName());
                return Collections.emptyList();
            }
        } else {
            // Unknown authentication type
            LOG.warn("Unknown principal type: {}", auth.getPrincipal().getClass().getName());
            return Collections.emptyList();
        }
        
        // Users without employee_id see nothing
        if (user == null || user.getEmployeeId() == null) {
            LOG.debug("User has no employee_id, returning empty list");
            return Collections.emptyList();
        }
        
        LOG.debug("Looking up employee with ID: {}", user.getEmployeeId());
        
        // Find manager's employee record
        Employee manager = employeeRepo.findByEmployeeId(user.getEmployeeId());
        if (manager == null || manager.getDepartment() == null) {
            LOG.debug("Manager not found or has no department");
            return Collections.emptyList();
        }
        
        LOG.debug("Manager {} belongs to department {}", 
                  manager.getFirstName(), manager.getDepartment().getDepartmentId());
        
        // Query only employees in manager's department (SQL WHERE clause!)
        List<Employee> employees = employeeRepo.findByDepartment_DepartmentId(
            manager.getDepartment().getDepartmentId()
        );
        
        LOG.info("Returning {} employees for manager {}", employees.size(), user.getUsername());
        
        return employees;
    }
}