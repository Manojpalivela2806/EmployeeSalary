package com.emp.EmployeeSalary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String desig;
    private String loc;
    private String pwd;
    private double fixedSalary;
    private int age;
    private String eMail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="attendance_id",referencedColumnName = "id")
    private Attendance attendance;
}
