package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.time.LocalTime;
import ca.mcgill.ecse321.grocerystoresystem.model.*;

public class StoreHourDto {


    private int storeHourID;
	private LocalTime startTime;
	private LocalTime endTime;
	private Weekdays weekday;

    public StoreHourDto(int storeHourID, LocalTime startTime, LocalTime endTime, Weekdays weekday){
        this.storeHourID = storeHourID; 
        this.startTime= startTime; 
        this.endTime =endTime; 
        this.weekday = weekday; 

    }

    
    public int getStoreHourID(){
        return storeHourID;
    }

    public void setStoreHourID(int storeHourID){
        this.storeHourID= storeHourID;
    }

    public LocalTime getStartTime(){
        return startTime;

    }

    public void setStartTime(LocalTime startTime){
        this.startTime=startTime;
    }

    public LocalTime getEndTime(){
        return endTime; 
    }

    public void setEndTime(LocalTime endTime){
        this.endTime= endTime;
    }

    public Weekdays getWeekday(){
        return weekday;
    }
    public void setWeekday(Weekdays weekday){
        this.weekday=weekday;

    }

    


    
}
