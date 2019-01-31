package CsvJsonDataProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import CsvJson.InternalCalls;
import CsvJsonPojos.GroupConfig;

public class CsvJsonMapper {
	static List<Integer> allGroupedCols = new ArrayList<Integer>();
	static List<GroupConfig> groupConfigs = new ArrayList<GroupConfig>();
	static List<String> Headers = new ArrayList<String>();
	static Set<Integer> coreDataCols = new HashSet<Integer>();
	JSONObject json = new JSONObject();

	public static List<String> getHeaders() {
		return Headers;
	}

	public static void setHeaders(List<String> headers) {
		Headers = headers;
	}

	public static Set<Integer> getCoreDataCols() {
		return coreDataCols;
	}

	public static void setCoreDataCols(Set<Integer> coreDataCols) {
		CsvJsonMapper.coreDataCols = coreDataCols;
	}

	public static List<Integer> getAllGroupedCols() {
		return allGroupedCols;
	}

	public static void setAllGroupedCols(List<Integer> allGroupedCols) {
		CsvJsonMapper.allGroupedCols = allGroupedCols;
	}

	public static List<CsvJsonPojos.GroupConfig> getGroupConfigs() {
		return groupConfigs;
	}

	public static void setGroupConfigs(List<GroupConfig> groupConfigs) {
		CsvJsonMapper.groupConfigs = groupConfigs;
	}

	public JSONArray BuildJson(ServletRequest request) throws IOException {
		System.out.println("Starting BuildJson");
		InternalCalls Internalcall = new InternalCalls();
		HttpServletRequest httpreq=(HttpServletRequest) request;
		String Groups = httpreq.getHeader("Grouping");
		Internalcall.getAllGroups(Groups);
		System.out.println("headers" +httpreq.getHeader("Fields").toString());
		String[] HeaderFields = httpreq.getHeader("Fields").split(",");
		Internalcall.setHeaders(HeaderFields);
		BufferedReader reader = request.getReader();
		String[] Records = reader.readLine().split(";");
		String[] headers = new String[Headers.size()];
		for(int i=0;i<headers.length;i++)
			headers[i]=Headers.get(i);
		for (String record : Records) {
			
			String[] InputFields = record.split(",");
			process(InputFields, headers);
		}
		CsvJsonDataStorage.printAllOrgs();
		CsvJsonDataStorage.printAllEmployees();
		JSONArray JsonArr = Internalcall.FinalJson();

		return JsonArr;
	}

	private void process(String[] fields, String[] headers) {
		System.out.println("Inside process");
		JSONArray jsonArray = new JSONArray();
		boolean first=true;
		String Org_Deptmapkey="";
		String Org_Deptmapvalue="";
		for (int i = 0; i < groupConfigs.size(); i++) {
			GroupConfig groupConfig = groupConfigs.get(i);
			String key = String.valueOf(i);
			for (int j : groupConfig.getGroupedCols()) {
				key = key + "-" + fields[j];
				if(first)
					Org_Deptmapkey=(Org_Deptmapkey+fields[j]);
				else
					Org_Deptmapvalue=Org_Deptmapvalue+fields[j];
			}
			first=false;
			if (CsvJsonDataStorage.getDataMap().containsKey(key)) {
				this.json = CsvJsonDataStorage.getDataMap().get(key);
				jsonArray = json.getJSONArray(groupConfig.getGroupName());
			} 
			else {
				JSONObject json = new JSONObject();
				jsonArray.put(json);
				
				for (int j : groupConfig.getGroupedCols()) 
					json.put(headers[j], fields[j]);
				jsonArray = new JSONArray();
				json.put(groupConfig.getGroupName(), jsonArray);
				CsvJsonDataStorage.getDataMap().put(key, json);

			}

			if (i == (groupConfigs.size() - 1)) {
				if(CsvJsonDataStorage.getOrg_DeptMap().containsKey(Org_Deptmapkey)) {
					if(!CsvJsonDataStorage.getOrg_DeptMap().get(Org_Deptmapkey).contains(Org_Deptmapvalue))
						CsvJsonDataStorage.getOrg_DeptMap().get(Org_Deptmapkey).add(Org_Deptmapvalue);
						
				}
				else {
					List<String> c=new ArrayList<String>();
					c.add(Org_Deptmapvalue);
					CsvJsonDataStorage.getOrg_DeptMap().put(Org_Deptmapkey,c);
				}
				InternalCalls obj = new InternalCalls();
				JSONObject jsonCore = obj.buildCoreData(fields, headers, coreDataCols,Org_Deptmapvalue);
				jsonArray.put(jsonCore);
			}
			
		}
		
	}
}
