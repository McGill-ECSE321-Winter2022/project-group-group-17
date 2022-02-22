package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

	Employee findEmployeeByEmail(String email);
	
	boolean existsByPersonID(Integer personID);
	
}
