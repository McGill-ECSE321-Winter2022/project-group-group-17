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

	//Calendar Associations
	@OneToMany(targetEntity = StoreHour.class)
	private List<StoreHour> openingTimes;

	@OneToMany(targetEntity = SpecialDay.class)
	private List<SpecialDay> closedDays;

	@OneToMany(targetEntity = Shift.class)
	private List<Shift> employeeShifts;
  
	public Calendar() {
		
	}
	
	public int getCalendarID() {
		return this.calendarID;
	}

	public List<StoreHour> getOpeningTimes() {
		return this.openingTimes;
	}

	public List<SpecialDay> getClosedDays() {
		return this.closedDays;
	}

	public List<Shift> getEmployeeShifts() {
		return this.employeeShifts;
	}
}
