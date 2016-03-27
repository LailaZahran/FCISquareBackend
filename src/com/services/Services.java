package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

import com.models.DBConnection;
import com.models.UserModel;

@Path("/")
public class Services {

	/*
	 * @GET
	 * 
	 * @Path("/signup")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response signUp(){ return
	 * Response.ok(new Viewable("/Signup.jsp")).build(); }
	 */

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		UserModel user = UserModel.addNewUser(name, email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
   /////////////////////////////////////////
	
	////////////////////////////////////////////
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		if(user==null)
		{
			return "error";
		}
		else{
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
		}
	}
		
	
	@POST
	@Path("/followUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String followUser(@FormParam("followerid") int id1,
			@FormParam("followingid") int id2) {
		boolean check = UserModel.followUser(id1, id2);
		if (check){
			return "Done";}
		else 
			return "error";
	}
	
	@POST
	@Path("/unFollowUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String unFollowUser(@FormParam("followerid") int id1,
			@FormParam("followingid") int id2) {
		boolean check = UserModel.unFollowUser(id1, id2);
		if (check){
			return "Done";}
		else 
			return "error";
	}
	
	@POST
	@Path("/getFollowers")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFollowers(@FormParam("id") int id1) {
		UserModel  user= UserModel.getFollowers(id1);
		//return check;
		if(user==null)
		{
			return "error";
		}
		else{
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		
		return json.toJSONString();
		}
		
	}
	
	@POST
	@Path("/getFollowerPosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFollowerPosition(@FormParam("id") int id1) {
       UserModel user=UserModel.getFollowerPosition(id1);	
       if(user==null)
		{
			return "error";
		}
		else{
		JSONObject json = new JSONObject();
		 
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		
		return json.toJSONString();
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
