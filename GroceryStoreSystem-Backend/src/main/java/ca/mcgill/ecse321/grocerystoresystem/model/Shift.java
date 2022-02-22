package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class Shift {
	
	@Id
	@GeneratedValue
	private int shiftID;
	
	
	// Shift Associations
	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	private Calendar calendar_shifts;
	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "shifts")
	private Employee employee;
	
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private ShiftStatus shiftStatus;
	
	public Shift() {};
	
	public Shift(LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus shiftStatus) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.shiftStatus = shiftStatus;
	}
	
	public Shift(int shiftID,LocalDate date, LocalTime startTime, LocalTime endTime, ShiftStatus shiftStatus) {
		this.shiftID = shiftID;
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

	public int getShiftID() {
		return shiftID;
	}
	public void setShiftID(int shiftID) {
		this.shiftID = shiftID;
	}
	
	public ShiftStatus getShiftStatus() {
		return shiftStatus;
	}
	public void setShiftStatus(ShiftStatus shiftStatus) {
		this.shiftStatus = shiftStatus;
	}
	
	
	
}
