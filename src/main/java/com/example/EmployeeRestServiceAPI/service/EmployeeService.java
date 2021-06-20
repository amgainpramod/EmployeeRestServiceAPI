package com.example.EmployeeRestServiceAPI.service;

import com.example.EmployeeRestServiceAPI.entities.Employee;
import com.example.EmployeeRestServiceAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public EmployeeService() {

    }

    public List<Employee> saveAllEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> findAllEmployeesByIds(List<Integer> ids) { // [4,5,45,50]
        List<Employee> employeeList = new ArrayList<>();
        for (int id : ids) {
            if (employeeRepository.findById(id) != null) {
                employeeList.add(employeeRepository.findById(id));
            }
        }
        return employeeList;
    }

//    public List<Employee> findAllEmployeesByIds(List<Integer> ids){
//        return employeeRepository.findAllById(ids);
//    }



    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id);

    }

    public List<Employee> findEmployeesByName(String employeename) {
        return employeeRepository.findByEmployeename(employeename);
    }





    public Employee updateEmployee(Employee employee, int id) {
        Employee existingEmployee = employeeRepository.findById(id);
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteAllEmployeesByIds(List<Integer> ids) {
        employeeRepository.deleteAllById(ids);
    }

    public boolean checkEmployeeExist(int id) {
        //check if the id(primary key) already exists. If exists, print book already exists otherwise add book
        Employee employee = employeeRepository.findById(id);
        if (employee != null) {
            return true;
        }
        return false;
    }

}
