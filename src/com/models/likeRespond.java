package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class likeRespond implements INotifications {

	@Override
	public CheckIn respond(int notificationId,int type ) {
		// TODO Auto-generated method stub
		

try {
			
			
			Connection conn = DBConnection.getActiveConnection();
			String sql1 = "SELECT `typeId` FROM `notificationlist` WHERE `id`=?";
			PreparedStatement stmt1;
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setInt(1,notificationId );
			ResultSet rs1 = stmt1.executeQuery();
			Notifications n=new Notifications();
			
			if (rs1.next()) {
			n.likeId=rs1.getInt("typeId");
			}
			
			String sql2 = "SELECT `checkinId` FROM `like` WHERE `likeId`=?";
			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1,n.likeId);
			
			ResultSet rs2 = stmt2.executeQuery();
			
			
			if (rs2.next()) {
			n.checkinId=rs2.getInt("checkinId");
			}
			
			String sql3 = "SELECT * FROM `check-in` WHERE `id`=?";
			PreparedStatement stmt3;
			stmt3 = conn.prepareStatement(sql3);
			stmt3.setInt(1,n.checkinId);
			
			ResultSet rs3 = stmt3.executeQuery();
			CheckIn c = new CheckIn();
			
			if (rs3.next()) {
				c.placeName=rs3.getString("placeName");
				return c;
				
			}
			return null;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
		return null;
	}
	

	

}
