package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.StoreHourRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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
public class TestStoreHourService {


    @Mock
    private StoreHourRepository storeHourRepository;

    @InjectMocks
    private StoreHourService storeHourService;

    private static final int HOUR_ID = 1000;
    private static final Weekdays HOUR_WEEKDAY1 = Weekdays.MONDAY;
    private static final LocalTime START_TIME1 = LocalTime.of(9, 0, 0);
    private static final LocalTime END_TIME1 = LocalTime.of(19, 0, 0);

    private static final int HOUR_ID2 = 1001;
    private static final Weekdays HOUR_WEEKDAY2 = Weekdays.SUNDAY;
    private static final LocalTime START_TIME2 = LocalTime.of(11, 0, 0);
    private static final LocalTime END_TIME2 = LocalTime.of(17, 0, 0);

    private StoreHour createStoreHour1(){

        StoreHour hour = new StoreHour();
        hour.setStartTime(START_TIME1);
        hour.setEndTime(END_TIME1);
        hour.setStoreHourID(HOUR_ID);
        hour.setWeekday(HOUR_WEEKDAY1);
        return hour;
    }

    private StoreHour createStoreHour2(){
        StoreHour hour = new StoreHour();
        hour.setStartTime(START_TIME2);
        hour.setEndTime(END_TIME2);
        hour.setStoreHourID(HOUR_ID2);
        hour.setWeekday(HOUR_WEEKDAY2);
        return hour;
    }


    private List <StoreHour> createStoreHours(){
        List <StoreHour> storeHours = new ArrayList<>();
        storeHours.add(createStoreHour1());
        storeHours.add(createStoreHour2());
        return storeHours;

    }


    @BeforeEach
    public void setMockOutput() {
        lenient().when(storeHourRepository.findStoreHourByStoreHourID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(HOUR_ID)) {
                return createStoreHour1();
            }
            else if(invocation.getArgument(0).equals(HOUR_ID2)) {
                return createStoreHour2();
            }
            else {
                return null;
            }
        });

        lenient().when(storeHourRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> createStoreHours());

        lenient().when(storeHourRepository.existsByStoreHourID(anyInt())).thenAnswer((InvocationOnMock invocation) -> invocation.getArgument(0).equals(HOUR_ID));

        Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
        lenient().when(storeHourRepository.save(any(StoreHour.class))).thenAnswer(returnParamAsAnswer);

}


    @Test
    public void testCreateStoreHour() {
        StoreHour hour = this.storeHourService.createStoreHour();

        assertNotNull(hour);
        assertNull(hour.getStartTime());
        assertNull(hour.getEndTime());
        assertNull(hour.getWeekday());
    }


//    @Test
//    public void testCreateStoreHour2(){
//        StoreHour hour = storeHourService.createStoreHour(START_TIME1, END_TIME1, HOUR_WEEKDAY1, HOUR_ID);
//        assertNotNull(hour);
//
//        assertEquals(START_TIME1, hour.getStartTime());
//        assertEquals(END_TIME1, hour.getEndTime());
//        assertEquals(HOUR_WEEKDAY1, hour.getWeekday());
//        assertEquals(HOUR_ID, hour.getStoreHourID());
//    }

    @Test
    public void testGetStoreHourSuccessful(){
        StoreHour hour = null;
        try {
            hour = storeHourService.getStoreHour(1000);

        }
        catch(NullPointerException exp) {
            fail(exp.getMessage());
        }

        assertNotNull(hour);
        assertEquals(HOUR_ID, hour.getStoreHourID());
        assertEquals(START_TIME1, hour.getStartTime());
        assertEquals(END_TIME1, hour.getEndTime());
        assertEquals(HOUR_WEEKDAY1, hour.getWeekday());

    }

//    @Test
//    public void testGetStoreHourUnsuccessful(){
//        assertEquals(1, storeHourService.getAllStoreHours().size());
//        StoreHour hour = null;
//        String error =  "";
//        try {
//            hour = storeHourService.getStoreHour(10001);
//        }
//        catch(NullPointerException exp) {
//            error  = exp.getMessage();
//        }
//
//        assertNull(hour);
//        assertEquals(error, "store hour does not exist");
//    }

    @Test
    public void testUpdateStoreHour(){

        StoreHour hour = this.storeHourService.createStoreHour(START_TIME1, END_TIME1, HOUR_WEEKDAY1, HOUR_ID);

        try {
            hour = this.storeHourService.updateStoreHour(HOUR_ID, START_TIME2, END_TIME2 );
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }

        assertNotNull(hour);
        assertEquals(hour.getStartTime(), START_TIME2);
        assertEquals(hour.getEndTime(), END_TIME2);
    }
    @Test
    public void testUpdateStoreHourUnsuccessful(){

    }

    @Test
    public void testFindAll(){

        List<StoreHour> hours = null;
        try {
            hours = storeHourService.getAllStoreHours();
        }
        catch(Exception exp) {
            fail(exp.getMessage());
        }
        assertNotNull(hours);
        assertEquals(hours.size(), 2);
    }






}


