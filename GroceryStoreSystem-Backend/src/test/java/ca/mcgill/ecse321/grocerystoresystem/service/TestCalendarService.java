package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.CalendarRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestCalendarService {
    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private CalendarService calendarService;

    private static final int CALENDAR_ID = 1000;

    private Calendar createCalendar() {
        Calendar calendar = new Calendar();
        calendar.setCalendarID(CALENDAR_ID);

        return calendar;
    }

    @BeforeEach
    public void setMockOutput() {
        lenient().when(calendarRepository.findCalendarByCalendarID(anyInt())).then((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CALENDAR_ID)) {
                return createCalendar();
            }
            else {
                return null;
            }
        });


        lenient().when(calendarRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<Calendar> calendars = new ArrayList<>();
            calendars.add(createCalendar());

            return calendars;
        });

        lenient().when(calendarRepository.existsByCalendarID(anyInt())).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0).equals(CALENDAR_ID));


        Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(calendarRepository.save(any(Calendar.class))).thenAnswer(returnParamAsAnswer);
    }

    @Test
    public void testCreateCalendar() {
        Calendar calendar = this.calendarService.createCalendar();

        assertNotNull(calendar);
    }


    @Test
    public void testGetCalendarByID() {
        Calendar calendar = null;
        try {
            calendar = this.calendarService.findCalendarByID(CALENDAR_ID);
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(calendar);
    }

    @Test
    public void testExistsByCalendarId() {
        assertTrue(this.calendarService.isCalendarByID(CALENDAR_ID));
    }

    @Test
    public void testFindAllCalendars() {
        List<Calendar> calendars = null;

        try {
            calendars = this.calendarService.getAllCalendars();
        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(calendars);
        assertEquals(calendars.size(), 1);
    }
}
