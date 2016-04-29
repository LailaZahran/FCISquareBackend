package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class UserActiviy implements IUserActivity {
 
	public int type;
	public int typeId;

	public ArrayList<UserActiviy> historyOfAction()
	{
try {
			Connection conn = DBConnection.getActiveConnection();
			String sql1 = "SELECT * FROM `historyofactions` WHERE 1";
			PreparedStatement stmt1;
			stmt1 = conn.prepareStatement(sql1);
			//stmt1.setString(1, placeName);

			ResultSet rs1 = stmt1.executeQuery();
			EnterLocation check=new EnterLocation();
			ArrayList <UserActiviy> HU= new ArrayList <UserActiviy>();
			
			while (rs1.next()) {
				UserActiviy U=new UserActiviy();
				U.type=rs1.getInt("type");
				U.typeId=rs1.getInt("typeId");
				HU.add(U);
			}
			return HU;
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}
}
