package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.model.ShiftStatus;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Shift;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;

public interface ShiftRepository extends CrudRepository<Shift, Integer>{

	/**
	 * Find Shift based on their ID
	 */
	Shift findShiftByShiftID(Integer shiftID);

	/**
	 * Find shift based on the date
	 */
	List<Shift> findShiftByDate(LocalDate date);

	/**
	 * Find shift based on the status
	 */
	List<Shift> findShiftByShiftStatus(ShiftStatus status);

	/**
	 * Find shifts based on the employee id
	 */
	List<Shift> findShiftByEmployee_PersonID(int personID);

	/**
	 * Find shifts with specified date, start time and end time
	 */
	List<Shift> findShiftByDateAndStartTimeAndEndTime(LocalDate date, LocalTime startTime, LocalTime endTime);

	/**
	 * Find shifts with the specified date, start time, end time and employee id
	 */
	List<Shift> findShiftByEmployee_PersonIDAndDateAndStartTimeAndEndTime(int personID, LocalDate date, LocalTime startTime, LocalTime endTime);

	/**
	 * Returns true if there is a Shift with the specified shift id
	 */
	boolean existsByShiftID(Integer shiftID);

	/**
	 * Returns true if there is a Shift with the specified date
	 */
	boolean existsByDate(LocalDate date);

	/**
	 * Returns true if there is a Shift with the specified status
	 */
	boolean existsByShiftStatus(ShiftStatus status);

	/**
	 * Returns true if there is a Shift with the specified employee id
	 */
	boolean existsByEmployee_PersonID(int personID);

	/**
	 * Returns true if there is a Shift with the specified date, start time and end time
	 */
	boolean existsByDateAndStartTimeAndEndTime(LocalDate date, LocalTime startTime, LocalTime endTime);

	/**
	 * Returns true if there is a Shift with the specified date, start time, end time and employee id
	 */
	boolean existsByEmployee_PersonIDAndDateAndStartTimeAndEndTime(int personID, LocalDate date, LocalTime startTime, LocalTime endTIme);
}
