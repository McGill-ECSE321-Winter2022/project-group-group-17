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
   * Validate person credentials via PersonID
   */
  @Transactional
  public boolean validateCustomerByID(int personID) {
      if (personID <= 0) {
        throw new IllegalArgumentException("Please enter a valid PersonID");
      }
      Customer customerExists = customerRepository.findCustomerByPersonID(personID);
      if (customerExists == null) {
        throw new NullPointerException("Cannot find Person with given ID");
      }
      if(!(customerRepository.findCustomerByPersonID(personID) instanceof Customer)) {
        throw new IllegalArgumentException("This person with given ID is not a customer!");
      }
      return true;
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
  
}
