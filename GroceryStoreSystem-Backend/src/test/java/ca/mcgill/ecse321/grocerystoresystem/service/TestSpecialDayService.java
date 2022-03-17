package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.ShiftRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.SpecialDayRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.CalendarRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestSpecialDayService {
  
  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeService employeeService;

  @Mock
  private ShiftRepository shiftRepository;

  @InjectMocks
  private ShiftService shiftService;
  
  @Mock
  private SpecialDayRepository specialDayRepository;

  @InjectMocks
  private SpecialDayService specialDayService;

  @Mock
  private CalendarRepository calendarRepository;

  @InjectMocks
  private CalendarService calendarService;
  
  private static final int EMPLOYEE_KEY = 1001;
  private static final int SHIFT_KEY = 2001;
  private static final int SPECIALDAY_KEY = 3001;
  private static final int SPECIALDAY_KEY2 = 3002;
  private static final int SPECIALDAY_KEY3 = 3003;
  private static final int CALENDAR_KEY = 4001;
  
  private static final LocalDateTime START_TIME = LocalDateTime.parse("2022-03-13T15:14:21.629");
  private static final LocalDateTime END_TIME = LocalDateTime.parse("2022-03-14T15:14:21.629");
  private static final LocalDateTime START_TIME2 = LocalDateTime.parse("2022-03-18T16:14:21.629");
  private static final LocalDateTime END_TIME2 = LocalDateTime.parse("2022-03-19T16:14:21.629");  
  private static final LocalDateTime START_TIME3 = LocalDateTime.parse("2022-03-20T16:14:21.629");
  private static final LocalDateTime END_TIME3 = LocalDateTime.parse("2022-03-21T16:14:21.629");   
  
  /*
   * Helper method to create a special day with given parameters
   */
  private SpecialDay createSpecialDay() {
    SpecialDay sDay = new SpecialDay();
    sDay.setSpecialDayID(SPECIALDAY_KEY);
    sDay.setStartTimestamp(START_TIME);
    sDay.setEndTimestamp(END_TIME);
    
    return sDay;
  }
  
  /*
   * Helper method to create a calendar with specific ID
   */
  private Calendar createCalendar() {
    Calendar calendar = new Calendar();
    calendar.setCalendarID(CALENDAR_KEY);
    return calendar;
  }
  
  /*
   * Expected output of certain tests
   */
  @BeforeEach
  public void setMockOutput(){
    lenient().when(specialDayRepository.findSpecialDayBySpecialDayID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
      if(invocation.getArgument(0).equals(SPECIALDAY_KEY)) {
        return createSpecialDay();
      }else {
        return null;
      }
    });
    
    lenient().when(calendarRepository.findCalendarByCalendarID(anyInt())).then( (InvocationOnMock invocation) -> {
      if(invocation.getArgument(0).equals(CALENDAR_KEY)) {
        return createCalendar();
      }else {
        return null;
      }
    });
    
    lenient().when(specialDayRepository.existsBySpecialDayID(anyInt())).thenAnswer(
        (InvocationOnMock invocation) -> invocation.getArgument(0).equals(SPECIALDAY_KEY));
    
    Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
    lenient().when(specialDayRepository.save(any(SpecialDay.class))).thenAnswer(returnParamAsAnswer); 
  }
  
  /*
   * Test to successfully create a specialDay
   */
  @Test
  public void testCreateSpecialDay() {
    SpecialDay sDay = new SpecialDay();
    
    try {
      sDay = this.specialDayService.createSpecialDay(SPECIALDAY_KEY, START_TIME, END_TIME);
  }
  catch(IllegalArgumentException exp) {
      fail(exp.getMessage());
  }
    
    assertNotNull(sDay);
    assertEquals(sDay.getSpecialdayID(), SPECIALDAY_KEY);
    assertEquals(sDay.getStartTimestamp(), START_TIME);
    assertEquals(sDay.getEndTimestamp(), END_TIME);
  }

  /*
   * Test that makes sure a specialDay isn't created due to start time being greater than end time
   */
  @Test
  public void testCreateSpecialDayFail() {
    SpecialDay sDay = new SpecialDay();
    String error = null;
    
    try {
      sDay = this.specialDayService.createSpecialDay(SPECIALDAY_KEY, END_TIME, START_TIME);
  }
  catch(IllegalArgumentException exp) {
      error = exp.getMessage();
  }
  assertEquals(error, "The start time cannot be after the end time!");
  }
  
  /*
   * Test that makes sure a specialDay isn't created due to null start time
   */
  @Test
  public void testCreateSpecialDayFail2() {
    SpecialDay sDay = new SpecialDay();
    String error = null;
    
    try {
      sDay = this.specialDayService.createSpecialDay(SPECIALDAY_KEY, null, START_TIME);
  }
  catch(IllegalArgumentException exp) {
      error = exp.getMessage();
  }
  assertEquals(error, "Please enter a valid start time!");
  }
  
  /*
   * Test to successfully update a specialDay
   */
  @Test
  public void testUpdateSpecialDay() {
    SpecialDay sDay = createSpecialDay(); 
   
    try {
      sDay = specialDayService.updateSpecialDay(SPECIALDAY_KEY, START_TIME2, END_TIME2);
    }catch(IllegalArgumentException exp){
      fail(exp.getMessage());
    }
    assertNotNull(sDay);
    assertEquals(SPECIALDAY_KEY, sDay.getSpecialdayID());
    assertEquals(START_TIME2, sDay.getStartTimestamp());
    assertEquals(END_TIME2, sDay.getEndTimestamp());
  }
  
  /*
   * Test to make sure customer cannot be updated if specialDayID doesn't exist in system
   */
  @Test
  public void testUpdateSpecialDayFail() {
    SpecialDay sDay = createSpecialDay();
    String error = "";
    
    try {
      sDay = specialDayService.updateSpecialDay(SPECIALDAY_KEY2, START_TIME2, END_TIME2);
    }catch(IllegalArgumentException exp){
      error = exp.getMessage();
    }
    assertEquals(SPECIALDAY_KEY, sDay.getSpecialdayID());
    assertEquals(START_TIME, sDay.getStartTimestamp());
    assertEquals(END_TIME, sDay.getEndTimestamp());
    assertEquals(error, "Cannot find specialDay with given ID");
  }
  
  /*
   * Tests that checks that a special day cannot be updated if the start time is greater than the end time
   */
  @Test
  public void testUpdateSpecialDayFail2() {
    SpecialDay sDay = createSpecialDay();
    String error = "";
    
    try {
      sDay = specialDayService.updateSpecialDay(SPECIALDAY_KEY, END_TIME2, START_TIME2);
    }catch(IllegalArgumentException exp){
      error = exp.getMessage();
    }
    assertEquals(SPECIALDAY_KEY, sDay.getSpecialdayID());
    assertEquals(START_TIME, sDay.getStartTimestamp());
    assertEquals(END_TIME, sDay.getEndTimestamp());
    assertEquals(error, "The start time cannot be after the end time!");
  }
  
  /*
   * Check if a day is of type SpecialDay using a specialDayID
   */
  @Test
  public void testIsSpecialDayByID() {
      boolean result = this.specialDayService.isSpecialDayByID(SPECIALDAY_KEY);
      assertTrue(result);
  }
  
  /*
   * Check if a day is NOT of type SpecialDay using invalid ID
   */
  @Test
  public void testIsSpecialDayByIDFail() {
      boolean result = this.specialDayService.isSpecialDayByID(150);
      assertFalse(result);
  }
}
