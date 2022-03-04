package ca.mcgill.ecse321.grocerystoresystem.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Employee extends Person {
	// Employee Associations
    @OneToMany(targetEntity = Shift.class)
    private List<Shift> shifts;

	private EmployeeStatus empStatus;

    public Employee() {
    	super(null, null, null, null);
    };

	public Employee(String first_name, String last_name, String email,  String password, EmployeeStatus empStatus ) {
		super(first_name, last_name, email, password);
		this.empStatus = empStatus;
	}

	public Employee(String first_name, String last_name, String email, String password, EmployeeStatus empStatus, Address address) {
		super(first_name, last_name, email, password, address);
		this.empStatus = empStatus;

	}

	public EmployeeStatus getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(EmployeeStatus empStatus) {
		this.empStatus = empStatus;
	}

}
