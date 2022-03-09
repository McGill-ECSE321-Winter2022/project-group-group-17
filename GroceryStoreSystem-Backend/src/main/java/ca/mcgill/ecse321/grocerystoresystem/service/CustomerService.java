package ca.mcgill.ecse321.grocerystoresystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystoresystem.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.AddressRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.*;

@Service
public class CustomerService {
  
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private AddressRepository addressRepository;
  
  /*
   * Create a new customer profile
   */
  @Transactional
  public Customer createCustomer(int personID, String firstName, String lastName, String email, String password, String city, String country, String postalCode, 
      String streetName, String streetNum, Boolean isLocal) {
    if(firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty() || email == null || email.isEmpty() || email.contains("@") == false) {
      throw new IllegalArgumentException("Please enter a valid name and email");
    }
    
    if(password.length() < 8 || password == null) {
      throw new IllegalArgumentException("Please enter a password with at least 8 characters");
    }
    
    if (city.isEmpty() || country.isEmpty() || postalCode.isEmpty() || streetName.isEmpty() || streetNum.isEmpty() || city==null || 
        country == null || postalCode == null || streetName == null || streetNum == null || isLocal == null) {  
      throw new IllegalArgumentException("Please enter a valid address");
    }
    
    if(customerRepository.existsByPersonID(personID)) {
      throw new IllegalArgumentException("Customer already exists!");
    }
    
    Address newAddress = new Address(streetName, streetNum, city, postalCode, country, isLocal);
    
    Customer newOnlineCustomer = new Customer(firstName, lastName, email, password, newAddress, false);
    customerRepository.save(newOnlineCustomer);
    
    return newOnlineCustomer;
    
  }
  
  /*
   * Customer login using email, password, personID
   */
  @Transactional
  public Customer login(String email, String password, int personID) {
    if (email == null || email.contains("@") == false) {
      throw new IllegalArgumentException("Please enter a valid email");
    }
    
    Customer customer = customerRepository.findCustomerByPersonID(personID);
    
    if (customer.getEmail().equals(email) == false) {
      throw new IllegalArgumentException("Incorrect email inputted!");
    }
    
    if (customer.getPassword().equals(password) == false) {
      throw new IllegalArgumentException("Incorrect password inputted!");
    }
    
    customer.setLoginStatus(true);
    customerRepository.save(customer);
    return customer;
  }  
  
  
  /*
   * Customer logout using personID
   */
  @Transactional
  public boolean logout(String email, int personID) {
    if (email == null || email.contains("@") == false) {
      throw new IllegalArgumentException("Please enter a valid email");
    }
    
    Customer customer = customerRepository.findCustomerByPersonID(personID);
    
    if (customer.getEmail().equals(email) == false) {
      throw new IllegalArgumentException("Incorrect email inputted!");
    }
    
    if (customer.getLoginStatus() == false) {
      throw new IllegalArgumentException("Account is currently not logged in!");
    }
    
    customer.setLoginStatus(false);
    customerRepository.save(customer);
    return true;
  }
  
  /*
   * Get customer with a personID
   */
  @Transactional
  public Customer getCustomer(int personID) {
    
    if (personID <= 0) {
      throw new IllegalArgumentException("Please enter a valid personID");
    }
    
    Customer customer = customerRepository.findCustomerByPersonID(personID);
    
    if(customer == null) {
      throw new IllegalArgumentException("Cannot find customer with specified ID");
    }
    
    return customer;
  }
  
  /*
   * Get customer with specified email
   */
  @Transactional
  public Customer getCustomerByEmail(String email) {
    
    if (!email.contains("@")) {
      throw new IllegalArgumentException("Please enter a valid email");
    }
    
    Customer customer = customerRepository.findCustomerByEmail(email);
    
    if(customer == null) {
      throw new IllegalArgumentException("Cannot find customer with specified email");
    }
    
    return customer;
  }
  
  /*
   * Get customers with specified first name
   */
  @Transactional
  public List<Customer> getCustomerByFirstName(String firstName) {
    
    if (firstName == null) {
      throw new IllegalArgumentException("Please enter a valid first name");
    }
    
    List<Customer> customers = customerRepository.findCustomersByFirstName(firstName);
    
    if(customers.isEmpty()) {
      throw new IllegalArgumentException("Cannot find customers with specified first name");
    }
    
    return customers;
  }
  
  /*
   * Get customers with specified last name
   */
  @Transactional
  public List<Customer> getCustomerByLastName(String lastName) {
    
    if (lastName == null) {
      throw new IllegalArgumentException("Please enter a valid last name");
    }
    
    List<Customer> customers = customerRepository.findCustomersByLastName(lastName);
    
    if(customers.isEmpty()) {
      throw new IllegalArgumentException("Cannot find customers with specified last name");
    }
    
    return customers;
  }
  
  /*
   * Get customers with specified first and last name
   */
  @Transactional
  public List<Customer> getCustomerByFullName(String firstName, String lastName) {
    
    if (firstName == null) {
      throw new IllegalArgumentException("Please enter a valid first name");
    }
    
    if (lastName == null) {
      throw new IllegalArgumentException("Please enter a valid last name");
    }
   
    List<Customer> customers = customerRepository.findCustomersByFirstNameAndLastName(firstName, lastName);
    
    if(customers.isEmpty()) {
      throw new IllegalArgumentException("Cannot find customers with specified first name");
    }
    
    return customers;
  }
  
  /*
   * Delete customer given personID
   */
  @Transactional
  public boolean deleteCustomerByID(int personID) {
    if (personID <= 0) {
      throw new IllegalArgumentException("Please enter a valid personID");
    }
    
    Customer c = customerRepository.findCustomerByPersonID(personID);
    
    if(c == null) {
      throw new NullPointerException("Cannot find Customer with this personID");
    }
    
    customerRepository.delete(c);
    addressRepository.delete(c.getAddress());
    
    return true;
  }
  
  /*
   * Checks if person is Customer by personID
   */
  @Transactional
  public boolean isCustomerByID(int personID) {
      return customerRepository.existsByPersonID(personID);
  }

  /*
   * Checks if person is Customer by first name
   */
  @Transactional
  public boolean isCustomerByFirstName(String firstName) {
      if(firstName == null || firstName.length() == 0) {
        throw new IllegalArgumentException("Please provide a valid first Name!");
      }
      return customerRepository.existsByFirstName(firstName);
  }

  /*
   * Checks if person is Customer by last name
   */
  @Transactional
  public boolean isCustomerByLastName(String lastName) {
      if(lastName == null || lastName.length() == 0) {
        throw new IllegalArgumentException("Please provide a valid last Name!");
      }
      return customerRepository.existsByFirstName(lastName);
  }

  /*
   * Checks if person is Customer by first and last name
   */
  @Transactional
  public boolean isCustomerByFirstAndLastName(String firstName, String lastName) {
      if(firstName == null || firstName.length() == 0) {
        throw new IllegalArgumentException("Please provide a valid first Name!");
      }
      if(lastName == null || lastName.length() == 0) {
        throw new IllegalArgumentException("Please provide a valid last Name!");
      }

      return customerRepository.existsByFirstNameAndLastName(firstName, lastName);
  }

  /*
   * Checks if person is Customer by email
   */
  @Transactional
  public boolean isCustomerByEmail(String email) {
      if(email == null || email.length() == 0 || !(email.contains("@"))) {
        throw new IllegalArgumentException("Please provide a valid email!");
      }
      return customerRepository.existsByEmail(email);
  }
  
  /*
   * Get all customers in grocery store system
   */
  @Transactional
  public List<Customer> getAllCustomers() {
      List<Customer> customerList = new ArrayList<>();
      for (Customer c : customerRepository.findAll()) {
        customerList.add(c);
      }
      return customerList;
  }
  
  /*
   * Get all customers who are local
   */
  @Transactional
  public List<Customer> getLocalCustomers(){
    List<Customer> localCustomers = new ArrayList<>();
    for(Customer c : customerRepository.findAll()) {
      if (c.getAddress().isLocal()) {
        localCustomers.add(c);
      }
    }
    return localCustomers; 
  }
  
  /*
   * Get all customers who are not local
   */
  @Transactional
  public List<Customer> getNonLocalCustomers(){
    List<Customer> foreignCustomers = new ArrayList<>();
    for(Customer c : customerRepository.findAll()) {
      if (!c.getAddress().isLocal()) {
        foreignCustomers.add(c);
      }
    }
    return foreignCustomers; 
  }
  
  /*
   * Get all logged in customers
   */
  @Transactional
  public List<Customer> getLoggedInCustomers(){
    List<Customer> loggedInUsers = new ArrayList<>();
    for(Customer c : customerRepository.findAll()) {
      if(c.getLoginStatus() == true) {
        loggedInUsers.add(c);
      }
    }
    
    return loggedInUsers;
  }
  
  /*
   * Update a customer's profile
   */
  @Transactional
  public Customer updateProfile(String firstName, String lastName, String email, String password, String city, String country, String postalCode, 
      String streetName, String streetNum, Boolean isLocal, int personID) {
    if(firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty() || email == null || email.isEmpty() || email.contains("@") == false) {
      throw new IllegalArgumentException("Please enter a valid name and email");
    }
    
    if(password.length() < 8 || password == null) {
      throw new IllegalArgumentException("Please enter a password with at least 8 characters");
    }
    
    if (city.isEmpty() || country.isEmpty() || postalCode.isEmpty() || streetName.isEmpty() || streetNum.isEmpty() || city==null || 
        country == null || postalCode == null || streetName == null || streetNum == null || isLocal == null) {  
      throw new IllegalArgumentException("Please enter a valid address");
    }
    
    Customer c = customerRepository.findCustomerByPersonID(personID);
    
    if(c != null && (c instanceof Customer)) {
      
      c.getAddress().setCity(city);
      c.getAddress().setCountry(country);
      c.getAddress().setPostalCode(postalCode);
      c.getAddress().setLocal(isLocal);
      c.getAddress().setStreetName(streetName);
      c.getAddress().setStreetNum(streetNum);
      
      c.setEmail(email);
      c.setFirstName(firstName);
      c.setLastName(lastName);
      c.setPassword(password);
      
      addressRepository.save(c.getAddress());
      customerRepository.save(c);
      return c;
    }
    else {
      throw new IllegalArgumentException("Cannot find customer with given personID");
    }
  }
  
  /*
   * update a customer's password
   */
  @Transactional
  public Customer updateCustomerPasswordById(int personID, String password) {
    
      if(password.length() < 8 || password == null) {
        throw new IllegalArgumentException("Please enter a password with at least 8 characters");
      }

      Customer customer = customerRepository.findCustomerByPersonID(personID);
      if(customer == null) {
        throw new NullPointerException("Customer not found");
      }
      customer.setPassword(password);
      customerRepository.save(customer);
      return customer;
  }
}
