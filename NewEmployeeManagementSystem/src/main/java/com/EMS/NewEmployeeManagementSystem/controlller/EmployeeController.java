package com.EMS.NewEmployeeManagementSystem.controlller;

import com.EMS.NewEmployeeManagementSystem.entity.Employee;
import com.EMS.NewEmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public Employee saveEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeService.saveEmployee(employee);
        return employee1;
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployee (){
        List<Employee> employeeList = employeeService.getAllEmployee();
        return employeeList;
    }

    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeService.updateEmployee(employee);
        return employee1;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        System.out.println("delete mapping called");
        String deleted = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("addressToEmployee")
    public ResponseEntity<List<Employee>> setAddressToEmployee(){
        List<Employee> joinedAddressToEmployee = employeeService.setAddressToEmployee();
        return ResponseEntity.ok(joinedAddressToEmployee);

    }

    @GetMapping("/salaryRange")
    public HashMap<String , Integer> getNumberOfEmployeeBySalaryRange(){
        return employeeService.getNumberOfEmployeeBySalaryRange();

    }

    @GetMapping("/reversedFirstName")
    public ResponseEntity<List<Employee>> getEmployeesWithReversedFirstName(){
        List<Employee> employeeList = employeeService.getEmployeesWithReversedFirstName();
        return ResponseEntity.ok(employeeList);
    }

}
