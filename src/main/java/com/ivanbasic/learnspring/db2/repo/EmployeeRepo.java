package com.ivanbasic.learnspring.db2.repo;

import com.ivanbasic.learnspring.db2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    //by int field
    int countByEmployeeId(int id);
    int countByEmployeeIdBetween( int id1, int id2);

    //by string field
    int countByFirstName(String name);
    int countByFirstNameStartingWith(String start);
    int countByFirstNameContaining(String containing);

    //by date-time field
    int countByHireDateBetween(LocalDateTime ldt1, LocalDateTime ldt2);

    //by child-entity-field (@ManyToOne)
    int countByDepartmentDepartmentIdIn(List<Integer> listInt);

    //by 2 fields
    int countByEmployeeIdAndFirstName( int id1, String name);

    //by JPQL query
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.employeeId = :id")
    int customJPQLCountByEmployeeId(int id);

    //by native query
    @Query(value="SELECT COUNT(*) FROM schema2.employees e WHERE e.employee_id = :id", nativeQuery = true)
    int customNativeCountByEmployeeId(int id);

}


