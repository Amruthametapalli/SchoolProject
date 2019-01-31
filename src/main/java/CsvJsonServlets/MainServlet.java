package CsvJsonServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import CsvJsonDataProcessor.CsvJsonDataFetcher;
import CsvJsonDataProcessor.CsvJsonMapper;
public class MainServlet implements Servlet{
	public void init(ServletConfig config) throws ServletException {
	
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		CsvJsonMapper Csv_JsonMapper=new CsvJsonMapper();
		CsvJsonDataFetcher DataFetcher=new CsvJsonDataFetcher();
		PrintWriter printer=res.getWriter();
		printer.write("Servlet invoked");
		HttpServletRequest httpreq=(HttpServletRequest) req;
		if(httpreq.getHeader("Fields")==null) {
		printer.write(DataFetcher.getemployees(httpreq).toString());
		}
		else {
			JSONArray JsonOrgArray=Csv_JsonMapper.BuildJson(req);
			printer.write("Final json is ");
			printer.write(JsonOrgArray.toString());
		}
	}
	
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}