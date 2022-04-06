package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.EmployeeDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.ItemQuantityDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.OrderDto;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import ca.mcgill.ecse321.grocerystoresystem.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = {"/employee/login"})
    public boolean login(@RequestParam String email, @RequestParam String password) {
        try {
            return this.employeeService.login(email, password);
        }
        catch(NullPointerException | IllegalArgumentException exp) {
            System.out.println(exp.getMessage());
            return false;
        }
    }

    @PostMapping(value = {"/employee/logout"})
    public boolean logout(@RequestParam String email) {
        try {
            return this.employeeService.logout(email);
        }
        catch(NullPointerException | IllegalArgumentException exp) {
            System.out.println(exp.getMessage());
            return false;
        }
    }

    @GetMapping(value = {"/employees/", "/employees"})
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value={"/employee/get/id/", "/employee/get/id"})
    public EmployeeDto getEmployeeWithId (@RequestParam int id){
        try {
            EmployeeDto e = convertToDto(employeeService.findEmployeeByID(id));
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

    @PostMapping(value = {"/employee/update/password/", "/employee/update/password"})
    public EmployeeDto updatePasswordByID(@RequestParam int id, @RequestParam String password) {
        try {
            return convertToDto(employeeService.updateEmployeePasswordById(id, password));
        }
        catch(NullPointerException exp) {
            return null;
        }
    }

    @PostMapping (value = {"/employee/resign/", "/employee/resign"})
    public boolean resignEmployeeDto(@RequestParam int id){

        try{
            return this.employeeService.findEmployeeByID(id).setEmpStatus(EmployeeStatus.resigned);
        }
        catch (NullPointerException | IllegalArgumentException x) {
            System.out.println(x.getMessage());
            return false;
        }
    }

    @PostMapping (value = {"/employee/fire/", "/employee/fire"})
    public boolean fireEmployeeDto(@RequestParam int id){

        try{
            return this.employeeService.findEmployeeByID(id).setEmpStatus(EmployeeStatus.fired);
        }
        catch (NullPointerException | IllegalArgumentException x) {
            System.out.println(x.getMessage());
            return false;
        }
    }

    @PostMapping (value = {"/employee/rehire/", "/employee/rehire"})
    public boolean rehireEmployeeDto(@RequestParam int id){

        try{
            return this.employeeService.findEmployeeByID(id).setEmpStatus(EmployeeStatus.hired);
        }
        catch (NullPointerException | IllegalArgumentException x) {
            System.out.println(x.getMessage());
            return false;
        }
    }

    @GetMapping (value={"/employee/get/employeestatus/", "/employee/update/employeestatus"})
    public boolean isHired(@RequestParam int id){
        EmployeeDto e = convertToDto(employeeService.findEmployeeByID(id));
        try {
            return e.getStatus()==EmployeeStatus.hired;
        }
        catch (NullPointerException|IllegalArgumentException x){
            System.out.println(x.getMessage());
            return false;
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

        if(employee.getAddress() != null && employee.getOrders() != null) {
            return new EmployeeDto(employee.getPersonID(), employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getEmpStatus(), convertToDto(employee.getAddress()), convertToOrderDto(employee.getOrders()));
        }

        else if (employee.getAddress() != null && employee.getOrders() == null){
            return new EmployeeDto(employee.getPersonID(), employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getEmpStatus(), convertToDto(employee.getAddress()), new ArrayList<>());

        }
        else if (employee.getAddress() == null && employee.getOrders() != null){
            return new EmployeeDto(employee.getPersonID(), employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getEmpStatus(), convertToDto(new Address()), convertToOrderDto(employee.getOrders()));
        }

        return new EmployeeDto(employee.getPersonID(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getEmpStatus());
    }

    private AddressDto convertToDto(Address a) throws NullPointerException{
        if(a == null) {
            throw new NullPointerException("Address is null");
        }

        return new AddressDto(a.getAddressID(), a.isLocal(), a.getStreetName(), a.getStreetNum(), a.getCity(), a.getPostalCode(), a.getCountry());
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
