package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.UserAccount;
import com.mysql.jdbc.Statement;

public class Comments {
	int checkinId;
	int userId;
	int commentId;
	String comment;
    
	/*
	 * Function comment responsible of adding a comment to 
	 * a certain check-in from a given id
	 */
	public Comments comment(int checkinId, String comment, int userId){
		
		try {	
			Connection conn = DBConnection.getActiveConnection();
			String sql2 = "Insert into `comment` (`checkinId`,`userId`,`comment`) VALUES  (?,?,?)";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			stmt2.setInt(1, checkinId);
			stmt2.setInt(2, userId);
			stmt2.setString(3, comment);
			
			stmt2.executeUpdate();
			ResultSet rs = stmt2.getGeneratedKeys();
			Comments c=new Comments();
			if (rs.next()) {
				
				c.commentId=rs.getInt(1);
				c.checkinId=checkinId;
				c.comment=comment;
				c.userId=userId;
			    commentId=c.commentId;
				//return c;
			}
			System.out.println("Comment Id: " + commentId);

		
			UserAccount u = getCheckinComment(checkinId, conn);
			
			//////////////////////////////////
			//ageb mn el checkin table, esm el user ely 3ml checkin, using checkin id..
			//ageb mn el comments table, el commentid & checkinid
			
			String sql3 = "Insert into `notificationlist` (`user1Id`,`user2Id`,`type`,`typeId` ) VALUES  (?,?,?,?)";
			
			
			ResultSet rs3 = getNotification(userId, conn, u, sql3);
			
			if (rs3.next()) {
				Notifications n=new Notifications();
				n.commentId=rs3.getInt(1);
				n.checkinId=checkinId;
				n.user1Id=userId;
			    
				return c;
			}
            String sql4 = "INSERT INTO `historyofactions`( `type`, `typeId`) VALUES  (?,?)";
			
			
			PreparedStatement stmt4;
			stmt4 = conn.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
			stmt4.setInt(1, 0);
			stmt4.setInt(2, commentId);
			
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
		stmt3.setInt(3,0);
		stmt3.setInt(4, commentId);
		
		stmt3.executeUpdate();
		ResultSet rs3 = stmt3.getGeneratedKeys();
		return rs3;
	}
	private UserAccount getCheckinComment(int checkinId, Connection conn)
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
	public String getcomment() {
		// TODO Auto-generated method stub
		return comment;
	}
	public int getuserId() {
		// TODO Auto-generated method stub
		return userId;
	}

}
