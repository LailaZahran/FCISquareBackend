package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class EnterLocation implements ICheckIn {
	
	String placeName;

	@Override
	public CheckIn checkin(String placeName , int UserId) {
		
try {
			
			
			Connection conn = DBConnection.getActiveConnection();
			String sql1 = "Select * from places where `name` = ?";
			PreparedStatement stmt1;
			stmt1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			stmt1.setString(1, placeName);
			
			stmt1.executeUpdate();
			ResultSet rs1 = stmt1.getGeneratedKeys();
			if (rs1.next()) {
				EnterLocation check=new EnterLocation();
				check.placeName=placeName;
			
			}
			else {
				//Call function save new place
				String sql = "Insert into places (`name`) VALUES  (?)";
				
				
			}
			
			
			String sql2 = "Insert into checkin (`userId`,`placeName`) VALUES  (?,?)";
			// System.out.println(sql);

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			stmt2.setInt(1, UserId);
			stmt2.setString(2, placeName);
			
			stmt2.executeUpdate();
			ResultSet rs = stmt2.getGeneratedKeys();
			
			if (rs.next()) {
				CheckIn check1=new CheckIn();
				check1.placeName=placeName;
				check1.userId=UserId;
				return check1;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		// TODO Auto-generated method stub
		
	}

	public String getplaceName() {
		// TODO Auto-generated method stub
		return placeName;
	}

}
