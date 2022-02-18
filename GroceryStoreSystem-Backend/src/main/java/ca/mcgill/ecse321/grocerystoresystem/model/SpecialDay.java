package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class SpecialDay {
	@Id
	@GeneratedValue
	private String specialDayID;
	
	// SpecialDay Associations
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "closedDays")
	private Calendar calendar;
	
	private LocalDateTime startTimestamp;
	private LocalDateTime endTimestamp;
	
	public SpecialDay(LocalDateTime startTimestamp, LocalDateTime endTimestamp, String specialDayID) {
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
		this.specialDayID = specialDayID;
	}

	public LocalDateTime getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(LocalDateTime startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public LocalDateTime getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(LocalDateTime endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public String getSpecialdayID() {
		return specialDayID;
	}
	public void setSpecialDayID(String specialDayID) {
		this.specialDayID = specialDayID;
	}
}
