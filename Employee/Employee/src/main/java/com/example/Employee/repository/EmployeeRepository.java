package com.example.Employee.repository;

import com.example.Employee.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    // Find employee by unique email
    Optional<EmployeeEntity> findByEmail(String email);

    // Find employee by unique employee code
    Optional<EmployeeEntity> findByEmployeeCode(String employeeCode);

    // Find all employees by department
//    List<EmployeeEntity> findByDepartment(String department);

}