package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.UserAccount;
import com.mysql.jdbc.Statement;

public class Likes {
	int checkinId;
	int userId;
	int likeId;
	
	public Likes like(int checkinId, int userId){
		try {	
			
			Connection conn = DBConnection.getActiveConnection();
			String sql2 = "Insert into `like` (`checkinId`,`userId`) VALUES  (?,?)";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			stmt2.setInt(1, checkinId);
			stmt2.setInt(2, userId);
			
			stmt2.executeUpdate();
			ResultSet rs = stmt2.getGeneratedKeys();
			
			if (rs.next()) {
			    Likes c=new Likes();
				c.checkinId=checkinId;
				c.userId=userId;
				c.likeId=rs.getInt(1);
				likeId=c.likeId;
				//return c;
			}

		///////////////////
			
//////////////////////////////////////////////////////////
String sql1 = "SELECT  `userId` FROM `check-in` where `id` = ?";

PreparedStatement stmt1;
stmt1 = conn.prepareStatement(sql1);
stmt1.setInt(1, checkinId);

ResultSet rs1 = stmt1.executeQuery();
UserAccount u=new UserAccount();

if (rs1.next()) {

u.id=rs1.getInt(1);

}
System.out.println("User2 Id: " + u.id);

//////////////////////////////////
//ageb mn el checkin table, esm el user ely 3ml checkin, using checkin id..
//ageb mn el comments table, el commentid & checkinid

String sql3 = "Insert into `notificationlist` (`user1Id`,`user2Id`,`type`,`typeId` ) VALUES  (?,?,?,?)";


PreparedStatement stmt3;
stmt3 = conn.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
stmt3.setInt(1, userId);
stmt3.setInt(2, u.id);
stmt3.setInt(3, 1);
stmt3.setInt(4, likeId);

stmt3.executeUpdate();
ResultSet rs3 = stmt3.getGeneratedKeys();

if (rs3.next()) {
Notifications c=new Notifications();
c.likeId=rs3.getInt(1);
c.checkinId=checkinId;
c.user1Id=userId;

//	return c;
}
			////////////////////
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		// TODO Auto-generated method stub
		
	}

	public int getcheckinId() {
		// TODO Auto-generated method stub
		return checkinId;
	}

	public int getuserId() {
		// TODO Auto-generated method stub
		return userId;
	}
}
