package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Calendar;

public interface CalendarRepository extends CrudRepository<Calendar, Integer>{
	
	/**
	 * Find Calendar based on their ID
	 */
	Calendar findCalendarByCalendarID(Integer calendarID);
	
	/**
	 * Returns true if their is an calendar in the database with the passed ID as parameter
	 */
	boolean existsByCalendarID(Integer calendarID);
}

