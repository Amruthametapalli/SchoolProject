package CsvJsonDataProcessor;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class CsvJsonDataStorage {
	static Map<String, JSONObject> dataMap = new HashMap<String, JSONObject>();
	static Map<String,List<String>> Org_DeptMap=new HashMap<String,List<String>>();
	static Map<String,List<String>> Dept_EmployeeMap=new HashMap<String,List<String>>();

	public static Map<String, List<String>> getOrg_DeptMap() {
		return Org_DeptMap;
	}

	public static void setOrg_DeptMap(Map<String, List<String>> org_DeptMap) {
		Org_DeptMap = org_DeptMap;
	}

	public static Map<String, List<String>> getDept_EmployeeMap() {
		return Dept_EmployeeMap;
	}

	public static void setDept_EmployeeMap(Map<String, List<String>> dept_EmployeeMap) {
		Dept_EmployeeMap = dept_EmployeeMap;
	}

	public static Map<String, JSONObject> getDataMap() {
		return dataMap;
	}

	public static void setDataMap(Map<String, JSONObject> dataMap) {
		CsvJsonDataStorage.dataMap = dataMap;
	}
	
	public static void printAllOrgs() {
		for (String key : Org_DeptMap.keySet()) {
			System.out.print("Org : "+key+" => "+Org_DeptMap.get(key));
		}
	}
	public static void printAllEmployees() {
		for (String key : Dept_EmployeeMap.keySet()) {
			System.out.print("Org : "+key+" => "+Dept_EmployeeMap.get(key));
		}
	}
}
