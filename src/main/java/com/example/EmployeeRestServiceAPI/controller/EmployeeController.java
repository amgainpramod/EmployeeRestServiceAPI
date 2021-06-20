package com.example.EmployeeRestServiceAPI.controller;

import com.example.EmployeeRestServiceAPI.entities.Employee;
import com.example.EmployeeRestServiceAPI.repository.EmployeeRepository;
import com.example.EmployeeRestServiceAPI.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
//@RequestMapping("employees/")
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("addEmployee")
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("addListEmployees")
    public List<Employee> addAllEmployees(@RequestBody List<Employee> employees) {
        return employeeService.saveAllEmployees(employees);
    }

    @GetMapping("allemployees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("employeeswithname")
    public List<Employee> getAllEmployeesWithName(@RequestParam(value = "emp_name") String employeename) {
        return employeeService.findEmployeesByName(employeename);
    }

    @GetMapping("employeewithid")
    public Employee getEmployeeWithId(@RequestParam(value = "emp_id") @Min(value = 1, message = "Employee ID should be greater than 0") int emp_id) {
        return employeeService.findEmployeeById(emp_id);
    }

    @GetMapping("employeeswithids")
    public List<Employee> getEmployeesByIds(@RequestBody List<Integer> ids) {
        return employeeService.findAllEmployeesByIds(ids);
    }

    @GetMapping("empByAgeLessThan")
    public List<Employee> getEmployeesByAgeLessThan(@RequestParam(value = "age") int age) {
        return employeeRepository.findByAgeLessThan(age);
    }

    @GetMapping("empByDeptAndAgeLessThanEqual")
    public List<Employee> getEmpByDeptAndAgeLessEqual(@RequestParam(value = "department") String department, @RequestParam(value = "age") int age) {
        return employeeRepository.findByDepartmentAndAgeLessThanEqual(department, age);
    }

    @GetMapping("empbydeptandage")
    public List<Employee> getEmpByDeptAndAgeLessThan(@RequestParam(value = "department") String department, @RequestParam(value = "age") int age) {
        return employeeRepository.findByDepartmentAndAgeLessThan(department, age);
    }

    @GetMapping("empByDeptAndAgeGreaterThanEqual")
    public List<Employee> getEmpByDeptAndAgeGreaterEqual(@RequestParam(value = "department") String department, @RequestParam(value = "age") int age) {
        return employeeRepository.findByDepartmentAndAgeGreaterThanEqual(department, age);
    }

    @GetMapping("employeesbyage")
    public List<Employee> getEmployeesByAge(@RequestParam(value = "age") int age)
    {
        return employeeRepository.findTop5ByAge(age);
    }

    @GetMapping("empnamestartingwith")
    public List<Employee> getAllEmployeeNameStartingWith(@RequestParam(value = "name_start_with")String employeename){
        return employeeRepository.findByEmployeenameStartingWith(employeename);
    }

    @GetMapping("empnameendingwith")
    public List<Employee> getAllEmployeeNameEndingWith(@RequestParam(value = "name_end_with")String employeename){
        return employeeRepository.findByEmployeenameEndingWith(employeename);
    }

    @GetMapping("empnamecontaining")
    public List<Employee> getAllEmployeeNameContaining(@RequestParam(value = "name_contain")String employeename){
        return employeeRepository.findByEmployeenameContaining(employeename);
    }



    /***
     *  More @GetMappings here......
     */

    @PutMapping("updateEmployee/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @RequestParam(value = "emp_id") int id) {
        return employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam(value = "emp_id") int id) {
        if (employeeService.checkEmployeeExist(id)) {
            Employee empToDelete = employeeService.findEmployeeById(id);
            employeeRepository.delete(empToDelete);
            return new ResponseEntity<>("Employee Record Successfully deleted", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Employee Record with id " + id + " doesn't exist!", HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("deleteListEmployees")
    public ResponseEntity<String> deleteEmployeeList(@RequestBody List<Integer> ids){
        employeeService.deleteAllEmployeesByIds(ids);
        return new ResponseEntity<>("Employee Records successfully deleted", HttpStatus.CREATED);

    }

}
