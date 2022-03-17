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
    public Employee createEmployee() {
        Employee employee = new Employee();

        return employeeRepository.save(employee);
    }
    /**
   * @author Amy Qi Wang
   * @param String first_name, String last_name, String email,  String password, EmployeeStatus empStatus
   * Method to hire/create a new Employee profile using all attributes of an employee, not including address 
   */
    @Transactional
    public Employee createEmployee(String first_name, String last_name, String email,  String password, EmployeeStatus empStatus ){
        
        if (first_name == null || last_name == null || first_name.length() == 0 || last_name.length() == 0) throw new IllegalArgumentException("Please enter a valid name");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Please enter a valid email");
        if (password == null || password.length() < 8) throw new IllegalArgumentException("Please enter a password that is at least 8 characters long");
        if (empStatus == null) throw new IllegalArgumentException("Must input employee status to create employee account");
        if (employeeRepository.findEmployeeByEmail(email) != null || customerRepository.findCustomerByEmail(email) !=null ) throw new IllegalArgumentException("Email in use, please choose another");
        Employee employee = new Employee ();
        employee.setFirstName(first_name);
        employee.setLastName(last_name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setEmpStatus(empStatus);
        employeeRepository.save(employee);
        return employee;
    }

     /**
   * @author Amy Qi Wang
   * @param String first_name, String last_name, String email,  String password, EmployeeStatus empStatus, Address address
   * Method to hire/create a new Employee profile using all attributes of an employee including address
   */

    @Transactional
    public Employee createEmployee(String first_name, String last_name, String email,  String password, EmployeeStatus empStatus, Address address ){
        
        if (first_name == null || last_name == null || first_name.length() == 0 || last_name.length() == 0) throw new IllegalArgumentException("Please enter a valid name");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Please enter a valid email");
        if (password == null || password.length() < 8) throw new IllegalArgumentException("Please enter a password that is at least 8 characters long");
        if (empStatus == null) throw new IllegalArgumentException("Must input employee status to create employee account");
        if (address == null) throw new IllegalArgumentException("Must input a valid address");
        if (employeeRepository.findEmployeeByEmail(email) != null|| customerRepository.findCustomerByEmail(email) !=null) throw new IllegalArgumentException("Email in use, please choose another");
        Employee employee = new Employee ();
        employee.setFirstName(first_name);
        employee.setLastName(last_name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setEmpStatus(empStatus);
        employee.setAddress(address);
        employeeRepository.save(employee);
        return employee;
    }

/**
   * @author Amy Qi Wang
   * @param 
   * Method to get a list of all employees
   */
    @Transactional
    public List<Employee> getAllEmployees(){

        return toList(employeeRepository.findAll());

    }

    /**
   * @author Amy Qi Wang
   * @param int personID
   * Method to delete an employee using their person ID 
   */
    @Transactional
    public boolean deleteEmployee(int personID){
        Employee e=employeeRepository.findEmployeeByPersonID(personID);
        if(e == null) throw new NullPointerException("Employee not found");
        employeeRepository.delete(e); 
        return true;
    }

     /**
   * @author Amy Qi Wang
   * @param 
   * Delete all employees
   */

    @Transactional
    public boolean deleteEmployees() {
        employeeRepository.deleteAll();

        return true;
    }
 /**
   * @author Amy Qi Wang
   * @param String email, String password
   * Logs into employee account
   */

    @Transactional
    public boolean login (String email, String password){
        if (email==null) throw new IllegalArgumentException("Please enter a valid email");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee==null) throw new NullPointerException("Employee not found");
        if (!employee.getPassword().equals(password)) throw new IllegalArgumentException("Wrong password");
        employee.setLoginStatus(true);
        employeeRepository.save(employee);
        return employee.getLoginStatus();

    }

     /**
   * @author Amy Qi Wang
   * @param String email
   * Logs out of employee account
   */

    @Transactional
    public boolean logout (String email){
        if (email==null) throw new IllegalArgumentException("Please enter a valid email");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee==null) throw new NullPointerException("Employee not found");
        employee.setLoginStatus(false);
        employeeRepository.save(employee);
        return (employee.getLoginStatus()==false);
    }

     /**
   * @author Amy Qi Wang
   * @param String email, String oldPassword, String newPassword
   * Updates employee password by email
   */

    @Transactional
    public Employee updatePasswordByEmail(String email, String oldPassword, String newPassword){
        if (email == null) throw new IllegalArgumentException("Must enter email account");
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) throw new NullPointerException("Employee does not exist");
        if (!employee.getPassword().equals(oldPassword)) throw new IllegalArgumentException("Wrong password. Input correct old password");
        if (newPassword.length() >= 8) employee.setPassword(newPassword);
        employeeRepository.save(employee);
        return employee;
    }

     /**
   * @author Amy Qi Wang
   * @param int personID, String password
   * Updates employee password by personID
   */

    @Transactional
    public Employee updateEmployeePasswordById(int personID, String password) {
        if(password == null || password.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid password");

        Employee employee = this.employeeRepository.findEmployeeByPersonID(personID);
        if(employee == null) throw new NullPointerException("Employee not found");

        employee.setPassword(password);
        employeeRepository.save(employee);

        return employee;
    }

 /**
   * @author Amy Qi Wang
   * @param String email, String password
   * Updates employee address by email
   */

    @Transactional
    public Employee updateEmployeeAddressByEmail(String email, int addressId){
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) throw new NullPointerException ("Must be valid employee");
        employee.setAddress(addressRepository.findAddressByAddressID(addressId));
        employeeRepository.save(employee);
        return employee;
    }

     /**
   * @author Amy Qi Wang
   * @param int personID, int addressID
   * Updates employee address by ID
   */

    @Transactional
    public Employee updateEmployeeAddressByID(int personID, int addressID) {
        Employee employee = employeeRepository.findEmployeeByPersonID(personID);
        if(employee == null) throw new NullPointerException("Employee not found");

        Address address = addressRepository.findAddressByAddressID(addressID);
        if(address == null) throw new NullPointerException("Address not found");

        employee.setAddress(address);
        employeeRepository.save(employee);

        return employee;
    }
/**
 * @author Amy Qi Wang 
 * @param String email 
 * finds employees by email
 */
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
    public List<Employee> findEmployeeByFirstName(String first_name) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        List<Employee> employees = this.employeeRepository.findEmployeeByFirstName(first_name);

        if(employees.size() == 0) throw new NullPointerException("No employees found");
        return employees;
    }

    @Transactional
    public List<Employee> findEmployeeByLastName(String last_name) {
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");
        List<Employee> employees = this.employeeRepository.findEmployeeByLastName(last_name);

        if(employees.size() == 0) throw new NullPointerException("No employees found");
        return employees;
    }

    @Transactional
    public List<Employee> findEmployeeByName(String first_name, String last_name) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");
        List<Employee> employees = this.employeeRepository.findEmployeeByFirstNameAndLastName(first_name, last_name);

        if(employees.size() == 0) throw new NullPointerException("No employees found");
        return employees;
    }

    @Transactional
    public boolean isEmployeeByFirstName(String first_name) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");

        return employeeRepository.existsByFirstName(first_name);
    }

    @Transactional
    public boolean isEmployeeByLastName(String last_name) {
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");

        return employeeRepository.existsByLastName(last_name);
    }

    @Transactional
    public boolean isEmployeeByFirstAndLastName(String first_name, String last_name) {
        if(first_name == null || first_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid First Name");
        if(last_name == null || last_name.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Last Name");

        return employeeRepository.existsByFirstNameAndLastName(first_name, last_name);
    }

    @Transactional 
    public boolean existsbyID(int id){
        return employeeRepository.existsByPersonID((id));
    }

    @Transactional
    public boolean isEmployeeByEmail(String email) {
        if(email == null || email.length() == 0) throw new IllegalArgumentException("Please provide valid arguments: Invalid Email");

        return employeeRepository.existsByEmail(email);
    }

   @Transactional
   public boolean fireEmployeeById(int id){
       Employee e=employeeRepository.findEmployeeByPersonID(id);
       e.setEmpStatus(EmployeeStatus.fired);
       employeeRepository.save(e);
       return e.getEmpStatus()==EmployeeStatus.fired ;
   }

   @Transactional
   public boolean resignEmployee(int id){
    Employee e=employeeRepository.findEmployeeByPersonID(id);
       e.setEmpStatus(EmployeeStatus.resigned);
       employeeRepository.save(e);
       return e.getEmpStatus()==EmployeeStatus.resigned;
   }

   @Transactional
   public boolean rehireEmployee (int id){
        Employee e=employeeRepository.findEmployeeByPersonID(id);
        if (e.getEmpStatus()==EmployeeStatus.resigned || e.getEmpStatus()==EmployeeStatus.fired ){
            e.setEmpStatus(EmployeeStatus.hired);
            employeeRepository.save(e);
        }
        return e.getEmpStatus()==EmployeeStatus.hired ;
   }

   @Transactional 
   public boolean isHired(int id){
       return employeeRepository.findEmployeeByPersonID(id).getEmpStatus()==EmployeeStatus.hired;
   }

    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}

