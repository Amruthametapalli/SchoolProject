package com.amrutha.hibernateTest.servlets;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amrutha.hibernateTest.dataProcessor.DataProcessor;
import com.amrutha.hibernateTest.exceptions.MyException;
import com.amrutha.hibernateTest.pojo.ResponsePojo;
import com.amrutha.hibernateTest.daos.Schools;

@Path("/school/add")
public class SchoolServlet {

	// Inserting total school details with APPLICATION/JSON
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSchool(Schools data) throws Exception{

		DataProcessor d = new DataProcessor();
		ResponsePojo resp = new ResponsePojo();
		
		try {
			//Student details
			d.processStu(data.getStuData());
		
			//Department details
			d.processDep(data.getDeptData());
		
			//Klasses details
			d.processKla(data.getKlassesData());
		
			//Professor details
			d.processProf(data.getProfData());
		
			//Subjects details
			d.processSub(data.getSubData());	
		
			return Response.status(201).entity("Successfully created school").build();
		}
		catch(MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
	}
}
