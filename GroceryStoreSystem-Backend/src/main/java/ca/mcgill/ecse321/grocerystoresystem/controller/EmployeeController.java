package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.EmployeeDto;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;
import ca.mcgill.ecse321.grocerystoresystem.service.EmployeeService;

import org.checkerframework.checker.units.qual.radians;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

    @Autowired 
    private EmployeeService employeeService;

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

    @PostMapping (value = {"/employee/update/address/", "/employee/update/address"})
    public EmployeeDto updateAddress (@RequestParam String email, @RequestParam Address newAddress){
        try {
            return convertToDto(employeeService.updateAddress(email, newAddress));
        }
        catch (NullPointerException n){
            return null;
        }
    }

    @PostMapping (value = {"/employee/update/password/", "/employee/update/password"})
    public EmployeeDto updatePassword (@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword){
        try {
            return convertToDto(employeeService.updatePassword(email, oldPassword, newPassword));
        }
        catch (NullPointerException n){
            return null;
        }
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
