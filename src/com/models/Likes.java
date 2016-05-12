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
	
	/*
	 * Function like responsible of adding a like to 
	 * a certain check-in from a given id
	 */
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
				return c;
			}

		///////////////////
			
//////////////////////////////////////////////////////////
UserAccount u = getCheckin(checkinId, conn);

//////////////////////////////////
//ageb mn el checkin table, esm el user ely 3ml checkin, using checkin id..
//ageb mn el comments table, el commentid & checkinid

String sql3 = "Insert into `notificationlist` (`user1Id`,`user2Id`,`type`,`typeId` ) VALUES  (?,?,?,?)";


ResultSet rs3 = getNotification(userId, conn, u, sql3);
Notifications c=new Notifications();
if (rs3.next()) {

c.likeId=rs3.getInt(1);
c.checkinId=checkinId;
c.user1Id=userId;

//	return c;
}
			////////////////////
String sql4 = "INSERT INTO `historyofactions`( `type`, `typeId`) VALUES  (?,?)";


PreparedStatement stmt4;
stmt4 = conn.prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS);
stmt4.setInt(1, 1);
stmt4.setInt(2, c.likeId);

stmt4.executeUpdate();
ResultSet rs4 = stmt4.getGeneratedKeys();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		// TODO Auto-generated method stub
		
	}

	private ResultSet getNotification(int userId, Connection conn,
			UserAccount u, String sql3) throws SQLException {
		PreparedStatement stmt3;
		stmt3 = conn.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
		stmt3.setInt(1, userId);
		stmt3.setInt(2, u.id);
		stmt3.setInt(3, 1);
		stmt3.setInt(4, likeId);
		
		stmt3.executeUpdate();
		ResultSet rs3 = stmt3.getGeneratedKeys();
		return rs3;
	}

	private UserAccount getCheckin(int checkinId, Connection conn)
			throws SQLException {
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
		return u;
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
