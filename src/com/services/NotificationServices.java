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

import com.models.CheckIn;
import com.models.DBConnection;
import com.models.FollowActivities;
import com.models.INotifications;
import com.models.Notifications;
import com.models.UserPositionUpdate;
import com.models.commentRespond;
import com.models.homePages;
import com.models.likeRespond;
import com.controller.UserAccount;

@Path("/")
public class NotificationServices {
	
	/*
	 * @GET
	 * 
	 * @Path("/respondNotification")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response respondNotification(){ return
	 * Response.ok(new Viewable("/respondNotification.jsp")).build(); }
	 */

	@POST
	@Path("/respondNotification")
	@Produces(MediaType.TEXT_PLAIN)
	public String respondNotification(@FormParam("notificationID") int notificationID,
			 @FormParam("type") int type) {
		INotifications n;
	if (type==0)
	{
		 n= new commentRespond();
	}
	else
	{
		 n= new likeRespond();
	}
	CheckIn c=new CheckIn();
	c=n.respond(notificationID, type);
	if(c==null)
	{
		return "error";
	}
	//	UserAccount user = new UserAccount();
		//user.addNewUser(name, email, pass);
		JSONObject json = new JSONObject();
		json.put("placeName",c.getplaceName() );
		return json.toJSONString();
	}

	@POST
	@Path("/GetNotifications")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetNotifications(@FormParam("UserID") int UserID) {
		//INotifications n;
      JSONArray actions=new JSONArray();
		ArrayList<Notifications> L = new ArrayList<Notifications>();
		Notifications N=new Notifications();
		L= N.GetNotifications(UserID);
		for (int i=0;i<L.size();i++){
			JSONObject json = new JSONObject();
			json.put("FollowerId", L.get(i).user2Id);
			
			json.put("type", L.get(i).type);
			actions.add(json);
		}
			
		return actions.toString();
	//	return json.toJSONString();
	}



}
