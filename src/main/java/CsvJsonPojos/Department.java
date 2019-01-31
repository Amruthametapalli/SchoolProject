package CsvJsonPojos;
import java.util.ArrayList;
import java.util.List;

public class Department{
	private String DepName;
	public List<Employee> employees=new ArrayList<Employee>();
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmpob(List<Employee> empob) {
		this.employees = empob;
	}
	public Department(String depname){
		DepName=depname;
		
	}
	public Department(){
		
	}
	public String getDepName() {
		return DepName;
	}
	public void setDepName(String depName) {
		DepName = depName;
	}
}