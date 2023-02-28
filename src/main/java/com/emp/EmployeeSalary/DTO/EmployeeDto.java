package com.emp.EmployeeSalary.DTO;

import com.emp.EmployeeSalary.entity.Attendance;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDto
{
    private long id;
    private String name;
    private String desig;
    private String loc;
    private String pwd;
    private double fixedSalary;
    private int age;
    private String eMail;
    private Attendance attendance;
}
