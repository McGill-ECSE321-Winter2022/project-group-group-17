package ca.mcgill.ecse321.grocerystoresystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystoresystem.model.StoreHour;

public interface StoreHourRepository extends CrudRepository<StoreHour, Integer >{

	StoreHour findStoreHourByStoreHourID(Integer storeHourID);
}
