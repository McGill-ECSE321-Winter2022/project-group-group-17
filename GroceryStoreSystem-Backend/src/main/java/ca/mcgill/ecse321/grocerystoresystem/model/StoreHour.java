package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class StoreHour {

	@Id
	@GeneratedValue
	private int storeHourID;
	
	private LocalTime startTime;
	private LocalTime endTime;
	private Weekdays weekday;
	
	// StoreHour Associations
	@ManyToOne()
	@JoinColumn(name="calendarid")
	private Calendar calendar_opening;
	
	public StoreHour() {};
	
	public StoreHour(LocalTime startTime, LocalTime endTime, Weekdays weekday) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekday = weekday;
	}
	
	public StoreHour(LocalTime startTime, LocalTime endTime, Weekdays weekday, int storeHourID ) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekday = weekday;
		this.storeHourID = storeHourID;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Weekdays getWeekday() {
		return weekday;
	}

	public void setWeekday(Weekdays weekday) {
		this.weekday = weekday;
	}

	public int getStoreHourID() {
		return storeHourID;
	}
	public void setStoreHourID (int storeHourID){
		this.storeHourID = storeHourID;
	}
}

