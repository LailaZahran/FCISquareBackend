package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.DBConnection;
import com.models.FollowActivities;
import com.models.UserActiviy;
import com.models.UserPositionUpdate;
import com.models.homePages;
import com.controller.UserAccount;

@Path("/")
public class Services {

	/**
	 * This class holds the services and connect it to the userModel functions
	 */
	
	
	/**
	 * 
	 * @param name
	 * @param email
	 * @param pass
	 * @return
	 */
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
		UserAccount user = new UserAccount();
		user.addNewUser(name, email, pass);
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
	/**
	 * 
	 * @param email
	 * @param pass
	 * @return
	 */
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserAccount user = new UserAccount();
		 user.login(email, pass);
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
		
	
	
	/////
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	@POST
	@Path("/followUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String followUser(@FormParam("followerid") String id1,
			@FormParam("followingid") String id2) {
		boolean check = FollowActivities.followUser(Integer.parseInt(id1), Integer.parseInt(id2));
		JSONObject json = new JSONObject();
		json.put("check", check ? 1 : 0);
		return json.toJSONString();
	}
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	@POST
	@Path("/unFollowUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String unFollowUser(@FormParam("followerid") String id1,
			@FormParam("followingid") String id2) {
		boolean check = FollowActivities.unFollowUser(Integer.parseInt(id1), Integer.parseInt(id2));
		JSONObject json = new JSONObject();
		json.put("check", check ? 1 : 0);
		return json.toJSONString();
	}
	
	////
	/**
	 * 
	 * @param id1
	 * @return
	 */
	@POST
	@Path("/getFollowers")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFollowers(@FormParam("id") int id1) {
		UserAccount  user= FollowActivities.getFollowers(id1);
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
	/**
	 * 
	 * @param id1
	 * @return
	 */
	@POST
	@Path("/getFollowerPosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFollowerPosition(@FormParam("id") int id1) {
       UserAccount user=UserPositionUpdate.getFollowerPosition(id1);	
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

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
	
	@POST
	@Path("/historyOfAction")
	@Produces(MediaType.TEXT_PLAIN)
	public String historyOfAction() {
		JSONArray actions=new JSONArray();
		UserActiviy U=new UserActiviy ();
		ArrayList<UserActiviy> L = new ArrayList<UserActiviy>();
		L=U.historyOfAction();
		for (int i=0;i<L.size();i++){
			JSONObject json = new JSONObject();
			json.put("type", L.get(i).type);
			if ( L.get(i).type==0){
				json.put("CommentId", L.get(i).typeId);
			}
			else if ( L.get(i).type==1){
				json.put("LikeId", L.get(i).typeId);
			}
			else
			{
				json.put("Check-inId", L.get(i).typeId);	
			}
			
			actions.add(json);
		}
			
		return actions.toString();
	}
}
