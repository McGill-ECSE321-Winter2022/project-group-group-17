package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.ShiftRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestShiftService {

    @Mock
    private ShiftRepository shiftRepository;

    @InjectMocks
    private ShiftService shiftService;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;



    private static final int SHIFT_ID = 1000;
    private static final LocalDate SHIFT_DATE = LocalDate.of(2022, 1, 5);
    private static final LocalTime SHIFT_START_TIME = LocalTime.of(8, 30, 0);
    private static final LocalTime SHIFT_END_TIME = LocalTime.of(17, 0, 0);
    private static final ShiftStatus SHIFT_STATUS = ShiftStatus.assigned;

    private static final int SHIFT_ID2 = 1001;
    private static final LocalDate SHIFT_DATE2 = LocalDate.of(2022, 1, 5);
    private static final LocalTime SHIFT_START_TIME2 = LocalTime.of(8, 30, 0);
    private static final LocalTime SHIFT_END_TIME2 = LocalTime.of(17, 0, 0);
    private static final ShiftStatus SHIFT_STATUS2 = ShiftStatus.assigned;

    private static final int EMPLOYEE_ID = 1000;
    private static final String EMPLOYEE_FIRST_NAME = "Mario";
    private static final String EMPLOYEE_LAST_NAME = "Bouzakhm";
    private static final String EMPLOYEE_EMAIL = "mariobouzakhm02@gmail.com";
    private static final String EMPLOYEE_PASSWORD = "123456";
    private static final EmployeeStatus EMPLOYEE_STATUS = EmployeeStatus.hired;

    

    private Shift createShift() {
        Shift shift = new Shift();
        shift.setShiftID(SHIFT_ID);
        shift.setDate(SHIFT_DATE);
        shift.setStartTime(SHIFT_START_TIME);
        shift.setEndTime(SHIFT_END_TIME);
        shift.setShiftStatus(SHIFT_STATUS);

        Employee employee = new Employee();
        employee.setPersonID(EMPLOYEE_ID);
        employee.setFirstName(EMPLOYEE_FIRST_NAME);
        employee.setLastName(EMPLOYEE_LAST_NAME);
        employee.setEmail(EMPLOYEE_EMAIL);
        employee.setPassword(EMPLOYEE_PASSWORD);
        employee.setEmpStatus(EMPLOYEE_STATUS);

        shift.setEmployee(employee);
        return shift;
    }

    private Shift createShiftWithoutEmployee() {
        Shift shift = new Shift();
        shift.setShiftID(SHIFT_ID2);
        shift.setDate(SHIFT_DATE2);
        shift.setStartTime(SHIFT_START_TIME2);
        shift.setEndTime(SHIFT_END_TIME2);
        shift.setShiftStatus(SHIFT_STATUS2);

        return shift;
    }

    private List<Shift> createShifts() {
        List<Shift> shifts = new ArrayList<>();
        shifts.add(createShift());
        shifts.add(createShiftWithoutEmployee());

        return shifts;
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setPersonID(EMPLOYEE_ID);
        employee.setFirstName(EMPLOYEE_FIRST_NAME);
        employee.setLastName(EMPLOYEE_LAST_NAME);
        employee.setEmail(EMPLOYEE_EMAIL);
        employee.setPassword(EMPLOYEE_PASSWORD);
        employee.setEmpStatus(EMPLOYEE_STATUS);

        return employee;
    }


    @BeforeEach
    public void setMockOutput() {
        lenient().when(shiftRepository.findShiftByShiftID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SHIFT_ID)) {
                return createShift();
            }
            else if(invocation.getArgument(0).equals(SHIFT_ID2)) {
                return createShiftWithoutEmployee();
            }
            else {
                return null;
            }
        });

        lenient().when(shiftRepository.findShiftByShiftStatus(any(ShiftStatus.class))).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SHIFT_STATUS)) {
                return createShifts();
            }
            else {
                return new ArrayList<>();
            }
        });

        lenient().when(shiftRepository.findShiftByDate(any(LocalDate.class))).thenAnswer((InvocationOnMock invocation) -> {
           if(invocation.getArgument(0).equals(SHIFT_DATE)) {
               return createShifts();
           }
           else {
               return new ArrayList<>();
           }
        });


        lenient().when(shiftRepository.findShiftByEmployee_PersonID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMPLOYEE_ID)) {
                List<Shift> shifts = new ArrayList<>();
                shifts.add(createShift());
                return shifts;
            }
            else {
                return new ArrayList<>();
            }
        });
        
        lenient().when(shiftRepository.findShiftByDateAndStartTimeAndEndTime(any(LocalDate.class), any(LocalTime.class), any(LocalTime.class))).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SHIFT_DATE) && invocation.getArgument(1).equals(SHIFT_START_TIME) && invocation.getArgument(2).equals(SHIFT_END_TIME)) {
                List<Shift> shifts = new ArrayList<>();
                shifts.add(createShift());
                return shifts;
            }
            else {
                return new ArrayList<>();
            }
        });

        lenient().when(shiftRepository.findShiftByEmployee_PersonIDAndDateAndStartTimeAndEndTime(anyInt(), any(LocalDate.class), any(LocalTime.class), any(LocalTime.class))).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMPLOYEE_ID) && invocation.getArgument(1).equals(SHIFT_DATE) 
                    && invocation.getArgument(2).equals(SHIFT_START_TIME) 
                    && invocation.getArgument(3).equals(SHIFT_END_TIME)) {
                List<Shift> shifts = new ArrayList<>();
                shifts.add(createShift());
                return shifts;
            }
            else {
                return new ArrayList<>();
            }
        });
        
        lenient().when(shiftRepository.existsByShiftID(anyInt())).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0).equals(SHIFT_ID));
        lenient().when(shiftRepository.existsByDate(any(LocalDate.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0).equals(SHIFT_DATE));
        lenient().when(shiftRepository.existsByShiftStatus(any(ShiftStatus.class))).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0).equals(SHIFT_STATUS));
        lenient().when(shiftRepository.existsByEmployee_PersonID(anyInt())).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0).equals(EMPLOYEE_ID));
        lenient().when(shiftRepository.existsByDateAndStartTimeAndEndTime(any(LocalDate.class), any(LocalTime.class), any(LocalTime.class))).thenAnswer((InvocationOnMock invocation) ->
                invocation.getArgument(0).equals(SHIFT_DATE) && invocation.getArgument(1).equals(SHIFT_START_TIME) && invocation.getArgument(2).equals(SHIFT_END_TIME));
        lenient().when(shiftRepository.existsByEmployee_PersonIDAndDateAndStartTimeAndEndTime(anyInt(), any(LocalDate.class), any(LocalTime.class), any(LocalTime.class))).thenAnswer((InvocationOnMock invocation) ->
                invocation.getArgument(0).equals(EMPLOYEE_ID) && invocation.getArgument(1).equals(SHIFT_DATE)
                        && invocation.getArgument(2).equals(SHIFT_START_TIME) && invocation.getArgument(3).equals(SHIFT_END_TIME));


        lenient().when(employeeRepository.findEmployeeByPersonID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMPLOYEE_ID)) {
                return createEmployee();
            }

            else {
                return null;
            }
        });

        lenient().when(shiftRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> createShifts());

        Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
        lenient().when(shiftRepository.save(any(Shift.class))).thenAnswer(returnParamAsAnswer);
    }


    @Test
    public void createShift1() {
        Shift shift = this.shiftService.createShift();

        assertNotNull(shift);
        assertNull(shift.getShiftStatus());
        assertNull(shift.getEmployee());
    }

    @Test
    public void createShift2() {
        Shift shift = this.shiftService.createShift(SHIFT_DATE, SHIFT_START_TIME, SHIFT_END_TIME, SHIFT_STATUS);

        assertNotNull(shift);
        assertNull(shift.getEmployee());

        assertEquals(SHIFT_DATE, shift.getDate());
        assertEquals(SHIFT_START_TIME, shift.getStartTime());
        assertEquals(SHIFT_END_TIME, shift.getEndTime());
        assertEquals(SHIFT_STATUS, shift.getShiftStatus());
    }

    @Test
    public void createShift3() {
        Shift shift = this.shiftService.createShift(SHIFT_DATE, SHIFT_START_TIME, SHIFT_END_TIME, SHIFT_STATUS, createEmployee());

        assertNotNull(shift);
        assertNotNull(shift.getEmployee());

        assertEquals(SHIFT_DATE, shift.getDate());
        assertEquals(SHIFT_START_TIME, shift.getStartTime());
        assertEquals(SHIFT_END_TIME, shift.getEndTime());
        assertEquals(SHIFT_STATUS, shift.getShiftStatus());

        assertEquals(EMPLOYEE_FIRST_NAME, shift.getEmployee().getFirstName());
        assertEquals(EMPLOYEE_LAST_NAME, shift.getEmployee().getLastName());
        assertEquals(EMPLOYEE_EMAIL, shift.getEmployee().getEmail());
        assertEquals(EMPLOYEE_PASSWORD, shift.getEmployee().getPassword());
    }

    @Test
    public void testShiftGetIDSuccessful() {
        Shift shift = null;
        try {
            shift = shiftService.findShiftByID(1000);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(shift);
        assertEquals(SHIFT_ID, shift.getShiftID());
        assertEquals(SHIFT_DATE, shift.getDate());
        assertEquals(SHIFT_START_TIME, shift.getStartTime());
        assertEquals(SHIFT_END_TIME, shift.getEndTime());
        assertEquals(SHIFT_STATUS, shift.getShiftStatus());

        assertNotNull(shift.getEmployee());
        assertEquals(EMPLOYEE_ID, shift.getEmployee().getPersonID());
        assertEquals(EMPLOYEE_FIRST_NAME, shift.getEmployee().getFirstName());
        assertEquals(EMPLOYEE_LAST_NAME, shift.getEmployee().getLastName());
        assertEquals(EMPLOYEE_EMAIL, shift.getEmployee().getEmail());
        assertEquals(EMPLOYEE_PASSWORD, shift.getEmployee().getPassword());
        assertEquals(EMPLOYEE_STATUS, shift.getEmployee().getEmpStatus());
    }


    @Test
    public void testShiftGetIDUnsuccessful() {
        Shift shift = null;
        String error = null;
        try {
            shift = shiftService.findShiftByID(100001);
        }
        catch(NullPointerException exp) {
            error  = exp.getMessage();
        }

        assertNull(shift);
        assertEquals(error, "Shift not found");
    }

    @Test
    public void testShiftGetDateSuccessful() {
        List<Shift> shifts = null;
        try {
            shifts = shiftService.findShiftByDate(SHIFT_DATE);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(shifts);
        assertEquals(shifts.size(), 2);
    }

    @Test
    public void testShiftGetStatusSuccessful() {
        List<Shift> shifts = null;
        try {
            shifts = shiftService.findShiftByStatus(SHIFT_STATUS);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(shifts);
        assertEquals(shifts.size(), 2);
    }

    @Test
    public void testShiftGetEmployee_ID() {
        List<Shift> shifts = null;
        try {
            shifts = shiftService.findShiftByEmployee(EMPLOYEE_ID);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(shifts);
        assertEquals(shifts.size(), 1);
    }

    @Test
    public void testShiftGetDateTimes() {
        List<Shift> shifts = null;
        try {
            shifts = shiftService.findShiftsByDateAndStartTimeAndEndTime(SHIFT_DATE, SHIFT_START_TIME, SHIFT_END_TIME);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(shifts);
        assertEquals(shifts.size(), 1);
    }

    @Test
    public void testShiftGetEmployeeAndDateAndTimes() {
        List<Shift> shifts = null;
        try {
            shifts = shiftService.findShiftsByEmployeeAndDateAndStartTimeAndEndTime(EMPLOYEE_ID, SHIFT_DATE
                    , SHIFT_START_TIME, SHIFT_END_TIME);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(shifts);
        assertEquals(shifts.size(), 1);
    }

    @Test
    public void updateShiftStatus() {
        Shift shift = null;

        try {
            shift = this.shiftService.updateShiftStatus(SHIFT_ID, ShiftStatus.noShow);
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(shift);
        assertEquals(shift.getShiftStatus(), ShiftStatus.noShow);
    }

    @Test
    public void updateShiftEmployee() {
        Shift shift = null;

        try {
            shift = shiftService.updateShiftEmployee(SHIFT_ID2, EMPLOYEE_ID);
        }
        catch(Exception exp ) {
            fail(exp.getMessage());
        }

        assertNotNull(shift);
        assertEquals(shift.getEmployee().getPersonID(), EMPLOYEE_ID);
    }

    @Test
    public void testFindAll() {
        List<Shift> shifts = null;

        try {
            shifts = shiftService.getAllShifts();
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(shifts);
        assertEquals(shifts.size(), 2);
    }


    @Test
    public void testIsShiftById() {
        assertTrue(this.shiftService.isShiftByID(SHIFT_ID));
    }

    @Test
    public void testIsShiftByDate() {
        assertTrue(this.shiftService.isShiftByDate(SHIFT_DATE));
    }

    @Test
    public void testIsShiftByStatus() {
        assertTrue(this.shiftService.isShiftByStatus(SHIFT_STATUS));
    }

    @Test
    public void testIsShiftByEmployeeID() {
        assertTrue(this.shiftService.isShiftByEmployeeID(EMPLOYEE_ID));
    }

    @Test
    public void testIsShiftByDateStartTimeAndEndTime() {
        assertTrue(this.shiftService.isShiftByDateAndStarTimeAndEndTime(SHIFT_DATE, SHIFT_START_TIME, SHIFT_END_TIME));
    }

    @Test
    public void testIsShiftByEmployeeDateStartTimeAndEndTime() {
        assertTrue(this.shiftService.isShiftByEmployeeIDAndDateAndStartTimeAndEndTime(SHIFT_DATE, SHIFT_START_TIME, SHIFT_END_TIME, EMPLOYEE_ID));
    }
}
