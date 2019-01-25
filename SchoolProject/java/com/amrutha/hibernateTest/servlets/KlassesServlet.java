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

import com.amrutha.hibernateTest.daos.Klasses;
import com.amrutha.hibernateTest.dataProcessor.DataProcessor;
import com.amrutha.hibernateTest.exceptions.MyException;
import com.amrutha.hibernateTest.pojo.KlassPojo;
import com.amrutha.hibernateTest.pojo.ResponsePojo;

@Path("/klass")
public class KlassesServlet {

	// Inserting Classes with TEXT/PLAIN or APPLICATION/JSON
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response addClasses(String reqBody) throws Exception {

		ResponsePojo resp = new ResponsePojo();

		try {
			DataProcessor p = new DataProcessor();
			p.processKlasses(reqBody);
			return Response.status(201).entity("Successfully created classes").build();
		} catch (MyException e) {
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
	public Response addClasses(List<KlassPojo> body) throws Exception {

		ResponsePojo response = new ResponsePojo();
		DataProcessor datProcessor = new DataProcessor();

		try {
			datProcessor.processKla(body);
			return Response.status(201).entity("Successfully created classes").build();
		} catch (MyException e) {
			e.printStackTrace();
			response.setCode(e.getCode());
			response.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(response).build();
		}
	}

	// Retrieving Klasses with Http GET request
	@GET
	@Path("/get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKlasses(@PathParam("columnName") String name, @PathParam("columnValue") String value) {

		ResponsePojo resp = new ResponsePojo();
		Klasses klas = new Klasses();

		try {
			List<Klasses> klasses = new ArrayList<Klasses>();

			if (name.equals("id")) {
				klas.getKlaDetails(Integer.parseInt(value));
			} else if (name.equals("name")) {
				klas.getKlaDetails(value);
			}
			return Response.status(200).entity(klasses).build();
		} catch (MyException e) {
			e.printStackTrace();
			resp.setCode(e.getCode());
			resp.setMessage(e.getErrorMessage());
			return Response.status(e.getCode()).entity(resp).build();
		}
	}
}
