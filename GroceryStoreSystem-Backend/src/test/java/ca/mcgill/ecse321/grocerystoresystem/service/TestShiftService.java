package ca.mcgill.ecse321.grocerystoresystem.service;

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

    private static final int SHIFT_ID = 1000;
    private static final LocalDate SHIFT_DATE = LocalDate.of(2022, 1, 5);
    private static final LocalTime SHIFT_START_TIME = LocalTime.of(8, 30, 00);
    private static final LocalTime SHIFT_END_TIME = LocalTime.of(17, 00, 00);
    private static final ShiftStatus SHIFT_STATUS = ShiftStatus.assigned;

    private static final int EMPLOYEE_ID = 1000;
    private static final String EMPLOYEE_FIRST_NAME = "Mario";
    private static final String EMPLOYEE_LAST_NAME = "Bouzakhm";
    private static final String EMPLOYEE_EMAIL = "mariobouzakhm02@gmail.com";
    private static final String EMPLOYEE_PASSWORD = "123456";
    private static final EmployeeStatus EMPLOYEE_STATUS = EmployeeStatus.hired;

    private Shift createShift() {
        Shift shift = new Shift();
        shift.setShiftID(1000);
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


    @BeforeEach
    public void setMockOutput() {
        lenient().when(shiftService.findShiftByID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SHIFT_ID)) {
                return createShift();
            } else {
                return null;
            }
        });

        Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
        lenient().when(shiftRepository.save(any(Shift.class))).thenAnswer(returnParamAsAnswer);
    }

    @Test
    public void testShiftGetIDSuccessfull() {
        assertEquals(0, shiftService.getAllShifts().size());
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
}
