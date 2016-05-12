package com.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.UserAccount;
import com.mysql.jdbc.Statement;



public class CheckIn implements ICheckIn {
	/*
	 * This class is responsible for check-in function
	 * and this function is implemented with two strategies
	 * one is by entering the user's location and the other is by entering
	 * current location
	 */
    public String placeName;
   // UserAccount user=new UserAccount();
   int userId;
//   UserAccount user =new UserAccount();
   
	
	public String checkin( String placeName , int UserId){
		//CheckIn check=new CheckIn();
	   return null;
	}


	public String getplaceName() {
		// TODO Auto-generated method stub
		return placeName;
	}

	public int getuserId() {
		// TODO Auto-generated method stub
		return userId;
	}


}
