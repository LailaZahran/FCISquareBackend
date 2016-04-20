package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.DBConnection;
import com.mysql.jdbc.Statement;

public interface IUserAccount {
	
	public UserAccount addNewUser(String name, String email, String pass);
	public UserAccount login(String email, String pass);
	
	

}
