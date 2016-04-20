package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Likes {
	int checkinId;
	int userId;
	
	public Likes like(int checkinId, int userId){
		try {	
			
			Connection conn = DBConnection.getActiveConnection();
			String sql2 = "Insert into like (`checkinId`,`userId`) VALUES  (?,?)";

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
			
				return c;
			}

		
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
