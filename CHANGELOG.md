# Changelog

## Version 0.0.55 - EXPERIMENTAL (Not Merged to Main)
### Added
* Custom UserDetails implementation (CustomUserDetails) with employee_id field
* Custom JdbcUserDetailsManager (CustomJdbcUserDetailsManager) that loads employee_id
* New manager users: ada and karl with ROLE_MANAGER
* SQL-based filtering in EmployeeService for role-based data access
* New endpoint /db2/employees/my-team for managers to see their team
* employee_id column in DB3 users table (nullable)
### Modified
* Employee and Department entities - added getters
* EmployeeRepo - added findByDepartment_DepartmentId() method
* SecurityConfig - uses CustomJdbcUserDetailsManager
### Notes
* **This is a learning experiment branch - NOT merged to main**
* Demonstrates proper extension of JdbcUserDetailsManager
* Shows SQL-based filtering (better performance than @PostFilter)
* Business rules implemented:
  * ADMIN users see all employees
  * MANAGER users see only employees in their department
  * Users without employee_id see nothing
* See CHANGELOG_DETAILS for detailed documentation:
  * 0.0.55a_Method_Level_Filtering.md - Complete technical guide
  * 0.0.55b_Implementation_Summary.md - What was implemented
  * 0.0.55c_Architecture_Diagram.md - Visual architecture
  * 0.0.55d_Testing_Commands.md - Quick test reference
  * 0.0.55e_Final_Status.md - Final implementation status
