package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class commentRespond implements INotifications {

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
			n.commentId=rs1.getInt("typeId");
			}
			
			String sql2 = "select `checkinId` from `comment` where `commentId`=?";
			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1,n.commentId);
			
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
			

/*		

try {
			
			
			Connection conn = DBConnection.getActiveConnection();
			String sql1 = "SELECT `typeId` FROM `notificationlist` WHERE `user1Id`=?";
			PreparedStatement stmt1;
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, placeName);

			ResultSet rs1 = stmt1.executeQuery();
			EnterLocation check=new EnterLocation();
			
			if (rs1.next()) {
				
				check.placeName=placeName;
				System.out.println(placeName);
			}
			else {
				//Call function save new place
				String sql2 = "INSERT INTO `places` (`name`, `description`, `lat`, `long`) VALUES  (?,?,?,?)";

				PreparedStatement stmt2;
				stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
				stmt2.setString(1, placeName);
				stmt2.setString(2, "NewPlace");
				stmt2.setDouble(3, 1202);
				stmt2.setDouble(4, 23);
	            System.out.println("Enty hna?");			
				stmt2.executeUpdate();
				ResultSet rs = stmt2.getGeneratedKeys();
				
				if (rs.next()) {

		            System.out.println("Enty hna kman?");	
					Places p=new Places();
					p.name=placeName;
					check.placeName=placeName;
				}
			
			}
			
			
			String sql2 = "INSERT INTO `check-in`( `userId`, `placeName`) VALUES  (?,?)";     
			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			stmt2.setInt(1, UserId);
			stmt2.setString(2, placeName);
			
			stmt2.executeUpdate();
			ResultSet rs = stmt2.getGeneratedKeys();
			if (rs.next()) {
				EnterLocation check1=new EnterLocation();
				check1.placeName=placeName;
				check1.userId=UserId;
				//return check1;
				 System.out.println("hereee");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		// TODO Auto-generated method stub
		
*/
