package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class Shift {
	
	@Id
	@GeneratedValue
	private String shiftID;
	
	
	// Shift Associations
	@ManyToOne(fetch = FetchType.LAZY)
	private Calendar calendar;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shifts")
	private Employee employee;
	
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private ShiftStatus shiftStatus;
	
	public Shift(LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus shiftStatus) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.shiftStatus = shiftStatus;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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

	public String getShiftID() {
		return shiftID;
	}
	
	public ShiftStatus getShiftStatus() {
		return shiftStatus;
	}
	public void setShiftStatus(ShiftStatus shiftStatus) {
		this.shiftStatus = shiftStatus;
	}
	
	
	
}
