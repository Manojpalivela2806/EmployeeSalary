package com.emp.EmployeeSalary.Services;

import com.emp.EmployeeSalary.DTO.EmployeeDto;
import com.emp.EmployeeSalary.Repository.EmployeeRepository;
import com.emp.EmployeeSalary.entity.Attendance;
import com.emp.EmployeeSalary.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    //==================================================================================================================
    public Employee addEmployee(EmployeeDto employeeDto)
    {
        Employee emp = new Employee();
        emp.setId(employeeDto.getId());
        emp.setName(employeeDto.getName());
        emp.setDesig(employeeDto.getDesig());
        emp.setLoc(employeeDto.getLoc());
        emp.setPwd(employeeDto.getPwd());
        emp.setAge(employeeDto.getAge());
        emp.setEMail(employeeDto.getEMail());
        emp.setFixedSalary(employeeDto.getFixedSalary());
        emp.setAttendance(employeeDto.getAttendance());
        return employeeRepository.save(emp);
    }

    //==================================================================================================================
    public List<EmployeeDto> getEmployee()
    {
        List<Employee>emp=employeeRepository.findAll();
        List<EmployeeDto>employeeDto = new ArrayList<>();
         if(!emp.isEmpty())
        {
            for (Employee emp1 :emp)
            {
                EmployeeDto dto1=new EmployeeDto();
                dto1.setId(emp1.getId());
                dto1.setName(emp1.getName());
                dto1.setDesig(emp1.getDesig());
                dto1.setLoc(emp1.getLoc());
                dto1.setPwd(emp1.getPwd());
                dto1.setAge(emp1.getAge());
                dto1.setEMail(emp1.getEMail());
                dto1.setFixedSalary(emp1.getFixedSalary());
                employeeDto.add(dto1);
            }
        }
        return employeeDto;
    }
    //==================================================================================================================
    public Employee getById(long id)
    {
        Optional<Employee> optionalEmployee= employeeRepository.findById(id);
        return optionalEmployee.get();
    }
    //==================================================================================================================
    public Employee updateEmployee(EmployeeDto employeeDto)
    {
        Optional<Employee> option = employeeRepository.findById(employeeDto.getId());
        Employee emp =option.get();
        if(option.isPresent())
        {
            emp.setId(employeeDto.getId());
            emp.setName(employeeDto.getName());
            emp.setDesig(employeeDto.getDesig());
            emp.setLoc(employeeDto.getLoc());
            emp.setPwd(employeeDto.getPwd());
            emp.setAge(employeeDto.getAge());
            emp.setEMail(employeeDto.getEMail());
            emp.setFixedSalary(employeeDto.getFixedSalary());
            emp.setAttendance(employeeDto.getAttendance());
        }
        return employeeRepository.save(emp);
    }
    //==================================================================================================================
    public String delete(long id)
    {
        employeeRepository.deleteById(id);
        return "Deleted";
    }
    //==================================================================================================================
    public String currentMonthSalary(long id)
    {
        double cmsalary =0.0;
        Optional<Employee> option = employeeRepository.findById(id);
        if(option.isPresent())
        {
            Employee emp =option.get();
            double total = emp.getFixedSalary();
            Attendance attendance = emp.getAttendance();
            int workingDays = attendance.getNoOfWorkedDays();
            int totalDays=attendance.getNoOfDays();
            if((double)workingDays/totalDays>=0.8)
            {
                cmsalary=total;
            }
            else
            {
                cmsalary=((double) workingDays/totalDays)*total;
            }
        }
        return "monthly salary of employee "+id+" is "+cmsalary;
    }
    //==================================================================================================================
    public String getAllInfo(long id,String pwd) {
        String s = null;
        Optional<Employee> option = employeeRepository.findById(id);
        if (option.isPresent()) {
            double monthsal;
            Employee emp = option.get();
            long id1 = emp.getId();
            String name1 = emp.getName();
            String pwd1 = emp.getPwd();
            String desig1 = emp.getDesig();
            String  loc1= emp.getLoc();
            int age1=emp.getAge();
            String email1 = emp.getEMail();
            double sal1 = emp.getFixedSalary();
            Attendance attendance = emp.getAttendance();
            long id2 = attendance.getId();
            int workedDays = attendance.getNoOfWorkedDays();
            int totalDays = attendance.getNoOfDays();
            if((double)workedDays/totalDays>=0.8)
            {
                monthsal=sal1;
            }
            else
            {
                monthsal=((double) workedDays/totalDays)*sal1;
            }
            if (id1 == id && pwd1.equalsIgnoreCase(pwd)) {
                s =     "id of employee: " + id  +"\n"+
                        " name of employee: " + name1 + "\n"+
                        " designation of employee: " + desig1 + "\n"+
                        "location of employee: "+ loc1 +"\n"+
                        "employee e-mail id: "+ email1+"\n"+
                        "age of employee: "+age1+"\n"+
                        "employee worked days in month: "+workedDays+"\n"+
                        " month salary he got: " + monthsal;
                return s;
            } else {
                return "id and password are not authorized";
            }
        } else {
            return "id and password are not authorized9";
        }
    }
}
