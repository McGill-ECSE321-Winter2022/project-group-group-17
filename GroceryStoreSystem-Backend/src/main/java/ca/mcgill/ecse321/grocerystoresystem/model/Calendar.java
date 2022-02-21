package ca.mcgill.ecse321.grocerystoresystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Calendar {	
	
	@Id
	@GeneratedValue
	private int calendarID;
	
  // Calendar Associations
  @OneToOne(optional=false, fetch = FetchType.LAZY)
  private StoreHour openingTime;
  @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
  private Set<SpecialDay> closedDays;
  @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
  private Set<Shift> shifts;
  
	public Calendar() {
		
	}
}
