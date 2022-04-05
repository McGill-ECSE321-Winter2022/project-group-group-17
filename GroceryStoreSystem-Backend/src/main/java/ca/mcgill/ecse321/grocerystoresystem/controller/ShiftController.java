package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.*;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import ca.mcgill.ecse321.grocerystoresystem.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @GetMapping(value = {"/shifts/", "/shifts"})
    public List<ShiftDto> getAllShifts() {
        return shiftService.getAllShifts().stream().map(this::convertToDto).collect(Collectors.toList());
    }


    @GetMapping(value = {"/shift/get/id", "/shift/get/id/"})
    public ShiftDto getShiftById(@RequestParam int id) {
        try {
            return convertToDto(shiftService.findShiftByID(id));
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/shift/get/date", "/shift/get/date/"})
    public List<ShiftDto> getShiftByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date) {
        try {
            return shiftService.findShiftByDate(date).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/shift/get/status", "/shift/get/status/"})
    public List<ShiftDto> getShiftByStatus(@RequestParam ShiftStatus status) {
        try {
            return shiftService.findShiftByStatus(status).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch (NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/shift/get/employee", "/shift/get/employee/"})
    public List<ShiftDto> getShiftByEmployee(@RequestParam int id) {
        try {
            return shiftService.findShiftByEmployee(id).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch(NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/shift/get/datetime", "/shift/get/datetime/"})
    public List<ShiftDto> getShiftByDateAndStartTimeAndEndTime(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date,
            @RequestParam("starttime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime starttime,
            @RequestParam("endtime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime endtime) {
        try {
            return shiftService.findShiftsByDateAndStartTimeAndEndTime(date, starttime, endtime).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch(NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/shift/get/empdatetime", "/shift/get/empdatetime/"})
    public List<ShiftDto> getShiftByEmployeeAndDateAndStartTimeAndEndTime(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date,
            @RequestParam("starttime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime starttime,
            @RequestParam("endtime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime endtime,
            @RequestParam int id) {
        try {
            return shiftService.findShiftsByEmployeeAndDateAndStartTimeAndEndTime(id, date, starttime, endtime).stream().map(this::convertToDto).collect(Collectors.toList());
        }
        catch(NullPointerException exp) {
            return null;
        }
    }

    @GetMapping(value = {"/shift/check/id", "/shift/check/id/"})
    public boolean isShiftById(@RequestParam int id) {
        return shiftService.isShiftByID(id);
    }

    @GetMapping(value = {"/shift/check/date", "/shift/check/date/"})
    public boolean isShiftByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date) {
        return shiftService.isShiftByDate(date);
    }

    @GetMapping(value = {"/shift/check/status", "/shift/check/status/"})
    public boolean isShiftByStatus(@RequestParam ShiftStatus status) {
        return shiftService.isShiftByStatus(status);
    }

    @GetMapping(value = {"/shift/check/employee", "/shift/check/employee/"})
    public boolean isShiftByEmployee(@RequestParam int id) {
        return shiftService.isShiftByEmployeeID(id);
    }

    @GetMapping(value = {"/shift/check/datetime", "/shift/check/datetime/"})
    public boolean isShiftByDateAndTime(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date,
            @RequestParam("starttime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime starttime,
            @RequestParam("endtime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime endtime) {
        return shiftService.isShiftByDateAndStarTimeAndEndTime(date, starttime, endtime);
    }

    @GetMapping(value = {"/shift/check/empdatetime", "/shift/check/empdatetime/"})
    public boolean isShiftByEmployeeDateAndTime(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date,
            @RequestParam("starttime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime starttime,
            @RequestParam("endtime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime endtime,
            @RequestParam int id) {
        return shiftService.isShiftByEmployeeIDAndDateAndStartTimeAndEndTime(date, starttime, endtime, id);
    }

    @PostMapping(value = {"/shift/create", "/shift/create/"})
    public ShiftDto createShift(@RequestParam("date") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date,
                                @RequestParam("starttime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime starttime,
                                @RequestParam("endtime") @DateTimeFormat(pattern = "HH.mm.ss") LocalTime endtime,
                                @RequestParam ShiftStatus status) {
        return convertToDto(shiftService.createShift(date, starttime, endtime, status));
    }

    @PostMapping(value = {"/shift/update/status", "/shift/update/status/"})
    public ShiftDto updateShiftStatus(@RequestParam int shiftID, @RequestParam ShiftStatus status) {
        try {
            return convertToDto(this.shiftService.updateShiftStatus(shiftID, status));
        }
        catch(NullPointerException exp) {
            return null;
        }
    }


    @PostMapping(value = {"/shift/update/employee", "/shift/update/employee/"})
    public ShiftDto updateShiftEmployee(@RequestParam int shiftID, @RequestParam int employeeID) {
        try {
            return convertToDto(this.shiftService.updateShiftEmployee(shiftID, employeeID));
        }
        catch(NullPointerException exp) {
            return null;
        }
    }

    @DeleteMapping(value = {"/shifts/delete/", "/shifts/delete"})
    public boolean deleteShifts() {
        try {
            return this.shiftService.deleteAllShifts();
        }
        catch(NullPointerException exp) {
            return false;
        }
    }

    @DeleteMapping(value = {"/shift/delete/", "/shift/delete"})
    public boolean deleteShift(@RequestParam int id) {
        try {
            return this.shiftService.deleteShift(id);
        }
        catch(NullPointerException exp) {
            return false;
        }
    }

    private ShiftDto convertToDto(Shift s) throws NullPointerException{
        if(s == null) {
            throw new NullPointerException("Service is null");
        }

        if(s.getEmployee() != null) {
            return new ShiftDto(s.getShiftID(), s.getDate(), s.getStartTime(), s.getEndTime(), s.getShiftStatus(), convertToDto(s.getEmployee()));
        }

        return new ShiftDto(s.getShiftID(), s.getDate(), s.getStartTime(), s.getEndTime(), s.getShiftStatus());
    }

    private EmployeeDto convertToDto(Employee employee) throws NullPointerException{
        if(employee == null) {
            throw new NullPointerException("Owner is null");
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