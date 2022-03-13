package ca.mcgill.ecse321.grocerystoresystem.service;
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
    @Autowired 
    AddressRepository addressRepository;

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
    public Employee createEmployee(String first_name, String last_name, String email,  String password, EmployeeStatus empStatus, Address address ){
        
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
        employee.setAddress(address);
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public Employee getEmployee(int personID){
        Employee employee= employeeRepository.findEmployeeByPersonID(personID);
        if (employee == null) throw new IllegalArgumentException("Please enter a valid employee email");
        return employee;

    }

    @Transactional
    public List<Employee> getAllEmployees(){

        return toList(employeeRepository.findAll());

    }
    @Transactional
    public boolean deleteEmployee(int personID){
        Employee e=employeeRepository.findEmployeeByPersonID(personID);
        if(e == null) throw new NullPointerException("Employee not found");
        employeeRepository.delete(e); 
        return true;
    }

    @Transactional
    public Employee login (String email, String password){
        if (email==null) throw new IllegalArgumentException("Please enter a valid email");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee==null) throw new NullPointerException("Employee not found");
        if (!employee.getPassword().equals(password)) throw new IllegalArgumentException("Wrong password");
        employee.setLoginStatus(true);
        return employee;

    }
    @Transactional
    public Employee logout (String email){
        if (email==null) throw new IllegalArgumentException("Please enter a valid email");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee==null) throw new NullPointerException("Employee not found");
        employee.setLoginStatus(false);
        return employee;
    }
    @Transactional
    public Employee updatePassword(String email, String oldPassword, String newPassword){
        if (email == null) throw new IllegalArgumentException("Must enter email account");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) throw new NullPointerException("Employee does not exist");
        if (!employee.getPassword().equals(oldPassword)) throw new IllegalArgumentException("Wrong password. Input correct old password");
        if (newPassword.length() >= 8) employee.setPassword(newPassword);
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    public Employee updateAddress(String email, Address newAddress){
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) throw new NullPointerException ("Must be valid employee");
        employee.setAddress(newAddress);
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional 
    public Employee findEmployeeByEmail(String email){
        if (email ==null || email.length() == 0) throw new IllegalArgumentException("Please provide an email");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) throw new NullPointerException("Employee not found");
        return employee;
    }

    @Transactional 
    public Employee findEmployeeByID(int id){
        Employee employee = employeeRepository.findEmployeeByPersonID(id);
        if(employee == null) throw new NullPointerException("Employee not found");
        return employee;
    }

    @Transactional 
    public boolean existsbyID(int id){
        return employeeRepository.existsByPersonID((id));
    }

    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}

