package com.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.UserAccount;
import com.mysql.jdbc.Statement;



public class CheckIn implements ICheckIn {
	
    public String placeName;
   // UserAccount user=new UserAccount();
   int userId;
//   UserAccount user =new UserAccount();
   
	
	public CheckIn checkin( String placeName , int UserId){
		CheckIn check=new CheckIn();
		return check;
		
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
