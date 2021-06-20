package com.example.EmployeeRestServiceAPI.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @NotNull
    private String employeename;

    @NotNull(message = "Department is required")
    @Pattern(regexp = "^[0-9A-Za-z ]*$", message = "Department accepts only Alphanumeric value") //DEPT IN CAPS
    @Size(min = 2, max = 50, message = "Department accepts only upto 50 character and minimum 2 characters")
    private String department;

    @NotNull
    @Temporal(TemporalType.DATE)
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") removed because global rule is defined in main class for date and time format
    private Date joiningdate;

    @NotNull(message = "Age is required")
    @Min(value =18, message = "The minimum age requirement is 18")
    private int age;

    @NotNull
    private String address;

    @NotNull
    private float salary;

    private ZonedDateTime lefton;

    @NotNull
    private boolean leftjob;


    public Employee(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(Date joiningdate) {
        this.joiningdate = joiningdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public ZonedDateTime getLefton() {
        return lefton;
    }

    public void setLefton(ZonedDateTime lefton) {
        this.lefton = lefton;
    }

    public boolean isLeftjob() {
        return leftjob;
    }

    public void setLeftjob(boolean leftjob) {
        this.leftjob = leftjob;
    }
}
