package com.models;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckInTest {
	
	 EnterLocation Location =new EnterLocation ();
	 
	@Test
  public void checkin() {
		String test=Location.checkin("cairo", 5);
		Assert.assertEquals(test,"Done");
		
	  
  }
}
