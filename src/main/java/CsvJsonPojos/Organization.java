package CsvJsonPojos;

import java.util.ArrayList;
import java.util.List;

public class Organization{
	private String OrgName;
	public List<Department> dep=new ArrayList<Department>();
	public Organization(String orgname){
		OrgName=orgname;
	}
	public Organization(){
		
	}
	public List<Department> getDep() {
		return dep;
	}
	public void setDep(List<Department> dep) {
		this.dep = dep;
	}
	
	public String getOrgName() {
		return OrgName;
	}
	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
}
