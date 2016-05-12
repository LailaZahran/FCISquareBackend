package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class homePages implements IHomePages  {
	
	public ArrayList<String> showHomePage(int userId)
	{
try {	
			
			Connection conn = DBConnection.getActiveConnection();
			String sql1 = "Select  `placeName` from `check-in` where `userId` = ?";
			PreparedStatement stmt1;
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setInt(1, userId);

			ResultSet rs1 = stmt1.executeQuery();
			ArrayList<String> A=new ArrayList<String>();
	        while(rs1.next()) {
				A.add(rs1.getString("placeName"));
	       		}
	       
	        String sql2 =" SELECT `followerID` FROM `followers` WHERE `followingID`=?";
	        PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1,  userId);
			ResultSet rs2 = stmt2.executeQuery();
            
			ArrayList<Integer> b =new ArrayList<Integer>();
	        followerFeeds(conn, A, rs2, b);
	        
	        for (int i=0;i<b.size();i++)
	        {
	        	
				String sql6 ="SELECT  `placeId` FROM `savedplaces` WHERE `userId`=?";
				PreparedStatement stmt6;
				stmt6 = conn.prepareStatement(sql6);
				stmt6.setInt(1, b.get(i));
				ResultSet rs6 = stmt6.executeQuery();
				while(rs6.next()) {
					String sql7 ="SELECT  `name` FROM  `places` WHERE `id`=?";
	
					PreparedStatement stmt7;
					stmt7 = conn.prepareStatement(sql7);
					stmt7.setInt(1, rs6.getInt("placeId"));
					ResultSet rs7 = stmt7.executeQuery();
					A.add(rs7.getString("placeName"));
		       	}
				
	        }
	        //dlw2ty hngeb el check-in ely el followers 3mleen 3leha comment
	        
	        
				return A;
	      
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}



	private void followerFeeds(Connection conn, ArrayList<String> A,
			ResultSet rs2, ArrayList<Integer> b) throws SQLException {
		while(rs2.next()) {
			b.add(rs2.getInt("followerID"));
			String sql3 = "Select  `placeName` from `check-in` where `userId` = ?";
			PreparedStatement stmt3;
			stmt3 = conn.prepareStatement(sql3);
			stmt3.setInt(1, rs2.getInt("followerID"));

			ResultSet rs3 = stmt3.executeQuery();
		    while(rs3.next()) {
				A.add(rs3.getString("placeName"));
		   		}
		    
		    String sql4 = "Select `checkinId`  from `comment` where `userId` = ?";
			PreparedStatement stmt4;
			stmt4 = conn.prepareStatement(sql4);
			stmt4.setInt(1, rs2.getInt("followerID"));
			ResultSet rs4 = stmt4.executeQuery();
		    while(rs4.next()) {
				//A.add(rs4.getString("placeName"));
		   		
			
			//bl check in id ely hagebo mn fo2 haro7 ageb el checkin nfso mn
			
			String sql5 = "Select  `placeName` from `check-in` where `id` = ?";
			PreparedStatement stmt5;
			stmt5 = conn.prepareStatement(sql5);
			stmt5.setInt(1, rs4.getInt("checkinId"));

			ResultSet rs5 = stmt5.executeQuery();
		    while(rs5.next()) {
				A.add(rs5.getString("placeName"));
		   		}
		    }
		    
		}
	}
	        
	       
	       
	   	
		
	
	
	
	public ArrayList<Integer> sort()
	{
		try {	
		Connection conn = DBConnection.getActiveConnection();
		//String str="SELECT `id` FROM `places` ORDER BY `numOfReq` ASC";
		String sql1 ="SELECT `typeId` FROM `homepage` WHERE `type`=1 AND `typeId`=  \"   SELECT `id` FROM `places` ORDER BY `numOfReq` ASC  \"     " ;
				//"SELECT `typeId` FROM `homepage` WHERE `type`=1 AND `typeId`="+"SELECT `id` FROM `places` ORDER BY `numOfReq` ASC" ;
	
		//
			
	//	\"ROM\" 
				
		PreparedStatement stmt1;
		stmt1 = conn.prepareStatement(sql1);
	//	stmt1.setInt(1, userId);

		ResultSet rs1 = stmt1.executeQuery();
		ArrayList<Integer> A=new ArrayList<Integer>();
        while(rs1.next()) {
			A.add(rs1.getInt("typeId"));
       		}
        return A;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     return null;
	}

}
