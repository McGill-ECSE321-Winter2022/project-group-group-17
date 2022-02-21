package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.SpecialDay;
import ca.mcgill.ecse321.grocerystoresystem.model.Calendar;

public interface SpecialDayRepository extends CrudRepository<SpecialDay, Integer>{

	List<SpecialDay> findByCalendar(Calendar id);
}
