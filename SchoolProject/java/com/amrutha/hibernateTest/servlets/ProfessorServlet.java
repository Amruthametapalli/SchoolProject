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
import com.amrutha.hibernateTest.daos.Professor;
import com.amrutha.hibernateTest.dataProcessor.DataProcessor;
import com.amrutha.hibernateTest.exceptions.MyException;
import com.amrutha.hibernateTest.pojo.ProfPojo;
import com.amrutha.hibernateTest.pojo.ResponsePojo;

@Path("/professor")
public class ProfessorServlet {
	
	//Inserting professors with TEXT/PLAIN or APPLICATION/JSON
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response AddProfessor(String reqBody) throws Exception {
		
		ResponsePojo resp = new ResponsePojo();
		
		try {
			DataProcessor p = new DataProcessor();
			p.processProfessor(reqBody);
			return Response.status(201).entity("Successfully created professor").build();
		}
		catch (MyException e) {
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
	public Response AddProfessor(List<ProfPojo> body) throws Exception{
		
		ResponsePojo response = new ResponsePojo();
		DataProcessor datProcessor = new DataProcessor();

		try {
			datProcessor.processProf(body);
			return Response.status(201).entity("Successfully created professor").build();
		}
		catch(MyException e) {
			e.printStackTrace();
			response.setCode(e.getCode());
			response.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(response).build();
		}
	}

	// Retrieving Professors with Http GET request
	@GET
	@Path("/get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfessor(@PathParam("columnName") String name, @PathParam("columnValue") String value) {

		ResponsePojo resp = new ResponsePojo();
		Professor prof = new Professor();

		try {
			List<Professor> professors = new ArrayList<Professor>();

			if (name.equals("id")) {
				professors = prof.getProfDetails(Integer.parseInt(value));
			} else if (name.equals("name")) {
				professors = prof.getProfDetails(value);
			}
			return Response.status(200).entity(professors).build();
		} catch (MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
	}
}
