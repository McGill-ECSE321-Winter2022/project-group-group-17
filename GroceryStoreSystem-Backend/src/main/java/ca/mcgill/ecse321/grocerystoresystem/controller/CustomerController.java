package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.CustomerDto;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Customer;
import ca.mcgill.ecse321.grocerystoresystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
  
  @Autowired
  private CustomerService customerService;
  
  @GetMapping(value = {"/customer/get/firstname/", "/customer/get/firstname"})
  public List<CustomerDto> getCustomerWithFirstName(@RequestParam String firstname) {
      try {
          return customerService.getCustomerByFirstName(firstname).stream().map(this::convertToDto).collect(Collectors.toList());
      }
      catch (NullPointerException exp) {
          return null;
      }
  }

  @GetMapping(value = {"/customer/get/lastname/", "/customer/get/lastname"})
  public List<CustomerDto> getCustomerWithLastName(@RequestParam String lastname) {
      try {
          return customerService.getCustomerByLastName(lastname).stream().map(this::convertToDto).collect(Collectors.toList());
      }
      catch (NullPointerException exp) {
          return null;
      }
  }
  
  @GetMapping(value = { "/customers/get/fullname", "/customers/get/fullname/" })
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
  public List<CustomerDto> getAllCustomers() {
    return customerService.getAllCustomers().stream().map(this::convertToDto).collect(Collectors.toList());
}
  
  @GetMapping(value = { "/customer/get/id", "/customer/get/id/" })
  public CustomerDto getCustomerByID(@RequestParam int id) {
    try {
      return convertToDto(customerService.getCustomer(id));
  }
  catch (NullPointerException exp) {
      return null;
  }
}
  
  @GetMapping(value = { "/customer/get/email", "customer/get/email/" })
  public CustomerDto getCustomerByEmail(@RequestParam String email) {
    try {
      return convertToDto(customerService.getCustomerByEmail(email));
  }
  catch (NullPointerException exp) {
      return null;
  }
}
  
  @PostMapping(value = { "/customer/create", "/customer/create/"})
  public CustomerDto createCustomer(@RequestParam int personID, @RequestParam String firstname, @RequestParam String lastname, 
      @RequestParam String email, @RequestParam String password, @RequestParam Address address) {
    Customer c = this.customerService.createCustomer(personID, firstname, lastname, email, password, address);
    return convertToDto(c);
  }
  
  @PostMapping(value = {"/customer/login", "/customer/login/"})
  public boolean login(@RequestParam String password, @RequestParam String email) {
    try {
      return (this.customerService.login(email, password) != null);
  }
  catch(NullPointerException | IllegalArgumentException exp) {
      System.out.println(exp.getMessage());
      return false;
  }
}
  
  @PostMapping(value = { "/customer/logout", "/customer/logout/id"})
  public boolean logout(@RequestParam int id, @RequestParam String email) {
    try {
      return (this.customerService.logout(email,id) != null);
  }
  catch(NullPointerException | IllegalArgumentException exp) {
      System.out.println(exp.getMessage());
      return false;
  }
}
  
  @DeleteMapping(value={"/customer/delete/", "/customer/delete"})
  public boolean deleteCustomerByID(@RequestParam int id) {
      try {
          return customerService.deleteCustomerByID(id);
      }
      catch (NullPointerException exp) {
          return false;
      }
  }

  @DeleteMapping(value = { "/customers/delete", "/customers/delete/" })
  public boolean deleteCustomers(){
      return customerService.deleteCustomers();
  }
  
  @GetMapping(value = {"/customers/local", "/customers/local/"})
  public List<CustomerDto> getLocalCustomers() {
      List<CustomerDto> customerDtoList = new ArrayList<>();
      List<Customer> customerList;
      try {
          customerList = customerService.getLocalCustomers();
      } catch (IllegalArgumentException exception) {
          return null;
      }
      for (Customer c : customerList) {
          customerDtoList.add(convertToDto(c));
      }
      return customerDtoList;
  }
  
  @GetMapping(value = {"/customers/nonlocal", "/customers/nonlocal/"})
  public List<CustomerDto> getNonLocalCustomers() {
      List<CustomerDto> customerDtoList = new ArrayList<>();
      List<Customer> customerList;
      try {
        customerList = customerService.getNonLocalCustomers();
    } catch (IllegalArgumentException exception) {
        return null;
    }
    for (Customer c : customerList) {
        customerDtoList.add(convertToDto(c));
    }
    return customerDtoList;
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
  
  @PostMapping(value={"/customer/update/address/", "/customer/update/address"})
  public CustomerDto updateCustomerAddress(@RequestParam int id, @RequestParam int addressID) {
      try {
          return convertToDto(customerService.updateCustomerAddressById(id, addressID));
      }
      catch(NullPointerException exp) {
          return null;
      }
  }
  
  @PutMapping(value = { "/customer/update/id", "/customer/update/id/" })
  public CustomerDto updateInfo(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password,
                                @RequestParam Address address){
      Customer c;
      try {
          return convertToDto(customerService.updateProfile(firstName, lastName, email, password, address, id));
      } catch (NullPointerException | IllegalArgumentException exception) {
          return null;
      }
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
