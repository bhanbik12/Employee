package com.EMS.NewEmployeeManagementSystem.service;

import com.EMS.NewEmployeeManagementSystem.entity.Address;
import com.EMS.NewEmployeeManagementSystem.entity.Employee;
import com.EMS.NewEmployeeManagementSystem.repository.AddressRepository;
import com.EMS.NewEmployeeManagementSystem.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Console;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private  AddressRepository addressRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> employee1 = employeeRepository.findById(employee.getId());
        Employee employee2 = null;
        if (!employee1.isEmpty()){
            employee2 = employee1.get();
            employee2.setName(employee2.getName());
            employee2.setEmail(employee2.getEmail());
            employee2.setSalary(employee2.getSalary());
            employeeRepository.save(employee);
        }
      return   employee2;
    }

    public String deleteEmployee(long id) {
        employeeRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public Employee getEmployeeById(long id) {
       Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee Not Found With ID : " + id));
        return employee;
    }

    public List<Employee> setAddressToEmployee() {

       List<Employee> employeeList = employeeRepository.findAll();
       List<Address> addressList = addressRepository.findAll();

       

        List<Long> listOfId = addressList.stream().map(address -> address.getId()).collect(Collectors.toList());

        for (int i = 0; i<employeeList.size() ; i++){
            Long addressId = listOfId.get(i);
          Employee employee =   employeeList.get(i);
           employee.setAddress(addressList.get(i));
           employeeRepository.save(employee);


        }
        return employeeList;
        }

    public HashMap<String, Integer> getNumberOfEmployeeBySalaryRange() {

        HashMap<String , Integer > rangeHashMap = new HashMap<String , Integer>();

        List<Employee> employeeList = employeeRepository.findAll();

        List<Double> salaryList = employeeList.stream().map(employee -> employee.getSalary()).collect(Collectors.toList());

        double minSalary = Collections.min(salaryList);
        double maxSalary = Collections.max(salaryList);

//        double minSalary = employeeRepository.minSalary();
//        double maxSalary = employeeRepository.maxSalary();

        double salaryDifference = maxSalary-minSalary;
        int firstDigitValue = Character.getNumericValue(String.valueOf(salaryDifference).charAt(0));

        double range = salaryDifference/firstDigitValue;
        logger.error("range value" , range);

        BigDecimal currentSalary = BigDecimal.valueOf(minSalary);
        while (currentSalary.compareTo(BigDecimal.valueOf(maxSalary)) <= 0){
            BigDecimal nextSalary = currentSalary.add(BigDecimal.valueOf(range));
            String key = currentSalary + "-" + nextSalary;

            rangeHashMap.put(key , 0);

            currentSalary = nextSalary;
        }

        for (int i= 0 ; i<employeeList.size() ; i++){
            Employee employee = employeeList.get(i);
           BigDecimal salary = BigDecimal.valueOf(employee.getSalary());
            for (Map.Entry<String , Integer> entry : rangeHashMap.entrySet()) {
                String key = entry.getKey();
           //     System.out.println(key.split("-"));
                String[] keyArray = key.split("-");
//                keyArray[0] keyArray[1]
//                    if(salary.compareTo(ne))
              if (salary.compareTo(new BigDecimal(keyArray[0])) >= 0 && salary.compareTo(new BigDecimal(keyArray[1])) <= 0 ){
                  rangeHashMap.put(key , rangeHashMap.get(key)+1);
              }

            }

        }

        return rangeHashMap;



    }

    public List<Employee> getEmployeesWithReversedFirstName() {
        List<Employee> employeeList = employeeRepository.findAll();
        for (int i=0 ; i<employeeList.size() ; i++){
            Employee employee = employeeList.get(i);
            String employeeName = employee.getName();
//            char[] chars = employeeName.toCharArray();
//            int left =0;
//            int right=employeeName.length()-1;
//
//            while (left < right){
//                char temp = chars[left];
//                chars[left] = chars[right];
//                chars[right] = temp;
//                left++;
//                right--;
//            }
            //String name="bishwo";

            String reversedString ="";
            for(int j=employeeName.length()-1 ;j>=0; j--){
                reversedString += employeeName.charAt(j);
            }
            //String reversedName = new String(chars);
            employee.setName(reversedString);
        }
        return employeeList;
    }


//
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));
//
//        Address address = addressRepository.findById(addressId)
//                .orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + addressId));
//
//        employee.setAddress(address);
//        employeeRepository.save(employee);
//        return "Address assigned to employee successfully";
  //  }
}
