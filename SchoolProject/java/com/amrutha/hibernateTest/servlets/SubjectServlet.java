package com.amrutha.hibernateTest.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amrutha.hibernateTest.daos.Department;
import com.amrutha.hibernateTest.daos.Subjects;
import com.amrutha.hibernateTest.dataProcessor.DataProcessor;
import com.amrutha.hibernateTest.exceptions.MyException;
import com.amrutha.hibernateTest.pojo.ResponsePojo;
import com.amrutha.hibernateTest.pojo.SubPojo;

@Path("/subjects")
public class SubjectServlet {

	// Inserting Subjects with TEXT/PLAIN or APPLICATION/JSON
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response AddSubject(String reqBody) throws Exception {

		ResponsePojo resp = new ResponsePojo();
		
		try {
			DataProcessor p = new DataProcessor();
			p.processStudents(reqBody);
			return Response.status(201).entity("Successfully created subject").build();
		} 
		catch(MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddSubject(List<SubPojo> body) throws Exception{
		
		ResponsePojo response = new ResponsePojo();
		DataProcessor datProcessor = new DataProcessor();
		
		try {
			datProcessor.processSub(body);
			return Response.status(201).entity("Successfully created subject").build();
		}
		catch(MyException e) {
			e.printStackTrace();
			response.setCode(e.getCode());
			response.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(response).build();
		}
	}

	// Retrieving Subjects with Http GET request
	@GET
	@Path("/get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeparment(@PathParam("columnName") String name, @PathParam("columnValue") String value) {

		ResponsePojo resp = new ResponsePojo();
		Subjects sub = new Subjects();

		try {
			List<Subjects> subjects = new ArrayList<Subjects>();

			if (name.equals("id")) {
				subjects = sub.getSubDetails(Integer.parseInt(value));
			} else if (name.equals("name")) {
				subjects = sub.getSubDetails(value);
			}
			return Response.status(200).entity(subjects).build();
		} catch (MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
	}
}
