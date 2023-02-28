package com.emp.EmployeeSalary.Controller;

import com.emp.EmployeeSalary.DTO.EmployeeDto;
import com.emp.EmployeeSalary.Services.EmployeeService;
import com.emp.EmployeeSalary.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    //==================================================================================================================
    @PostMapping("/add")
    public Employee insertEmployee(@RequestBody EmployeeDto employeeDto)
    {
        return employeeService.addEmployee(employeeDto);
    }
    //==================================================================================================================
    @GetMapping("/get")
    public List<EmployeeDto> getEmployees()
    {
        return employeeService.getEmployee();
    }
    //==================================================================================================================
    @GetMapping("/get/{id}")   //localhost:9090/employee/get/id(when @pathvariable is used)  localhost:9090/employee/get?id=1
    public Employee getByEmpId(@PathVariable long id)
    {
        return employeeService.getById(id);
    }
    //==================================================================================================================
    @PutMapping("/put/{id}")
    public Employee updateEmployee(@RequestBody EmployeeDto emp)
    {
        return employeeService.updateEmployee(emp);
    }
    //==================================================================================================================
    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable long id)
    {
         employeeService.delete(id);
    }
    //==================================================================================================================
    @GetMapping("/get/salary/{id}")
    public String currentMonthSalary(@PathVariable long id)
    {
        return employeeService.currentMonthSalary(id);
    }
    //==================================================================================================================
    @GetMapping("/authorized/{id}/{pwd}")
    public String getAllInfo(@PathVariable long id,@PathVariable String pwd)
    {
        return employeeService.getAllInfo(id,pwd);
    }
}
