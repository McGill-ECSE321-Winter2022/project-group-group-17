package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.*;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import ca.mcgill.ecse321.grocerystoresystem.service.CustomerService;
import ca.mcgill.ecse321.grocerystoresystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private PersonService personService;

  @GetMapping(value = {"/customer/get/firstName/", "/customer/get/firstName"})
  public List<CustomerDto> getCustomerWithFirstName(@RequestParam String firstName) {
      try {
          return customerService.getCustomerByFirstName(firstName).stream().map(this::convertToDto).collect(Collectors.toList());
      }
      catch (NullPointerException exp) {
          return null;
      }
  }

  @GetMapping(value = {"/customer/get/lastName/", "/customer/get/lastName"})
  public List<CustomerDto> getCustomerWithLastName(@RequestParam String lastName) {
      try {
          return customerService.getCustomerByLastName(lastName).stream().map(this::convertToDto).collect(Collectors.toList());
      }
      catch (NullPointerException exp) {
          return null;
      }
  }
  
  @GetMapping(value = { "/customer/get/fullName", "/customer/get/fullName/" })
  public List<CustomerDto> getCustomersByFirstAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
    try {
      return customerService.getCustomerByFullName(firstName, lastName).stream().map(this::convertToDto).collect(Collectors.toList());
  }
  catch (NullPointerException exp) {
      return null;
  }
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
  
  @GetMapping(value = { "/customer/get/email", "/customer/get/email/" })
  public CustomerDto getCustomerByEmail(@RequestParam String email) {
    try {
      return convertToDto(customerService.getCustomerByEmail(email));
  }
  catch (NullPointerException exp) {
      return null;
  }
}


  @PostMapping(value = { "/customer/create", "/customer/create/"})
  public CustomerDto createCustomer( @RequestParam String firstName, @RequestParam String lastName, 
      @RequestParam String email, @RequestParam String password) {
    Customer c = this.customerService.createCustomer(firstName, lastName, email, password);
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

  @GetMapping(value = {"/customer/check/firstName/", "/customer/check/firstName"})
  public boolean isCustomerWithFirstName(@RequestParam String firstName) {
    return customerService.isCustomerByFirstName(firstName);
  }

  @GetMapping(value = {"/customer/check/lastName/", "/customer/check/lastName"})
  public boolean isCustomerWithLastName(@RequestParam String lastName) {
      return customerService.isCustomerByLastName(lastName);
  }

  @GetMapping(value = {"/customer/check/email/", "/customer/check/email"})
  public boolean isCustomerWithEmail(@RequestParam String email) {
      return customerService.isCustomerByEmail(email);
  }

  @GetMapping(value = {"/customer/check/fullName/", "/customer/check/fullName"})
  public boolean isCustomerWithFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
      return customerService.isCustomerByFirstAndLastName(firstName, lastName);
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
  
  @PostMapping(value = { "/customer/update/id", "/customer/update/id/" })
  public CustomerDto updateInfo(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password,
                                @RequestParam Address address){
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
    if (c.getAddress() != null && c.getOrders() != null) {
    return new CustomerDto(c.getPersonID(), c.getFirstName(),c.getLastName(), c.getEmail(), convertToDto(c.getAddress()), c.getLoginStatus(), convertToOrderDto(c.getOrders()));
    }
    else if (c.getAddress() == null && c.getOrders() != null) {
        return new CustomerDto(c.getPersonID(), c.getFirstName(),c.getLastName(), c.getEmail(), convertToDto(new Address()), c.getLoginStatus(), convertToOrderDto(c.getOrders()));
    }
    else if (c.getAddress() != null && c.getOrders() == null) {
        return new CustomerDto(c.getPersonID(), c.getFirstName(),c.getLastName(), c.getEmail(), convertToDto(new Address()), c.getLoginStatus(), new ArrayList<>());
    }
    else {
      return new CustomerDto(c.getPersonID(), c.getFirstName(),c.getLastName(), c.getEmail(), convertToDto(new Address()), c.getLoginStatus(), new ArrayList<>());
    }
    
    }
  
  private AddressDto convertToDto(Address address) {
    if (address == null) {
      throw new NullPointerException("Cannot find Address");
    }
    return new AddressDto(address.getAddressID(), address.isLocal(), address.getStreetName(), address.getCity(), address.getPostalCode(), address.getStreetNum(), address.getCountry());
  }

    private List<OrderDto> convertToOrderDto(List<Order> orders){
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders){
            OrderDto orderDto = convertToDto(order);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    private OrderDto convertToDto (Order order){
        if (order == null){
            throw new NullPointerException("Order is null");
        }

        if (order instanceof DeliveryOrder){
            return convertToDto((DeliveryOrder) order);
        }
        else if (order instanceof PickupOrder){
            return convertToDto((PickupOrder) order);
        }
        else{
            return convertToDto((InStoreOrder) order);
        }
    }

    private OrderDto convertToDto(DeliveryOrder deliveryOrder) {
        if(deliveryOrder == null) {
            throw new NullPointerException("Order is null");
        }

        if (deliveryOrder.getPortionNum() != null && deliveryOrder.getAddress() != null) {
            return new OrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(),
                    deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(),
                    deliveryOrder.getDeliveryTime(), convertToDto(deliveryOrder.getAddress()),
                    convertToDto(deliveryOrder.getPortionNum()));
        }

        else if(deliveryOrder.getPortionNum() != null && deliveryOrder.getAddress() == null){
            return new OrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(),
                    deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(),
                    deliveryOrder.getDeliveryTime(), convertToDto(new Address()),
                    convertToDto(deliveryOrder.getPortionNum()));
        }
        else if(deliveryOrder.getPortionNum() == null && deliveryOrder.getAddress() != null){
            return new OrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(),
                    deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(),
                    deliveryOrder.getDeliveryTime(), convertToDto(deliveryOrder.getAddress()),
                    new ArrayList<>());
        }
        else{
            return new OrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(),
                    deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(),
                    deliveryOrder.getDeliveryTime(), convertToDto(new Address()),
                    new ArrayList<>());

        }
    }

    private OrderDto convertToDto(PickupOrder pickupOrder) {
        if(pickupOrder == null) {
            throw new NullPointerException("Order is null");
        }

        if (pickupOrder.getPortionNum() != null) return new OrderDto(pickupOrder.getOrderID(), pickupOrder.getTotalCost(), pickupOrder.getOrderTimeStamp(),
                pickupOrder.isPaid(), pickupOrder.getPickupDate(), convertToDto(pickupOrder.getPortionNum()));

        else{

            return new OrderDto(pickupOrder.getOrderID(), pickupOrder.getTotalCost(), pickupOrder.getOrderTimeStamp(),
                    pickupOrder.isPaid(), pickupOrder.getPickupDate(),new ArrayList<>());
        }

    }

    private List<ItemQuantityDto> convertToDto(List<ItemQuantity> itemQuantities){
        List<ItemQuantityDto> itemQuantityDtos = new ArrayList<>();

        for (ItemQuantity itemQuantity : itemQuantities){
            ItemQuantityDto itemQuantityDto = convertToDto(itemQuantity);
            itemQuantityDtos.add(itemQuantityDto);
        }

        return itemQuantityDtos;
    }

    private OrderDto convertToDto(InStoreOrder inStoreOrder) {
        if(inStoreOrder == null) {
            throw new NullPointerException("Order is null");
        }

        if (inStoreOrder.getPortionNum() != null) return new OrderDto(inStoreOrder.getOrderID(), inStoreOrder.getTotalCost(), inStoreOrder.getOrderTimeStamp(),
                inStoreOrder.isPaid(), convertToDto(inStoreOrder.getPortionNum()));

        else{

            return new OrderDto(inStoreOrder.getOrderID(), inStoreOrder.getTotalCost(), inStoreOrder.getOrderTimeStamp(),
                    inStoreOrder.isPaid(), new ArrayList<>());
        }
    }

    private ItemQuantityDto convertToDto (ItemQuantity itemQuantity){
        if (itemQuantity == null){
            throw new NullPointerException("Item Quantity is null");
        }
        return new ItemQuantityDto(itemQuantity.getItemNum(), itemQuantity.getQuantityID());
    }
}
