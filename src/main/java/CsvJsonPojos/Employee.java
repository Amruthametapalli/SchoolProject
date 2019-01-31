package CsvJsonPojos;


public class Employee {
	private String EmployeeName;
	private String Role;
	private long EmployeeId;
	public Employee(String name,long id,String role ){
		EmployeeName=name;
		EmployeeId=id;
		Role=role;
	}
	Employee(){
		
	}
	
	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public long getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(long employeeId) {
		EmployeeId = employeeId;
	}

	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}

	
	
}