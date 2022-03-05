package ca.mcgill.ecse321.grocerystoresystem.service;

import ca.mcgill.ecse321.grocerystoresystem.dao.ShiftRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.Shift;
import ca.mcgill.ecse321.grocerystoresystem.model.ShiftStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Transactional
    public Shift createShift() {
        Shift shift = new Shift();

        shiftRepository.save(shift);
        return shift;
    }

    @Transactional
    public Shift createShift(LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus status) {
        if(date == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid date");
        if(startTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid start time");
        if(endTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid end time");
        if(status == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid shift status");

        Shift shift = new Shift(date, startTime, endTime, status);
        shiftRepository.save(shift);

        return shift;
    }

    @Transactional
    public Shift createShift(LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus status, Employee employee) {
        if(date == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid date");
        if(startTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid start time");
        if(endTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid end time");
        if(status == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid shift status");
        if(employee == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid employee");

        Shift shift = new Shift(date, startTime, endTime, status, employee);
        shiftRepository.save(shift);

        return shift;
    }

    @Transactional
    public Shift deleteShift(int shiftID) {
        Shift shift = this.shiftRepository.findShiftByShiftID(shiftID);
        if(shift == null) throw new NullPointerException("Shift not found");

        shiftRepository.delete(shift);
        return shift;
    }

    @Transactional
    public boolean isShiftByID(int shiftID) {
        return shiftRepository.existsByShiftID(shiftID);
    }

    @Transactional
    public boolean isShiftByDate(LocalDate date) {
        if(date == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid date");

        return shiftRepository.existsByDate(date);
    }

    @Transactional
    public boolean isShiftByStatus(ShiftStatus status) {
        if(status == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid shift status");

        return shiftRepository.existsByShiftStatus(status);
    }

    @Transactional
    public boolean isShiftByDateAndStarTimeAndEndTime(LocalDate date, LocalTime startTime, LocalTime endTime) {
        if(date == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid date");
        if(startTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid start time");
        if(endTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid end time");

        return shiftRepository.existsByDateAndStartTimeAndEndTime(date, startTime, endTime);
    }

    @Transactional
    public boolean isShiftByEmployeeID(int personID) {
        return shiftRepository.existsByEmployee_PersonID(personID);
    }

    @Transactional
    public boolean isShiftByEmployeeIDAndDateAndStartTimeAndEndTime(LocalDate date, LocalTime startTime, LocalTime endTime, int peronID) {
        if(date == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid date");
        if(startTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid start time");
        if(endTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid end time");

        return shiftRepository.existsByEmployee_PersonIDAndDateAndStartTimeAndEndTime(peronID, date, startTime, endTime);
    }

    @Transactional
    public Shift findShiftByID(int shiftID) {
        Shift shift = shiftRepository.findShiftByShiftID(shiftID);
        if(shift == null) throw new NullPointerException("Shift not found");

        return shift;
    }

    @Transactional
    public List<Shift> findShiftByDate(LocalDate date) {
        if(date == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid date");

        List<Shift> shifts = shiftRepository.findShiftByDate(date);
        if(shifts == null || shifts.size() == 0)  throw new IllegalArgumentException("Shifts not found");

        return shifts;
    }

    @Transactional
    public List<Shift> findShiftByStatus(ShiftStatus status) {
        if(status == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid shift status");

        List<Shift> shifts = shiftRepository.findShiftByShiftStatus(status);
        if(shifts == null || shifts.size() == 0)  throw new IllegalArgumentException("Shifts not found");

        return shifts;
    }

    @Transactional
    public List<Shift> findShiftByEmployee(int personID) {
        List<Shift> shifts = shiftRepository.findShiftByEmployee_PersonID(personID);
        if(shifts == null || shifts.size() == 0)  throw new NullPointerException("Shifts not found");

        return shifts;
    }

    @Transactional
    public List<Shift> findShiftsByDateAndStartTimeAndEndTime(LocalDate date, LocalTime startTime, LocalTime endTime) {
        if(date == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid date");
        if(startTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid start time");
        if(endTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid end time");

        List<Shift> shifts =  shiftRepository.findShiftByDateAndStartTimeAndEndTime(date, startTime, endTime);
        if(shifts == null || shifts.size() == 0) throw new NullPointerException("Shifts not found");

        return shifts;
    }

    @Transactional
    public List<Shift> findShiftsByEmployeeAndDateAndStartTimeAndEndTime(int peronID, LocalDate date, LocalTime startTime, LocalTime endTime) {
        if(date == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid date");
        if(startTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid start time");
        if(endTime == null) throw new IllegalArgumentException("Please provide valid arguments: Invalid end time");

        List<Shift> shifts = shiftRepository.findShiftByEmployee_PersonIDAndDateAndStartTimeAndEndTime(peronID, date, startTime, endTime);
        if(shifts == null || shifts.size() == 0) throw new NullPointerException("Shifts not found");

        return shifts;
    }

    @Transactional
    public List<Shift> getAllShifts() {
        return toList(shiftRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
