package ca.mcgill.ecse321.grocerystoresystem.service;

//import java.sql.Date;
//import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystoresystem.model.*;
import ca.mcgill.ecse321.grocerystoresystem.dao.*;

@Service 
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ShiftRepository shiftRepository;
    @Autowired 
    CustomerRepository customerRepository;

    @Transactional
    public Employee createEmployee(String first_name, String last_name, String email,  String password, EmployeeStatus empStatus ){
        
        if (first_name == null || last_name == null || first_name.length() == 0 || last_name.length() == 0) throw new IllegalArgumentException("Please enter a valid name");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Please enter a valid email");
        if (password == null || password.length() < 8) throw new IllegalArgumentException("Please enter a password that is at least 8 characters long");
        if (empStatus == null) throw new IllegalArgumentException("Must input employee status to create employee account");
        if (employeeRepository.findEmployeeByEmail(email) != null) throw new IllegalArgumentException("Email in use by existing employee, please choose another");
        Employee employee = new Employee ();
        employee.setFirstName(first_name);
        employee.setLastName(last_name);
        employee.setEmpStatus(empStatus);
        employee.setEmail(email);
        employee.setPassword(password);
        employeeRepository.save(employee);
        return employee;

    }
    @Transactional
    public Employee getEmployee(String email){
        Employee employee= employeeRepository.findEmployeeByEmail(email);
        if (employee == null) throw new IllegalArgumentException("Please enter a valid employee email");
        return employee;

    }

    @Transactional
    public List<Employee> getAllEmployees(){

        return toList(employeeRepository.findAll());

    }
    @Transactional
    public Employee deleteEmployee(String email){

        if (email ==null || employeeRepository.findEmployeeByEmail(email)!=null) throw new IllegalArgumentException("Enter a valid employee email");
        Employee e=employeeRepository.findEmployeeByEmail(email);
        employeeRepository.delete(e); 
        return e;
    

    }
    @Transactional
    public Employee login (String email, String password){
        if (email==null) throw new IllegalArgumentException("Please enter a valid email");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee==null) throw new IllegalArgumentException("Employee not found");
        if (!employee.getPassword().equals(password)) throw new IllegalArgumentException("Wrong password");
        employee.setLoginStatus(true);
        return employee;

    }
    @Transactional
    public Employee logout (String email){
        if (email==null) throw new IllegalArgumentException("Please enter a valid email");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee==null) throw new IllegalArgumentException("Employee not found");
        employee.setLoginStatus(false);
        return null;
    }
    @Transactional
    public Employee updatePassword(String email, String oldPassword, String newPassword){
        if (email == null) throw new IllegalArgumentException("Must enter email account");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) throw new IllegalArgumentException("Employee does not exist");
        if (!employee.getPassword().equals(oldPassword)) throw new IllegalArgumentException("Wrong password. Input correct old password");
        if (newPassword.length() >= 8) employee.setPassword(newPassword);
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public Employee updateAddress (String email, String newAddress){
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) throw new IllegalArgumentException ("Must be valid employee");
        //insert code to get address 
        return employee;
    }

    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}

