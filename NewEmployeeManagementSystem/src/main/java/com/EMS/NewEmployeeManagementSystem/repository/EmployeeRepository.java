package com.EMS.NewEmployeeManagementSystem.repository;

import com.EMS.NewEmployeeManagementSystem.entity.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @Query(value = "select min(salary) from Employee" , nativeQuery = true)
//    public int minSalary();
//
//    @Query(value = "select max(salary) from Employee" , nativeQuery = true)
//    public int maxSalary();


//    @Query(value = " SELECT * FROM employee WHERE salary >= minSalary AND salary <= nextSalary" , nativeQuery = true)
//    int countEmployee(@Param("currentSalary") BigDecimal minSalary, @Param("nextSalary") BigDecimal nextSalary );

//    @Query("SELECT COUNT(e) FROM Employee e WHERE e.salary >= :minSalary AND e.salary <= :maxSalary")
//    int countEmployeesBySalaryRange(@Param("minSalary") BigDecimal minSalary, @Param("maxSalary") BigDecimal maxSalary);

}
