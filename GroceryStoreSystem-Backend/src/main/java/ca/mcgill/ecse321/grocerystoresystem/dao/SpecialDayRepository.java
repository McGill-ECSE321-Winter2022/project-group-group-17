package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.SpecialDay;

public interface SpecialDayRepository extends CrudRepository<SpecialDay, Integer>{

	SpecialDay findSpecialDayByShiftID(Integer shiftID);
}
