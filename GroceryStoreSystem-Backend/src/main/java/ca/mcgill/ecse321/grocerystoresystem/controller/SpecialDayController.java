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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SpecialDayController {
  
  @Autowired
  private SpecialDayService specialDayService;
  
  /**
   * @author Yash Khapre
   * @param calendarID
   * Controller method to get closed days
   */
  @GetMapping(value = { "/specialday/closeddays/{id}", "/specialday/closeddays/{id}/" })
  public ResponseEntity getClosedDays(@PathVariable("id") int calendarID) {
    List<SpecialDayDto> closedDaysDto = new ArrayList<>();
    List<SpecialDay> closedDays;    
    try {
      closedDays = specialDayService.getClosedDays(calendarID);
  } catch (IllegalArgumentException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }  
    for(SpecialDay sDay : closedDays) {
      closedDaysDto.add(convertToDto(sDay));
    }    
    if (closedDaysDto.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No closedDays with specified calendar ID exist");
    }
    return new ResponseEntity<>(closedDaysDto, HttpStatus.OK);
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method to get all shifts on a specified special day
   */
  @GetMapping(value = { "/specialday/shifts/{id}", "/specialday/shifts/{id}/" })
  public ResponseEntity getSpecialShifts(@PathVariable("id") int specialDayID) {
    List<ShiftDto> specialShiftsDtoList = new ArrayList<>();
    List<Shift> specialShifts;    
    try {
      specialShifts = specialDayService.getSpecialShifts(specialDayID);
    } catch(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }    
    for(Shift s : specialShifts) {
      specialShiftsDtoList.add(convertToDto(s));
    }    
    if(specialShiftsDtoList.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No shifts at specified Special Day exist");
    }
    return new ResponseEntity<>(specialShiftsDtoList, HttpStatus.OK);    
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method that gets the employees on a specified specialDay
   */
  @GetMapping(value = { "/specialday/employees/{id}", "/specialday/employees/{id}/" })
  public ResponseEntity getEmployeesOnSpecialShifts(@PathVariable("id") int specialDayID) {
    List<EmployeeDto> employeeDtoList = new ArrayList<>();
    List<Employee> employeeList;    
    try {
      employeeList = specialDayService.getEmployeesOnSpecialShifts(specialDayID);
    } catch(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }    
    for(Employee e : employeeList) {
      employeeDtoList.add(convertToDto(e));
    }    
    if(employeeList.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees assigned at specified Special Day!");
    }
    return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);    
  }
  
  /**
   * @author Yash Khaprre
   * @param specialDayID, startTime, endTime
   * Controller method to update a specialDay's features
   */
  @PutMapping(value = { "/specialday/update/{id}", "/specialday/update/{id}/" })
  public ResponseEntity updateSpecialDay(@PathVariable("id") int specialDayID, LocalDateTime startTime, LocalDateTime endTime) {
    SpecialDay sDay;
    try {
      sDay = specialDayService.updateSpecialDay(specialDayID, startTime, endTime);
    }catch(IllegalArgumentException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    if (sDay == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot update specified special day!");
    }
    return new ResponseEntity<>(convertToDto(sDay), HttpStatus.OK);
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID, shiftID, date, startTime, endTime, shiftStatus, personID
   * Update a specific Shift on a specific specialDay
   */
  @PutMapping(value = { "specialday/updateshift/{id}", "/specialday/updateshift/{id}/" })
  public ResponseEntity updateSpecificDayShift(@PathVariable("id") int specialDayID, int shiftID, LocalDate date, 
      LocalTime startTime, LocalTime endTime, ShiftStatus shiftStatus, int personID) {
    Shift shift;
    try {
      shift = specialDayService.updateSpecificDayShift(shiftID, specialDayID, date, startTime, endTime, shiftStatus, personID);
    }catch(IllegalArgumentException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    if (shift == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot update specified special day!");
    }
    return new ResponseEntity<>(convertToDto(shift), HttpStatus.OK);
  }
  
  /**
   * @author Yash Khapre
   * @param specialDayID
   * Controller method to delete a specific specialDay
   */
  @DeleteMapping(value = { "/specialDay/delete/{id}", "/specialDay/delete/{id}/" })
  public ResponseEntity deleteSpecialDay(@PathVariable("id") int specialDayID){
      boolean delete;
      try {
          delete = specialDayService.deleteSpecialDayByID(specialDayID);
      } catch (IllegalArgumentException exception) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      if (delete == true && specialDayService.getSpecialDay(specialDayID) == null) {
        return ResponseEntity.status(HttpStatus.OK).body("SpecialDay with specialDayID " + specialDayID + " has been successfully deleted");
      }
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error deleting special day!");
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
