package ca.mcgill.ecse321.grocerystoresystem.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calendar")
public class Calendar {	
	
	@Id
	@GeneratedValue
	private int calendarID;
	
	// Calendar Associations
//	@OneToMany(mappedBy = "calendar_opening")
//	private List<StoreHour> openingTime;
//
//	@OneToMany(mappedBy = "calendar_days")
//	private Set<SpecialDay> closedDays;
//
//	@OneToMany(mappedBy = "calendar_shifts")
//	private Set<Shift> shifts;
  
	public Calendar() {
		
	}
	
	public int getCalendarID() {
		return this.calendarID;
	}
}
