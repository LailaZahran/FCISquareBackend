package com.models;

	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.UserAccount;
import com.mysql.jdbc.Statement;

	public class UserPositionUpdate {

		private Double lat;
		private Double lon;

		/**
		 * 
		 * @param id
		 * @param lat
		 * @param lon
		 * @return
		 */
		public static boolean updateUserPosition(Integer id, Double lat, Double lon) {
			try{
				Connection conn = DBConnection.getActiveConnection();
				String sql = "Update users set `lat` = ? , `long` = ? where `id` = ?";
				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setDouble(1, lat);
				stmt.setDouble(2, lon);
				stmt.setInt(3, id);
				stmt.executeUpdate();
				return true;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return false;
		}
	////////////getFollowerLastPosition/////////////////////////
		/**
		 * 
		 * @param followerID
		 * @return
		 */
		public static UserAccount getFollowerPosition(Integer followerID) {
			try{
				Connection conn = DBConnection.getActiveConnection();
				
				String sql = "SELECT `lat`, `long` FROM `users` WHERE`id`=?";
				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, followerID);
			
				ResultSet rs =  stmt.executeQuery();
				if (rs.next()) {
					UserAccount user = new UserAccount();
					//user.id = rs.getInt(1);
					user.lat = rs.getDouble("lat");
					user.lon = rs.getDouble("long");
					System.out.println(user.lat);
					System.out.println(user.lon);
					return user;
				}
				
				
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			return null;
		}
	}

