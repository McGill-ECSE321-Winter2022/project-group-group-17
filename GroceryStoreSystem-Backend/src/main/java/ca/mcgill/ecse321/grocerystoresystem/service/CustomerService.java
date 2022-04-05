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
  
  
  /**
   * @author Yash Khapre
   * @param String firstName, String lastName, String email, String password, Address address
   * Method to create a customer given an address object as an input
   */
  @Transactional
  public Customer createCustomer(int personID, String firstName, String lastName, String email, String password, Address address) {
      if(firstName == null || firstName.length() == 0) {
        throw new IllegalArgumentException("Please provide valid First Name");
      }
      if(lastName == null || lastName.length() == 0) {
        throw new IllegalArgumentException("Please provide valid Last Name");
      }
      if(email == null || email.length() == 0) {
        throw new IllegalArgumentException("Please provide valid Email");
      }
      if(password.length() < 8 || password == null) {
        throw new IllegalArgumentException("Please provide valid Password");
      }
      if(address == null) {
        throw new IllegalArgumentException("Please provide valid Address");
      }
      if(customerRepository.existsByEmail(email) || customerRepository.existsByPersonID(personID)) {
        throw new IllegalArgumentException("Customer already exists!"); 
      }
      Customer customer = new Customer();
      customer.setFirstName(firstName);
      customer.setLastName(lastName);
      customer.setEmail(email);
      customer.setPassword(password);
      customer.setAddress(address);
      customerRepository.save(customer);
      return customer;
  }
  
  /**
   * @author Yash Khapre
   * @param String firstName, String lastName, String email, String password, Address address
   * Method to create a customer given everything but an address
   */
  @Transactional
  public Customer createCustomer(String firstName, String lastName, String email, String password) {
      if(firstName == null || firstName.length() == 0) {
        throw new IllegalArgumentException("Please provide valid First Name");
      }
      if(lastName == null || lastName.length() == 0) {
        throw new IllegalArgumentException("Please provide valid Last Name");
      }
      if(email == null || email.length() == 0 || !email.contains("a")) {
        throw new IllegalArgumentException("Please provide valid Email");
      }
      if(password.length() < 8 || password == null) {
        throw new IllegalArgumentException("Please provide valid Password");
      }
      Customer customer = new Customer();
      customer.setFirstName(firstName);
      customer.setLastName(lastName);
      customer.setEmail(email);
      customer.setPassword(password);
      customerRepository.save(customer);
      return customer;
  }
  
  /**
   * @author Yash Khapre
   * @Param String email, String password, int personID
   * Customer login method
   */
  @Transactional
  public Customer login(String email, String password) {
    if (email == null || email.contains("@") == false) {
      throw new IllegalArgumentException("Please enter a valid email");
    } 
    Customer customer = customerRepository.findCustomerByEmail(email);
    if (customer == null) {
      throw new IllegalArgumentException("Incorrect email inputted!");
    }
    if (customer.getPassword().equals(password) == false) {
      throw new IllegalArgumentException("Incorrect password inputted!");
    }
    customer.setLoginStatus(true);
    customerRepository.save(customer);
    return customer;
  }  
  
  /**
   * @author Yash Khapre
   * @param String email, int personID
   * Customer logout method
   */
  @Transactional
  public Customer logout(String email, int personID) {
    if (email == null || email.contains("@") == false) {
      throw new IllegalArgumentException("Please enter a valid email");
    }
    Customer customer = customerRepository.findCustomerByPersonID(personID);
    if (customer.getEmail().equals(email) == false) {
      throw new IllegalArgumentException("Incorrect email inputted!");
    }
    customer.setLoginStatus(false);
    
    return customerRepository.save(customer);
  }
  
  /**
   * @author Yash Khapre
   * @param int personID
   * Get a customer given a personID method
   */
  @Transactional
  public Customer getCustomer(int personID) {
    
    Customer customer = customerRepository.findCustomerByPersonID(personID);
    if(customer == null) {
      throw new IllegalArgumentException("Cannot find customer with specified ID");
    }
    return customer;
  }
  
  /**
   * @author Yash Khapre
   * @param String email
   * Get a customer given a specific email
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
  
  /**
   * @author Yash Khapre
   * @param String firstName
   * Method to get all customers with specified first name
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
  
  /**
   * @author Yash Khapre
   * @param String lastName
   * Method to get all customers with specified last name
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
  
  /**
   * @author Yash Khapre
   * @param String firstName, String lastName
   * Method to get all customers with specified first and last names
   */
  @Transactional
  public List<Customer> getCustomerByFullName(String firstName, String lastName) {    
    List<Customer> customers = null;
    if (firstName == null) {
      throw new IllegalArgumentException("Please enter a valid first name");
    }    
    if (lastName == null) {
      throw new IllegalArgumentException("Please enter a valid last name");
    }   
    customers = customerRepository.findCustomersByFirstNameAndLastName(firstName, lastName);    
    if(customers == null || customers.isEmpty()) {
      throw new IllegalArgumentException("Cannot find customers with specified full name");
    }    
    return customers;
  }
  
  /**
   * @author Yash Khapre
   * @param int personID
   * Delete a customer given a personID method
   */
  @Transactional
  public boolean deleteCustomerByID(int personID) {  
    Customer customer = customerRepository.findCustomerByPersonID(personID);    
    if(customer == null) throw new NullPointerException("Customer not found");

    customerRepository.delete(customer);

    return !this.isCustomerByID(customer.getPersonID());
  }
  
  /**
   * @author Yash Khapre
   * @param none
   * Delete all customers method
   */
  @Transactional
  public boolean deleteCustomers() {
      customerRepository.deleteAll();

      return this.getAllCustomers().size() == 0;
  }
  
  /**
   * @author Yash Khapre
   * @param int personID
   * Method that checks if a person is a Customer using personID
   */
  @Transactional
  public boolean isCustomerByID(int personID) {
      return customerRepository.existsByPersonID(personID);
  }

  /**
   * @author Yash Khapre
   * @param String firstName
   * Method that checks if person is Customer using first name
   */
  @Transactional
  public boolean isCustomerByFirstName(String firstName) {
      if(firstName == null || firstName.length() == 0) {
        throw new IllegalArgumentException("Please provide a valid first Name!");
      }
      return customerRepository.existsByFirstName(firstName);
  }

  /**
   * @author Yash Khapre
   * @param String lastName
   * Method that checks if a person is a Customer using specified last name
   */
  @Transactional
  public boolean isCustomerByLastName(String lastName) {
      if(lastName == null || lastName.length() == 0) {
        throw new IllegalArgumentException("Please provide a valid last Name!");
      }
      return customerRepository.existsByLastName(lastName);
  }

  /**
   * @author Yash Khapre
   * @param String firstName, String lastName
   * Method that checks if person is Customer by first and last name
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

  /**
   * @author Yash Khapre
   * @param String email
   * Checks if a person is a Customer using email
   */
  @Transactional
  public boolean isCustomerByEmail(String email) {
      if(email == null || email.length() == 0 || !(email.contains("@"))) {
        throw new IllegalArgumentException("Please provide a valid email!");
      }
      return customerRepository.existsByEmail(email);
  }
  
  /**
   * @author Yash Khapre
   * Method that gets all customers in grocery store system
   */
  @Transactional
  public List<Customer> getAllCustomers() {
      List<Customer> customerList = new ArrayList<>();
      for (Customer c : customerRepository.findAll()) {
        customerList.add(c);
      }
      return customerList;
  }
  
  /**
   * @author Yash Khapre
   * Method that gets all customers who are local
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
  
  /**
   * @author Yash Khapre
   * Method that gets all customers who are not local
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
  
  /**
   * @author Yash Khapre
   * Method that gets all logged-in customers
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
  
  /**
   * @author Yash Khapre
   * @param String firstName, String lastName, String email, Address a
   * Method that updates a specific customer's profile
   */
  @Transactional
  public Customer updateProfile(String firstName, String lastName, String email, String password, Address address, int personID) {
    if(firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty() || email == null || email.isEmpty() || email.contains("@") == false) {
      throw new IllegalArgumentException("Please enter a valid name and email");
    }   
    if(password.length() < 8 || password == null) {
      throw new IllegalArgumentException("Please enter a password with at least 8 characters");
    } 
    if (address.getCity().isEmpty() || address.getCountry().isEmpty() || address.getPostalCode().isEmpty() || address.getStreetName().isEmpty() || 
        address.getStreetNum().isEmpty() || address.getCity()==null || address.getCountry() == null || address.getPostalCode() == null ||
        address.getStreetName() == null || address.getStreetNum() == null) {  
      throw new IllegalArgumentException("Please enter a valid address");
    }

    Customer c = customerRepository.findCustomerByPersonID(personID);
    Address a = new Address();
    if(c != null && (c instanceof Customer)) {
      a.setCity(address.getCity());
      a.setCountry(address.getCountry());
      a.setPostalCode(address.getPostalCode());
      a.setLocal(address.isLocal());
      a.setStreetName(address.getStreetName());
      a.setStreetNum(address.getStreetNum());
      
      c.setEmail(email);
      c.setFirstName(firstName);
      c.setLastName(lastName);
      c.setPassword(password);
      c.setAddress(a);
      
      addressRepository.save(a);
      customerRepository.save(c);
      return c;
    }
    else {
      throw new IllegalArgumentException("Cannot find customer with given personID");
    }
  }
  
  /**
   * @author Yash Khapre
   * @param int personID, String password
   * Method to update a customer's password
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
  
  /**
   * @author Yash Khapre
   * @param int personID, int addressID
   * Method to update a customer's address
   */
  @Transactional
  public Customer updateCustomerAddressById(int personID, int addressID) {
      Customer customer = customerRepository.findCustomerByPersonID(personID);
      Address address = addressRepository.findAddressByAddressID(addressID);
      if(customer == null) {
        throw new NullPointerException("Customer not found");
      }
      if(address == null) {
        throw new NullPointerException("Address not found");
      }
      customer.setAddress(address);
      customerRepository.save(customer);
      return customer;
  }
}
