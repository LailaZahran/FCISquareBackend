package com.services;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.models.CheckIn;
import com.models.Comments;
import com.models.DBConnection;
import com.models.EnterLocation;
import com.models.FollowActivities;
import com.models.Likes;
import com.models.UserPositionUpdate;
import com.controller.UserAccount;

@Path("/")

public class CheckInServices {
	
	/*
	 * @GET
	 * 
	 * @Path("/checkin")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response checkin(){ return
	 * Response.ok(new Viewable("/checkin.jsp")).build(); }
	 */
     
	@POST
	@Path("/checkin")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkin(@FormParam("placeName") String placeName, @FormParam("UserId") int UserId) {
		EnterLocation loc = new EnterLocation();
		loc.checkin(placeName, UserId);
		JSONObject json = new JSONObject();
		json.put("placeName", loc.getplaceName());
		json.put("userId", loc.getuserId());
		return json.toJSONString();
	}
	
	
	/*
	 * @GET
	 * 
	 * @Path("/comment")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response comment(){ return
	 * Response.ok(new Viewable("/comment.jsp")).build(); }
	 */
	
	@POST
	@Path("/comment")
	@Produces(MediaType.TEXT_PLAIN)
	public String comment(@FormParam("checkinId") int checkinId, @FormParam("comment") String comment, @FormParam("userId") int userId) {
		Comments c = new Comments();
		c.comment(checkinId,comment, userId);
		JSONObject json = new JSONObject();
		json.put("checkinId", c.getcheckinId());
		json.put("comment", c.getcomment());
		json.put("userId", c.getuserId());
		return json.toJSONString();
	}
	
	/*
	 * @GET
	 * 
	 * @Path("/like")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response like(){ return
	 * Response.ok(new Viewable("/like.jsp")).build(); }
	 */
	
	@POST
	@Path("/like")
	@Produces(MediaType.TEXT_PLAIN)
	public String comment(@FormParam("checkinId") int checkinId, @FormParam("userId") int userId) {
		Likes c = new Likes();
		c.like(checkinId, userId);
		JSONObject json = new JSONObject();
		json.put("checkinId", c.getcheckinId());
		json.put("userId", c.getuserId());
		return json.toJSONString();
	}
}
