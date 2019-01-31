package CsvJsonPojos;

import java.util.ArrayList;
import java.util.List;

public class Organizations {
	List<Organization> orgs=new ArrayList<Organization>();
	public Organizations(){
		
	}
	public List<Organization> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<Organization> orgs) {
		this.orgs = orgs;
	}

}
