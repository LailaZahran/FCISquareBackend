package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.UserAccount;
import com.mysql.jdbc.Statement;

public class Places {
	
	String name;
	String desc;
	double lon;
	double lat;
	UserAccount user = new UserAccount();
	
	public Places addNewPlace(String name, String desc, double lon, double lat)
	{
		try {	
			Connection conn = DBConnection.getActiveConnection();
			String sql2 = "Insert into places (`name`,`description`,`lat`, `long`) VALUES  (?,?,?,?)";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			stmt2.setString(1, name);
			stmt2.setString(2, desc);
			stmt2.setDouble(3, lat);
			stmt2.setDouble(4, lon);
			
			stmt2.executeUpdate();
			ResultSet rs = stmt2.getGeneratedKeys();
			
			if (rs.next()) {
				Places p=new Places();
				p.name=name;
				p.desc=desc;
				p.lon=lon;
				p.lat=lat;
				return p;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		// TODO Auto-generated method stub
	}
	
	public void savePlace(int id,String name)
	{
		
	}

	public String getname() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public String getdesc() {
		// TODO Auto-generated method stub
		return desc;
	}
	public Double getlon() {
	
		return lon;
	}
	public Double getlat() {
		
		return lat;
	}

}
