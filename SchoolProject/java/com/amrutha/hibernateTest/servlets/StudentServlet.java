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
import com.amrutha.hibernateTest.daos.Student;
import com.amrutha.hibernateTest.dataProcessor.DataProcessor;
import com.amrutha.hibernateTest.exceptions.MyException;
import com.amrutha.hibernateTest.pojo.ResponsePojo;
import com.amrutha.hibernateTest.pojo.StuPojo;

@Path("/student")
public class StudentServlet {

	// Inserting Student details with TEXT/PLAIN or APPLICATION/JSON
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response AddStudent(String reqBody) throws Exception{

		ResponsePojo resp = new ResponsePojo();
		
		try {
			DataProcessor p = new DataProcessor();
			p.processStudents(reqBody);
			return Response.status(201).entity("Successfully created student").build();
		} 
		catch(MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
		catch(Exception e) {
			e.printStackTrace();			
			return Response.status(500).build();
		}
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddStudent(List<StuPojo> body) throws Exception{
		
		ResponsePojo response = new ResponsePojo();
		DataProcessor datProcessor = new DataProcessor();
		
		try {
			datProcessor.processStu(body);
			return Response.status(201).entity("Successfully created student").build();
		}
		catch(MyException e) {
			e.printStackTrace();
			response.setCode(e.getCode());
			response.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(response).build();
		}
	}

	// Retrieving Students with Http GET request
	@GET
	@Path("/get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@PathParam("columnName") String name, @PathParam("columnValue") String value) {

		ResponsePojo resp = new ResponsePojo();
		Student stud = new Student();

		try {
			List<Student> students = new ArrayList<Student>();

			if (name.equals("id")) {
				students = stud.getStuDetails(Integer.parseInt(value));
			} else if (name.equals("name")) {
				students = stud.getStuDetails(value);
			}
			return Response.status(200).entity(students).build();
		} catch (MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
	}
}
