package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystoresystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.ShiftRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.SpecialDayRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.CalendarRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.*;

@Service
public class SpecialDayService {
  
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private CalendarRepository calendarRepository;
  @Autowired
  private ShiftRepository shiftRepository;
  @Autowired
  private SpecialDayRepository specialDayRepository;
  
  
  /*
   * Get all closed days with calendarID
   */
  @Transactional
  public List<SpecialDay> getClosedDays(int calendarID){
    List<SpecialDay> closedDays = new ArrayList<>();
    closedDays = calendarRepository.findCalendarByCalendarID(calendarID).getClosedDays();
    
    return closedDays;
  }
  
  /*
   * Get all shifts on special days given a specialDayID
   */
  @Transactional
  public List<Shift> getSpecialShifts(int specialDayID){
    List<Shift> specialShifts = new ArrayList<>();
    SpecialDay sDay = specialDayRepository.findSpecialDayBySpecialDayID(specialDayID);
    
    if(sDay == null) {
      throw new IllegalArgumentException("Cannot find specialDay with given ID");
    }
    
    for(Shift s : shiftRepository.findAll()) {
      
      if((s.getDate()).compareTo(sDay.getStartTimestamp().toLocalDate()) == 0){
        
        if((s.getStartTime().isAfter(sDay.getStartTimestamp().toLocalTime())) && (s.getEndTime().isBefore(sDay.getEndTimestamp().toLocalTime()))) {
          
          specialShifts.add(s);
          
        }
      }
    }
    
    return specialShifts;
  }

  /*
   * Update special days
   */
  @Transactional
  public boolean updateSpecialDay(int specialDayID, LocalDateTime startTime, LocalDateTime endTime) {
   
    SpecialDay s = specialDayRepository.findSpecialDayBySpecialDayID(specialDayID);
    if(s == null) {
      throw new IllegalArgumentException("Cannot find specialDay with given ID");
    }
    
    if(startTime.isAfter(endTime)) {
      throw new IllegalArgumentException("The start time cannot be after the end time!");
    }
    
    s.setStartTimestamp(startTime);
    s.setEndTimestamp(endTime);
    
    specialDayRepository.save(s);
    return true;
  }
  
  /*
   * Update Shift on SpecialDay
   */
  @Transactional
  public boolean updateSpecificDayShift(int shiftID, int specialDayID, LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus shiftStatus, int personID) {
    Shift s = shiftRepository.findShiftByShiftID(shiftID);
    SpecialDay sDay = specialDayRepository.findSpecialDayBySpecialDayID(specialDayID);
    Employee employee = employeeRepository.findEmployeeByPersonID(personID);
    
    if (personID <= 0) {
      throw new IllegalArgumentException("Please enter a valid PersonID");
    }
    
    if(employee == null) {
      throw new IllegalArgumentException("Cannot find employee with given ID");
    }
    
    if(s == null) {
      throw new IllegalArgumentException("Cannot find Shift with given ID");
    }
    
    if(sDay == null) {
      throw new IllegalArgumentException("Cannot find specialDay with given ID");
    }
    
    if((s.getDate()).compareTo(sDay.getStartTimestamp().toLocalDate()) == 1){
      throw new IllegalArgumentException("The shift with given ID is not on the same day as the Special Day!");
    }
     
    if(!(s.getStartTime().isAfter(sDay.getStartTimestamp().toLocalTime())) && (s.getEndTime().isBefore(sDay.getEndTimestamp().toLocalTime()))) {
      throw new IllegalArgumentException("This shift does not match the Special Day hours ");
    }
    
    if(date.compareTo(sDay.getStartTimestamp().toLocalDate())==1) {
      throw new IllegalArgumentException("The new shift date doesn't match the Special Day date!");
    }
    
    if(startTime.isAfter(endTime)) {
      throw new IllegalArgumentException("The start time cannot be after the end time!");
    }
    
    s.setDate(date);
    s.setEmployee(employee);
    s.setEndTime(endTime);
    s.setStartTime(startTime);
    s.setShiftStatus(shiftStatus);
    
    shiftRepository.save(s);
    specialDayRepository.save(sDay);
    employeeRepository.save(employee);
    
    return true;
  }
  
  /*
   * Delete specialDay given specialDayID
   */
  @Transactional
  public boolean deleteSpecialDayByID(int specialDayID) {
    if (specialDayID <= 0) {
      throw new IllegalArgumentException("Please enter a valid personID");
    }
    
    SpecialDay sDay = specialDayRepository.findSpecialDayBySpecialDayID(specialDayID);
    
    if(sDay == null) {
      throw new NullPointerException("Cannot find Customer with this personID");
    }
    
    specialDayRepository.delete(sDay);
    return true;
  }
  
}
