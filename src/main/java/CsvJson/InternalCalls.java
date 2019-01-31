package CsvJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import CsvJsonDataProcessor.CsvJsonDataStorage;
import CsvJsonDataProcessor.CsvJsonMapper;
import CsvJsonPojos.GroupConfig;

public class InternalCalls {
	Map<String, JSONObject> JsonFieldMap = new HashMap<String, JSONObject>();
	JSONObject Jsonob = new JSONObject();
	JSONArray JsonRowArray = new JSONArray();

	private String formCamelCaseName(String name) {
		System.out.println(" camelcase");
		String[] elements = name.split(" ");
		String camelCaseName = null;
		String temp = null;

		boolean firstIteration = true;

		for (String element : elements) {
			temp = element.substring(1, element.length());
			char c = (firstIteration) ? element.toLowerCase().charAt(0) : element.toUpperCase().charAt(0);
			temp = c + temp;
			camelCaseName = (firstIteration) ? temp : camelCaseName + temp;
			firstIteration = false;
		}
		System.out.println("camelcase is "+camelCaseName);
		return camelCaseName;
	}

	public void getAllGroups(String groups) {
		System.out.println("Groups colls");
		for (String group : groups.split(";")) {
			System.out.println("Inside group colls");
			GroupConfig thisGroup = new GroupConfig();
			String[] groupMembers = group.split(",");
			thisGroup.setGroupName(this.formCamelCaseName(groupMembers[0]));
			int firstElement = 0;
			for (String groupMember : groupMembers) {
				if (firstElement++ == 0)
					continue;
				thisGroup.getGroupedCols().add(Integer.valueOf(groupMember));
				
			}
			CsvJsonMapper.getAllGroupedCols().addAll(thisGroup.getGroupedCols());
			CsvJsonMapper.getGroupConfigs().add(thisGroup);
		}
	}

	public void setHeaders(String[] headerFields) {
		System.out.println(" headers");
		for (int i = 0; i < headerFields.length; i++) {
			CsvJsonMapper.getHeaders().add(this.formCamelCaseName(headerFields[i]));
			if (!CsvJsonMapper.getAllGroupedCols().contains(i))
				CsvJsonMapper.getCoreDataCols().add(i);
		}
	}

	public JSONObject buildCoreData(String[] fields, String[] headers, Set<Integer> coreDataCols,String valuemap) {
		JSONObject coreJson = new JSONObject();
		String Dept_Employeevalue="";
		for (int i : coreDataCols) {
			Dept_Employeevalue=Dept_Employeevalue+fields[i];
			coreJson.put(headers[i], fields[i]);
		}
		if(!CsvJsonDataStorage.getDept_EmployeeMap().containsKey(valuemap)) {
			List<String> c=new ArrayList<String>();
			Collections.addAll(c, Dept_Employeevalue);
			CsvJsonDataStorage.getDept_EmployeeMap().put(valuemap, c);
		}
		CsvJsonDataStorage.getDept_EmployeeMap().get(valuemap).add(Dept_Employeevalue);
		return coreJson;
	}

	public JSONArray FinalJson() {
		JSONArray finalResult = new JSONArray();
		for (String key : CsvJsonDataStorage.getDataMap().keySet()) {
			if (key.startsWith("0-")) {
				JSONObject orgJson = CsvJsonDataStorage.getDataMap().get(key);
				finalResult.put(orgJson);
			}
		}

		return finalResult;
	}

}