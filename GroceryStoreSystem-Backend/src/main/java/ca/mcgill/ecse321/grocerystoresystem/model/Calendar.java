package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;

@Entity
public class Calendar {	
	
  // Calendar Associations
  @OneToOne(optional="false", fetch = FetchType.LAZY)
  private StoreHour openingTime;
  @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
  private SpecialDay closedDays;
  @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
  private Shift shifts;
  
	public Calendar() {
		
	}
}
