package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shift {
	
	@Id
	@GeneratedValue
	private int shift_ID;
	
	
	// Shift Associations
	@ManyToOne(fetch = FetchType.LAZY)
	private Calendar calendar;
	@ManyToOne(mappedBy = "shifts", fetch = FetchType.LAZY)
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

	public int getShift_ID() {
		return shift_ID;
	}
	
	public ShiftStatus getShiftStatus() {
		return shiftStatus;
	}
	public void setShiftStatus(ShiftStatus shiftStatus) {
		this.shiftStatus = shiftStatus;
	}
	
	
	
}
