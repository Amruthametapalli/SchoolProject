package CsvJsonDataProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class CsvJsonDataFetcher {
	public List<String> getemployees(HttpServletRequest Request) {
		try {
			System.out.println("Came to data fetcher");
			String uri=Request.getRequestURI();
			String[] parts = uri.split("/");
			String query=parts[2]+parts[3];
			System.out.println("query is "+query );
			return getEmployees(query);			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	private List<String> getEmployees(String query){
		List<String> employees=new ArrayList<String>();
		System.out.println(CsvJsonDataStorage.getOrg_DeptMap().size());
		
		if(CsvJsonDataStorage.getOrg_DeptMap().containsKey(query)) {
			List<String> departments=new ArrayList<String>();
			departments=CsvJsonDataStorage.getOrg_DeptMap().get(query);
			System.out.println("departments are "+departments.toString() );
			for(String depkey:departments) {
				employees.addAll(CsvJsonDataStorage.getDept_EmployeeMap().get(depkey));
			}
			System.out.println("employees are "+employees.toString() );
			return employees;
		}
		else {
			return null;
		}
	}
}