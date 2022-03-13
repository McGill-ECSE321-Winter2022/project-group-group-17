package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.EmployeeDto;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;
import ca.mcgill.ecse321.grocerystoresystem.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

    @Autowired 
    private EmployeeService employeeService;

    @PutMapping(value = {"/employee/login/{id}", "/employee/login/{id}/"})
    public ResponseEntity login(@PathVariable("id") int personID, @RequestParam String password, @RequestParam String email) {
        Customer c;
        try {
            c = employeeService.login(email, password);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (c == null) {
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error logging in!");
        }
        return new ResponseEntity<>(convertToDto(c), HttpStatus.OK);
    }
    
    @PutMapping(value = { "/employee/logout/{id}", "/employee/logout/{id}"})
    public ResponseEntity logout(@PathVariable("id") int personID, @RequestParam String email) {
        boolean logout;
        try {
            logout = employeeService.logout(email);
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

    @GetMapping(value = {"/employees/", "/employees"})
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value={"/employee/get/id/", "/employee/get/id"})
    public EmployeeDto getEmployeeWithId (@RequestParam int id){
        try {
            EmployeeDto e = convertToDto(employeeService.getEmployee(id));
            return e;
        }
        catch (NullPointerException n) {
            return null;
        }

    }

    @GetMapping(value={"/employee/get/email/", "/employee/get/email"})
    public EmployeeDto getEmployeeWithEmail (@RequestParam String email){
        try {
            return convertToDto(employeeService.findEmployeeByEmail(email));
        }
        catch(NullPointerException n){
            return null;
        }
    }

    @GetMapping(value = {"/employee/get/firstname/", "/employee/get/firstname"})
    public List<EmployeeDto> getEmployeeWithFirstName(@RequestParam String firstname) {
        try {
            return employeeService.findEmployeeByFirstName(firstname).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch (NullPointerException exp) {
            return null;
        }

    }

    @GetMapping(value = {"/employee/get/lastname/", "/employee/get/lastname"})
    public List<EmployeeDto> getEmployeeWithLastName(@RequestParam String lastname) {
        try {
            return employeeService.findEmployeeByLastName(lastname).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/employee/get/fullname/", "/employee/get/fullname"})
    public List<EmployeeDto> getEmployeeWithName(@RequestParam String firstname, @RequestParam String lastname) {
        try {
            return employeeService.findEmployeeByName(firstname, lastname).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @PostMapping(value = {"/employee/create/", "/employee/create"})
    public EmployeeDto createEmployee (@RequestParam String firstname, @RequestParam String lastname, 
                                        @RequestParam String email, @RequestParam String password, @RequestParam EmployeeStatus empStatus){
        EmployeeDto employee = convertToDto(employeeService.createEmployee(firstname, lastname, email, password, empStatus));
        return employee; 
     }

    @DeleteMapping(value = {"/employee/delete/", "/employee/delete"})
    public boolean deleteEmployeeByID(@RequestParam int id){

        try {
            return employeeService.deleteEmployee(id);
        }
        catch (NullPointerException n){
            return false;
        }
     }

     @DeleteMapping(value={"/employees/delete/", "/employees/delete"})
     public boolean deleteEmployees() {
         employeeService.deleteEmployees();
 
         return true;
     }


     @PostMapping(value={"/employee/update/address/", "/employee/update/address"})
     public EmployeeDto updateAddressByID(@RequestParam int id, @RequestParam int addressID) {
         try {
             return convertToDto(employeeService.updateEmployeeAddressByID(id, addressID));
         }
         catch(NullPointerException exp) {
             return null;
         }
     }
 
     @PostMapping(value = {"/employee/update/password", "/employee/update/password/"})
     public EmployeeDto updatePasswordByID(@RequestParam int id, @RequestParam String password) {
         try {
             return convertToDto(employeeService.updateEmployeePasswordById(id, password));
         }
         catch(NullPointerException exp) {
             return null;
         }
     }

    @PostMapping (value = {"/employee/update/address/", "/employee/update/address"})
    public EmployeeDto updateAddressByEmail (@RequestParam String email, @RequestParam Address newAddress){
        try {
            return convertToDto(employeeService.updateEmployeeAddressByEmail(email, newAddress));
        }
        catch (NullPointerException n){
            return null;
        }
    }

    @PostMapping (value = {"/employee/update/password/", "/employee/update/password"})
    public EmployeeDto updatePasswordByEmail (@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword){
        try {
            return convertToDto(employeeService.updatePasswordByEmail(email, oldPassword, newPassword));
        }
        catch (NullPointerException n){
            return null;
        }
    }


    @GetMapping(value = {"/employee/check/id/", "/employee/check/id"})
    public boolean isEmployeeWithID(@RequestParam int id) {
        return employeeService.existsbyID(id);
    }

    @GetMapping(value = {"/employee/check/firstname/", "/employee/check/firstname"})
    public boolean isEmployeeWithFirstName(@RequestParam String firstname) {
        return employeeService.isEmployeeByFirstName(firstname);
    }

    @GetMapping(value = {"/employee/check/lastname/", "/employee/check/lastname"})
    public boolean isEmployeeWithLastName(@RequestParam String lastname) {
        return employeeService.isEmployeeByLastName(lastname);
    }

    @GetMapping(value = {"/employee/check/email/", "/employee/check/email"})
    public boolean isEmployeeWithEmail(@RequestParam String email) {
        return employeeService.isEmployeeByEmail(email);
    }

    @GetMapping(value = {"/employee/check/fullname/", "/employee/check/fullname"})
    public boolean isEmployeeWithFirstNameAndLastName(@RequestParam String firstname, @RequestParam String lastname) {
        return employeeService.isEmployeeByFirstAndLastName(firstname, lastname);
    }

    private EmployeeDto convertToDto(Employee employee) throws NullPointerException{
        if(employee == null) {
            throw new NullPointerException("Employee is null");
        }

        if(employee.getAddress() != null) {
            return new EmployeeDto(employee.getPersonID(), employee.getFirstName(), employee.getLastName(),  employee.getEmail(), employee.getEmpStatus(), convertToDto(employee.getAddress()));
        }

        return new EmployeeDto(employee.getPersonID(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getEmpStatus());
    }

    private AddressDto convertToDto(Address a) throws NullPointerException{
        if(a == null) {
            throw new NullPointerException("Address is null");
        }

        return new AddressDto(a.getAddressID(), a.isLocal(), a.getStreetName(), a.getStreetNum(), a.getCity(), a.getPostalCode(), a.getCountry());
    }




    
}
