package ca.mcgill.ecse321.grocerystoresystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Shift;
import ca.mcgill.ecse321.grocerystoresystem.model.Employee;

public interface ShiftRepository extends CrudRepository<Shift, Integer>{

	List<Shift> findShiftByEmployee(Employee email);
}
