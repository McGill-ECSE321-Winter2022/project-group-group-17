package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.CustomerDto;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Customer;
import ca.mcgill.ecse321.grocerystoresystem.service.CustomerService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
  
  @Autowired
  private CustomerService customerService;
  
  @GetMapping(value = { "/customers/fullname", "/customers/fullname/" })
  public ResponseEntity getCustomersByFirstAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
      List<CustomerDto> cDtoList = new ArrayList<>();
      List<Customer> customerList;
      try {
          customerList = customerService.getCustomerByFullName(firstName, lastName);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }

      for (Customer c : customerList) {
          cDtoList.add(convertToDto(c));
      }
      if (cDtoList.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customers with specified first and last names exists");
      }
      return new ResponseEntity<>(cDtoList, HttpStatus.OK);
  }
  
  @GetMapping(value = { "/customers", "/customers/" })
  public ResponseEntity getAllCustomers() {
    List<CustomerDto> customerDtoList = new ArrayList<>();
    List<Customer> customerList;
    try {
        customerList = customerService.getAllCustomers();
    } catch (IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    for (Customer c : customerList) {
        customerDtoList.add(convertToDto(c));
    }
    if (customerDtoList.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any Customers in System");
    }
    return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
}
  
  @GetMapping(value = { "/customer/{id}", "/customer/{id}/" })
  public ResponseEntity getCustomerByID(@PathVariable("id") int personID) {
      Customer customer;
      try {
          customer = customerService.getCustomer(personID);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      if (customer == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find Customer with given personID" + personID);
      }
      return new ResponseEntity<>(convertToDto(customer), HttpStatus.OK);
  }
  
  @GetMapping(value = { "/customer/email", "customer/email/" })
  public ResponseEntity getCustomerByEmail(@RequestParam String email) {
      Customer c;
      try {
          c = customerService.getCustomerByEmail(email);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      if (c == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find Customer with this email");
      }
      return new ResponseEntity<>(convertToDto(c), HttpStatus.OK);
  }
  
  @PostMapping(value = { "/customer/create", "/customer/create/"})
  public ResponseEntity createCustomer(@RequestParam int personID, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String password,
                                    @RequestParam String city, @RequestParam String country, @RequestParam String postalCode,
                                    @RequestParam String streetName, @RequestParam String streetNum, boolean isLocal) {
      Customer customer;
      try {
          customer = customerService.createCustomer(personID, firstname, lastname, email, password, city, country, postalCode, streetName, streetNum, isLocal);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      return new ResponseEntity<>(convertToDto(customer), HttpStatus.OK);
  }
  
  @PutMapping(value = {"/customer/login/{id}", "/customer/login/{id}/"})
  public ResponseEntity login(@PathVariable("id") int personID, @RequestParam String password, @RequestParam String email) {
      Customer c;
      try {
          c = customerService.login(email, password, personID);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      if (c == null) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error logging in!");
      }
      return new ResponseEntity<>(convertToDto(c), HttpStatus.OK);
  }
  
  @PutMapping(value = { "/customer/logout/{id}", "/customer/logout/{id}"})
  public ResponseEntity logout(@PathVariable("id") int personID, @RequestParam String email) {
      boolean logout;
      try {
          logout = customerService.logout(email, personID);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      if (logout == true) {
        return ResponseEntity.status(HttpStatus.OK).body("Successfully logged out");
      }
      else {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error logging out!");
      }
  }

  @DeleteMapping(value = { "/customer/{id}", "/customer/{id}/" })
  public ResponseEntity deleteCustomer(@PathVariable("id") int personID){
      boolean delete;
      try {
          delete = customerService.deleteCustomerByID(personID);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      if (delete == true && customerService.getCustomer(personID) == null) {
        return ResponseEntity.status(HttpStatus.OK).body("Customer with personID " + personID + " has been successfully deleted");
      }
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error deleting customer!");
  }
  
  @GetMapping(value = {"/customers/local", "/customers/local/"})
  public ResponseEntity getLocalCustomers() {
      List<CustomerDto> customerDtoList = new ArrayList<>();
      List<Customer> customerList;
      try {
          customerList = customerService.getLocalCustomers();
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      for (Customer c : customerList) {
          customerDtoList.add(convertToDto(c));
      }
      if (customerDtoList.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any local customers");
      }
      return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
  }
  
  @GetMapping(value = {"/customers/nonlocal", "/customers/nonlocal/"})
  public ResponseEntity getNonLocalCustomers() {
      List<CustomerDto> customerDtoList = new ArrayList<>();
      List<Customer> customerList;
      try {
          customerList = customerService.getNonLocalCustomers();
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      for (Customer c : customerList) {
          customerDtoList.add(convertToDto(c));
      }
      if (customerDtoList.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find any local customers");
      }
      return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
  }
  
  @GetMapping(value = {"/customer/check/id/", "/customer/check/id"})
  public boolean isCustomerWithID(@RequestParam int id) {
      return customerService.isCustomerByID(id);
  }

  @GetMapping(value = {"/customer/check/firstname/", "/customer/check/firstname"})
  public boolean isCustomerWithFirstName(@RequestParam String firstname) {
    return customerService.isCustomerByFirstName(firstname);
  }

  @GetMapping(value = {"/customer/check/lastname/", "/customer/check/lastname"})
  public boolean isCustomerWithLastName(@RequestParam String lastname) {
      return customerService.isCustomerByLastName(lastname);
  }

  @GetMapping(value = {"/customer/check/email/", "/customer/check/email"})
  public boolean isCustomerWithEmail(@RequestParam String email) {
      return customerService.isCustomerByEmail(email);
  }

  @GetMapping(value = {"/customer/check/fullname/", "/customer/check/fullname"})
  public boolean isCustomerWithFirstNameAndLastName(@RequestParam String firstname, @RequestParam String lastname) {
      return customerService.isCustomerByFirstAndLastName(firstname, lastname);
  }
  
  @PostMapping(value = {"/customer/update/password", "/customer/update/password/"})
  public CustomerDto updateCustomerPassword(@RequestParam int id, @RequestParam String password) {
      try {
        Customer c = customerService.updateCustomerPasswordById(id, password);  
        return convertToDto(c);
      }
      catch(NullPointerException exception) {
          return null;
      }
  }
  
  @PutMapping(value = { "/customer/update/{id}", "/customer/update/{id}/" })
  public ResponseEntity updateInfo(@PathVariable("id") int personID, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password,
                                @RequestParam String streetName,  @RequestParam String StreetNum, @RequestParam String city, @RequestParam String postalCode,
                                @RequestParam boolean isLocal){
      Customer c;
      try {
          c = customerService.updateProfile(firstName, lastName, email, password, city, city, postalCode, streetName, StreetNum, isLocal, personID);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      if (c == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot update this Customer profile");
      }
      return new ResponseEntity<>(convertToDto(c), HttpStatus.OK);
  }
  
  private CustomerDto convertToDto(Customer c) {
    if (c == null) {
      throw new NullPointerException("Cannot find this Customer");
    }
    
    return new CustomerDto(c.getPersonID(), c.getFirstName(),c.getLastName(), c.getEmail(), convertToDto(c.getAddress()), c.getLoginStatus());
    }
  
  private AddressDto convertToDto(Address address) {
    if (address == null) {
      throw new NullPointerException("Cannot find Address");
    }
    return new AddressDto(address.getAddressID(), address.isLocal(), address.getStreetName(), address.getCity(), address.getPostalCode(), address.getStreetNum(), address.getCountry());
  }
}
