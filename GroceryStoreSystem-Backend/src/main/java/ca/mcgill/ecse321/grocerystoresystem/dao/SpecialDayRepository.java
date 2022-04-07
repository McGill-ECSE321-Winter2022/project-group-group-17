package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.SpecialDay;
import ca.mcgill.ecse321.grocerystoresystem.model.Calendar;

public interface SpecialDayRepository extends CrudRepository<SpecialDay, Integer>{
	
	/**
	 * Find SpecialDay based on their ID
	 */
	SpecialDay findSpecialDayBySpecialDayID(Integer specialDayID);
	
	/**
	 * Returns true if their is a Special Day in the database with the passed ID as parameter
	 */
	boolean existsBySpecialDayID(Integer specialDayID);
	
}
