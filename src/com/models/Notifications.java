package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import com.mysql.jdbc.Statement;
import com.sun.jmx.snmp.Timestamp;


@Path("/ViewNotificationService")

public class Notifications implements INotifications {

    int commentId;
	int likeId;
	int checkinId;
	int user1Id;
	int user2Id;
	int type;
	
	public static ArrayList<Integer> GetNotifications(int UserID) {
		try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Select `user2Id`, `type`, `typeId` from `notificationlist` WHERE `user1Id` = ?";  //checkIn is a table 
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, UserID);
		
		ResultSet rs1 = stmt.executeQuery();
		EnterLocation check=new EnterLocation();
		
		if (rs1.next()) {
			
		Notifications I= new Notifications();
		I.user2Id=rs1.getInt("user2Id");
		I.type=rs1.getInt("type");
		
		}
		//	ArrayList<Integer> Notify = new ArrayList<>();
			
			  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void respond() {
		// TODO Auto-generated method stub
		
	}
}
 
