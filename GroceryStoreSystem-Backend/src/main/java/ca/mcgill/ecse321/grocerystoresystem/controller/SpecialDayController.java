package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.EmployeeDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.ShiftDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.SpecialDayDto;
import ca.mcgill.ecse321.grocerystoresystem.model.SpecialDay;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.Shift;
import ca.mcgill.ecse321.grocerystoresystem.model.ShiftStatus;
import ca.mcgill.ecse321.grocerystoresystem.service.SpecialDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class SpecialDayController {
  
  @Autowired
  private SpecialDayService specialDayService;
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method to get create specialDay
   */
  @PostMapping(value = { "/specialday/create", "/specialday/create/"})
  public SpecialDayDto createSpecialDay(@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy.MM.dd/HH.mm.ss") LocalDateTime startTime,
      @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy.MM.dd/HH.mm.ss") LocalDateTime endTime) {
    SpecialDay sDay = this.specialDayService.createSpecialDay(startTime, endTime);
    return convertToDto(sDay);
  }
  
  @GetMapping(value = { "/specialdays", "/specialdays/" })
  public List<SpecialDayDto> getAllCustomers() {
    return specialDayService.getAllSpecialDays().stream().map(this::convertToDto).collect(Collectors.toList());
}
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method to get a specialDay
   */
  @GetMapping(value = { "/specialday/get/id", "/specialday/get/id/" })
  public SpecialDayDto getSpecialDay(@RequestParam int id) {
    try {
      return convertToDto(specialDayService.getSpecialDay(id));
  }
  catch (NullPointerException exp) {
      return null;
  }
  }
  
  /**
   * @author Yash Khapre
   * @param calendarID
   * Controller method to get closed days
   */
  @GetMapping(value = { "/specialday/closeddays/id", "/specialday/closeddays/id/" })
  public List<SpecialDayDto> getClosedDays(@RequestParam int id) {
    try {
      return specialDayService.getClosedDays(id).stream().map(this::convertToDto).collect(Collectors.toList());
  }
  catch (NullPointerException exp) {
      return null;
  }
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method to get all shifts on a specified special day
   */
  @GetMapping(value = { "/specialday/shifts/id", "/specialday/shifts/id/" })
  public List<ShiftDto> getSpecialShifts(@RequestParam int id) { 
    try {
      return specialDayService.getSpecialShifts(id).stream().map(this::convertToDto).collect(Collectors.toList());
  }
  catch (NullPointerException exp) {
      return null;
  }
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method that gets the employees on a specified specialDay
   */
  @GetMapping(value = { "/specialday/employees/id", "/specialday/employees/id/" })
  public List<EmployeeDto> getEmployeesOnSpecialShifts(@RequestParam int id) {
    try {
      return specialDayService.getEmployeesOnSpecialShifts(id).stream().map(this::convertToDto).collect(Collectors.toList());
  }
  catch (NullPointerException exp) {
      return null;
  }
  }
  
  /**
   * @author Yash Khaprre
   * @param specialDayID, startTime, endTime
   * Controller method to update a specialDay's features
   */
  @PostMapping(value = { "/specialday/update", "/specialday/update/" })
  public SpecialDayDto updateSpecialDay(@RequestParam int id, @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy.MM.dd/HH.mm.ss") LocalDateTime startTime,
      @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy.MM.dd/HH.mm.ss") LocalDateTime endTime) {
    try {
      return convertToDto(specialDayService.updateSpecialDay(id, startTime, endTime));
  } catch (NullPointerException | IllegalArgumentException exception) {
      return null;
  }
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID, shiftID, date, startTime, endTime, shiftStatus, personID
   * Update a specific Shift on a specific specialDay
   */
  @PostMapping(value = { "specialday/updateshift/id", "/specialday/updateshift/id/" })
  public ShiftDto updateSpecificDayShift(@RequestParam int id, @RequestParam int shiftID, @RequestParam LocalDate date, 
      @RequestParam LocalTime startTime, @RequestParam LocalTime endTime, @RequestParam ShiftStatus shiftStatus, @RequestParam int personID) {
    try {
      return convertToDto(specialDayService.updateSpecificDayShift(shiftID, id, date, startTime, endTime, shiftStatus, personID));
  } catch (NullPointerException | IllegalArgumentException exception) {
      return null;
  }
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method to delete a specific specialDay
   */
  @DeleteMapping(value = { "/specialday/delete", "/specialday/delete/" })
  public boolean deleteSpecialDay(@RequestParam int id){
    try {
      return specialDayService.deleteSpecialDayByID(id);
  }
  catch (NullPointerException exp) {
      return false;
  }
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method to check if a specialDay exists given an ID
   */
  @GetMapping(value = {"/specialday/check/id/", "/specialay/check/id"})
  public boolean isCustomerWithID(@RequestParam int id) {
      return specialDayService.isSpecialDayByID(id);
  }
  
  /**
   * @author Yash Khapre
   * @param SpecialDay sDay
   * Method to convert a SpecialDay into a SpecialDayDto
   */
  private SpecialDayDto convertToDto(SpecialDay sDay) {
    if (sDay == null) {
      throw new NullPointerException("Cannot find this Special Day!");
    }
    return new SpecialDayDto(sDay.getStartTimestamp(), sDay.getEndTimestamp(), sDay.getSpecialdayID());
    }
  
  /**
   * @author Yash Khapre
   * @param Shift s
   * Method to convert a Shift into a ShiftDto
   */
  private ShiftDto convertToDto(Shift s) {
    if (s == null) {
      throw new NullPointerException("Cannot find this Shift!");
    }
    return new ShiftDto(s.getShiftID(), s.getDate(), s.getStartTime(), s.getEndTime(), s.getShiftStatus(), convertToDto(s.getEmployee()));
    }
  
  /**
   * @author Yash Khapre
   * @param Employee e
   * Method to convert an Employee into an EmployeeDto
   */
  private EmployeeDto convertToDto(Employee e) {
    if (e == null) {
      throw new NullPointerException("Cannot find this Employee!");
    }
    return new EmployeeDto(e.getPersonID(), e.getFirstName(), e.getLastName(), e.getEmail(), e.getEmpStatus(), convertToDto(e.getAddress()));
    }
  
  /**
   * @author Yash Khapre
   * @param Address address
   * Method to convert an Address into an AddressDto
   */
  private AddressDto convertToDto(Address address) {
    if (address == null) {
      throw new NullPointerException("Cannot find Address!");
    }
    return new AddressDto(address.getAddressID(), address.isLocal(), address.getStreetName(), address.getCity(), address.getPostalCode(), address.getStreetNum(), address.getCountry());
  }
  
}
