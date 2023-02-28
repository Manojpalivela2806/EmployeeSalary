package com.emp.EmployeeSalary.Repository;

import com.emp.EmployeeSalary.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
