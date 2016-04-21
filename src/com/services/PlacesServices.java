package com.services;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.controller.UserAccount;
import com.models.CheckIn;
import com.models.EnterLocation;
import com.models.Places;

@Path("/")

public class PlacesServices {
	
	/*
	 * @GET
	 * 
	 * @Path("/addNewPlace")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response addNewPlace(){ return
	 * Response.ok(new Viewable("/addNewPlace.jsp")).build(); }
	 */
	
	
	@POST
	@Path("/addNewPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewPlace(@FormParam("name") String name,@FormParam("desc") String desc, 
	                @FormParam("lat") Double lat,@FormParam("lon") Double lon) {
	                          
		Places loc = new Places();
		loc.addNewPlace(name, desc,lat,lon);
		JSONObject json = new JSONObject();
		json.put("name", loc.getname());
		json.put("desc", loc.getdesc());
		json.put("lat", loc.getlat());
		json.put("lon", loc.getlon());
		return json.toJSONString();
	}
	
	/*
	 * @GET
	 * 
	 * @Path("/savePlace")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response savePlace(){ return
	 * Response.ok(new Viewable("/savePlace.jsp")).build(); }
	 */
	
	
	@POST
	@Path("/savePlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String savePlace(@FormParam("UserId") int UserId ,@FormParam("name") String name) {
	           System.out.println("heraaaaaaaaa");               
		Places loc = new Places();
		loc.SavePlace(UserId,name);
		JSONObject json = new JSONObject();
		json.put("UserId", loc.getid());
		json.put("name", loc.getname());
		
		return json.toJSONString();
	}
		 

}

