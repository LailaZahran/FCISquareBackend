package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.UserAccount;
import com.mysql.jdbc.Statement;

public class Places {
	/*
	 * This class is responsible for functions that are related to
	 * places like add new place /save place
	 */
	
	String name;
	String desc;
	int placeId;
	public Double lat;
	public Double lon;
	
	//UserAccount user = new UserAccount();
	
	public Places addNewPlace(String name, String desc, double lat, double lon)
	{
		try {	
			/*
			 * //Call function save new place
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
			
			 */
			Connection conn = DBConnection.getActiveConnection();
			String sql2 = "INSERT INTO `places` (`name`, `description`, `lat`, `long`) VALUES  (?,?,?,?)";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			stmt2.setString(1, name);
			stmt2.setString(2, desc);
			stmt2.setDouble(3, lat);
			stmt2.setDouble(4, lon);
            System.out.println("Enty hna?");			
            stmt2.executeUpdate();
			ResultSet rs = stmt2.getGeneratedKeys();
			Places p=new Places();
			if (rs.next()) {

	            System.out.println("Enty hna kman?");	
				
				p.name=name;
				p.desc=desc;
				p.lon=lon;
				p.lat=lat;
				return p;
			}
			
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		// TODO Auto-generated method stub
	}
	
	
		public Places SavePlace(int UserID,String name) {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT `id` FROM `places` WHERE `name` = ?"; //get any saved place
	                    System.out.println("henaa?");
			PreparedStatement stmt, stmt1;
			try {
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, name);
				ResultSet rs = stmt.executeQuery();
				Places p=new Places();
				if (rs.next()) {
					int placeId=rs.getInt(1);
					String sql2 = "Insert into `savedplaces`(`userId`, `placeId`) VALUES  (?,?)"; //"userPlaces table"  save new place
                    
					stmt1 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
					stmt1.setInt(1,  UserID);
					stmt1.setInt(2, placeId);
					stmt1.executeUpdate();
					
					if (rs.next()) {

			            System.out.println("Enty hna kman?");	
						
						p.name=name;
						p.placeId=placeId;
						
					}return p;
					//return true;
				}
				return p;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}return null;
		}
	

	public String getname() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public String getdesc() {
		// TODO Auto-generated method stub
		return desc;
	}
	public int getid() {
		// TODO Auto-generated method stub
		return placeId;
	}
	public Double getlon() {
	
		return lon;
	}
	public Double getlat() {
		
		return lat;
	}

}
