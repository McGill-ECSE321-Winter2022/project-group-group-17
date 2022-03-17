package ca.mcgill.ecse321.grocerystoresystem.service;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;
import java.time.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.grocerystoresystem.model.*;
import ca.mcgill.ecse321.grocerystoresystem.dao.*;

@Service
public class StoreHourService {
    
    @Autowired
    StoreHourRepository storeHourRepository;

    @Transactional
    public StoreHour createStoreHour(){
        StoreHour storeHour = new StoreHour();

        return storeHourRepository.save(storeHour);
    }

    @Transactional
    public StoreHour createStoreHour (LocalTime startTime, LocalTime endTime, Weekdays weekday, int storeHourID){
        if (endTime == null||startTime ==null || startTime.isAfter(endTime)) throw new IllegalArgumentException("Must enter valid start and end time");
        if (weekday == null) throw new IllegalArgumentException("Must enter valid weekday");

        StoreHour newStoreHour = new StoreHour(startTime, endTime, weekday, storeHourID);
        storeHourRepository.save(newStoreHour);
        return null;

    }
    @Transactional
    public boolean deleteStoreHour (int storeHourID){
        if (!storeHourRepository.existsByStoreHourID(storeHourID)) return false;
        StoreHour hour = storeHourRepository.findStoreHourByStoreHourID(storeHourID);
        storeHourRepository.delete(hour);
        return true;
    }

    @Transactional 
    public boolean deleteAllHours(){
        storeHourRepository.deleteAll();
        return this.getAllStoreHours().size() == 0;
    }

    @Transactional 
    public StoreHour updateStoreHourById (int storeHourID, LocalTime startTime, LocalTime endTime){
        if (!storeHourRepository.existsByStoreHourID(storeHourID)) throw new IllegalArgumentException("store hour does not exist");
        StoreHour hour = storeHourRepository.findStoreHourByStoreHourID(storeHourID);
        hour.setStartTime(startTime);
        hour.setEndTime(endTime);
        storeHourRepository.save(hour);
        return hour;
    }

    @Transactional 
    public StoreHour updateStoreHourByDay (Weekdays day, LocalTime startTime, LocalTime endTime){
        if (storeHourRepository.findStoreHourByWeekday(day)==null) throw new IllegalArgumentException("store hour does not exist");
        StoreHour hour = storeHourRepository.findStoreHourByWeekday(day);
        hour.setStartTime(startTime);
        hour.setEndTime(endTime);
        storeHourRepository.save(hour);
        return hour;
    }

    @Transactional 
    public StoreHour getStoreHour (int id){
        if (!storeHourRepository.existsByStoreHourID(id)) throw new IllegalArgumentException("store hour does not exist");
        return storeHourRepository.findStoreHourByStoreHourID(id);

    }

    @Transactional 
    public StoreHour getStoreHourByWeekday(Weekdays day){
        return storeHourRepository.findStoreHourByWeekday(day);
    }

    @Transactional 
    public List <StoreHour> getAllStoreHours() {
        return toList(storeHourRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}



}
