package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class StoreHour {

	@Id
	@GeneratedValue
	private int storehour_id;
	
	private LocalTime startTime;
	private LocalTime endTime;
	private Weekdays weekday;
	
	// StoreHour Associations
	@OneToOne(mappedBy="openingTime")
	private Calendar calendar;
	
	private StoreHour(LocalTime startTime, LocalTime endTime, Weekdays weekday) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekday = weekday;
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

	public int getStorehour_id() {
		return storehour_id;
	}
}
