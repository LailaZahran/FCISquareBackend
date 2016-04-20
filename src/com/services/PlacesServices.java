package com.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.CheckIn;
import com.models.Places;

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
	@Path("/checkin")
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewPlace(@FormParam("name") String name, @FormParam("desc") String desc ,
			@FormParam("lon") double lon, @FormParam("lat") double lat) {
	    Places loc = new Places();
		loc.addNewPlace(name, desc,lon,lat);
		JSONObject json = new JSONObject();
		json.put("name", loc.getname());
		json.put("desc", loc.getdesc());
		json.put("lon",  loc.getlon());
		json.put("lat", loc.getlat());
		return json.toJSONString();
	}
}

