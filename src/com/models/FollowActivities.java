package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.UserAccount;
import com.mysql.jdbc.Statement;

public class FollowActivities {
	
	private String name;
	private Integer id;
	private Double lat;
	private Double lon;
	/*
	 * This class is responsible for follow activities that 
	 * include follow/unfollow / getfollowers
	 * 
	 */
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	public static boolean followUser(int id1, int id2) {
		try {
			
			Connection conn = DBConnection.getActiveConnection();
			
			String sql= "INSERT INTO followers (`followerID`, `followingID`) VALUES(?,?)";
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();
		 
			//////////////////////////////////////////////////
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				userInstance(rs);
			}
			System.out.println(id1);
			System.out.println(id2);
				return true;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
////////////unFollow User/////////////////////////
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	public static boolean unFollowUser(int id1,int id2) {
		try {
			
			Connection conn = DBConnection.getActiveConnection();
			//Check if this statement is written correctly or not
			String sql = "DELETE FROM followers WHERE `followerID` = ? and `followingID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				userInstance(rs);
			}
			System.out.println(id1);
			System.out.println(id2);
		 
			return true;
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return false;
		}
	private static void userInstance(ResultSet rs) throws SQLException {
		UserAccount user = new UserAccount();
		UserAccount user2 = new UserAccount();
		user.id = rs.getInt(1);
		user2.id=rs.getInt(2);
	}
	
////////////get Followers/////////////////////////
/**
* 
* @param id1
* @return
*/
public static UserAccount getFollowers(int id1) {
String name;
try {
	Connection conn = DBConnection.getActiveConnection();
	//Check if this statement is true or not
	String sql =" SELECT `followerID` FROM `followers` WHERE `followingID`=?";
	
	PreparedStatement stmt;
	stmt = conn.prepareStatement(sql);
	stmt.setInt(1, id1);
	
	ResultSet rs = stmt.executeQuery();
	
	if (rs.next()) {
		
		UserAccount user = new UserAccount();
		user.id = rs.getInt("followerID");
		
		name=fromIDtoName(user.id);
		System.out.println(name);
		user.name = name;
	   return user;	
   }


} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;
}


//////////// ID to Name/////////////////////////
/**
 * 
 * @param id
 * @return
 */
public static String fromIDtoName(int id)
{
	try{
	Connection conn = DBConnection.getActiveConnection();
	
	String sql = "Select name from users where `id` = ?";
	
	PreparedStatement stmt;
	stmt = conn.prepareStatement(sql);
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	
	if (rs.next()) {
		UserAccount user = new UserAccount();
		
		user.name = rs.getString("name");
		return user.name;
	}
	return null;
	
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}return null;

}


}
