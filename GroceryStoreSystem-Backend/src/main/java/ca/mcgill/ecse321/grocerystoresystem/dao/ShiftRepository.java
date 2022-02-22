package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Shift;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;

public interface ShiftRepository extends CrudRepository<Shift, Integer>{

	/**
	 * Find Shift List based on the Employee associated with them
	 */
	List<Shift> findByEmployee(Employee email);
	
	/**
	 * Find Shift based on their ID
	 */
	Shift findShiftByShiftID(Integer shiftID);
	
	/**
	 * Returns true if their is a Shift in the database with the passed ID as parameter
	 */
	boolean existsByShiftID(Integer shiftID);
}
