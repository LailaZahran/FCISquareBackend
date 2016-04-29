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
import com.models.Notifications;
import com.models.UserActiviy;
import com.models.UserPositionUpdate;
import com.models.homePages;
import com.controller.UserAccount;

@Path("/")
public class homePagesServices {


	@POST
	@Path("/showHomePage")
	@Produces(MediaType.TEXT_PLAIN)
	public String showHomePage(@FormParam("userId") int userId) {
		JSONArray actions=new JSONArray();
		homePages U=new homePages ();
		
		ArrayList<String> L = new ArrayList<String>();
		L=U.showHomePage(userId);
		if(L==null)
		{return "error";
		
		}
		for (int i =0;i<L.size();i++){
			JSONObject json = new JSONObject();
			json.put("placeName", L.get(i));
			actions.add(json);
		}
			
		return actions.toString();
}

	
	@POST
	@Path("/sort")
	@Produces(MediaType.TEXT_PLAIN)
	public String sort() {
		JSONArray actions=new JSONArray();
	
		homePages U=new homePages ();
		ArrayList<Integer> L = new ArrayList<Integer>();
		L=U.sort();
		for (int i =0;i<L.size();i++){
			JSONObject json = new JSONObject();
			json.put("placeId", U.sort());
			actions.add(json);
		}
			
		return actions.toString();
}
	
}
