package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Comments {
	int checkinId;
	int userId;
	String comment;
	/*
	 * 
CREATE TABLE IF NOT EXISTS `comment` (
  
  `checkinId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `comment` varchar(22) NOT NULL,
  
 
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

	 */
	public Comments comment(int checkinId, String comment, int userId){
		
		try {	
			Connection conn = DBConnection.getActiveConnection();
			String sql2 = "Insert into comment (`checkinId`,`userId`,`comment`) VALUES  (?,?,?)";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			stmt2.setInt(1, checkinId);
			stmt2.setInt(2, userId);
			stmt2.setString(3, comment);
			
			stmt2.executeUpdate();
			ResultSet rs = stmt2.getGeneratedKeys();
			
			if (rs.next()) {
				Comments c=new Comments();
				c.checkinId=checkinId;
				c.comment=comment;
				c.userId=userId;
			
				return c;
			}

			/*
			 *  `user1Id` int(11) NOT NULL,
			 *  `user2Id` int(11) NOT NULL,
			 *  `type` int(11) NOT NULL,
			 *  `typeId` int(11) NOT NULL,
			
			 
			String sql1 = "Insert into Notifications (`user1Id`,`user2Id`,`type`,`typeId` ) VALUES  (?,?,?,?)";
			
			//ageb mn el checkin table, esm el user ely 3ml checkin, using checkin id..
			//ageb mn el comments table, el commentid & checkinid
			
			PreparedStatement stmt1;
			stmt1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			stmt1.setInt(1, userId);
			stmt1.setInt(2, userId);
			stmt1.setString(3, comment);
			
			stmt1.executeUpdate();
			*/
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
	public String getcomment() {
		// TODO Auto-generated method stub
		return comment;
	}
	public int getuserId() {
		// TODO Auto-generated method stub
		return userId;
	}

}
