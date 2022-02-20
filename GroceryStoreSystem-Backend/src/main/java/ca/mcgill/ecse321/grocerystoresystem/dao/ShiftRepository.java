package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Integer>{

	Shift findShiftByShiftID(Integer shiftID);
}
