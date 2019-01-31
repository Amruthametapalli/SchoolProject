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
import com.amrutha.hibernateTest.dataProcessor.DataProcessor;
import com.amrutha.hibernateTest.exceptions.MyException;
import com.amrutha.hibernateTest.pojo.DeptPojo;
import com.amrutha.hibernateTest.pojo.ResponsePojo;

@Path("/department")
public class DepartmentServlet {

	// Inserting Departments with TEXT/PLAIN or APPLICATION/JSON with Http POST
	// request
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response AddDepartment(String reqBody) throws Exception {

		ResponsePojo resp = new ResponsePojo();

		try {
			DataProcessor p = new DataProcessor();
			p.processDepartment(reqBody);
			return Response.status(201).entity("Successfully created department").build();
		} catch (MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddDepartment(List<DeptPojo> body) throws Exception {

		ResponsePojo response = new ResponsePojo();
		DataProcessor datProcess = new DataProcessor();

		try {
			datProcess.processDep(body);
			return Response.status(201).entity("Successfully created department").build();
		} catch (MyException e) {
			e.printStackTrace();
			response.setCode(e.getCode());
			response.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(response).build();
		}
	}

	// Retrieving Departments with Http GET request
	@GET
	@Path("/get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeparment(@PathParam("columnName") String name, @PathParam("columnValue") String value) {

		ResponsePojo resp = new ResponsePojo();
		Department dep = new Department();

		try {
			List<Department> departments = new ArrayList<Department>();

			if (name.equals("id")) {
				departments = dep.getDeptDetails(Integer.parseInt(value));
			} else if (name.equals("name")) {
				departments = dep.getDeptDetails(value);
			}
			return Response.status(200).entity(departments).build();
		} catch (MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
	}
}
