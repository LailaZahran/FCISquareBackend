package com.services;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.json.simple.JSONObject;
import org.testng.Assert;


public class ServicesTest {
  Services S= new Services();
  @Test
  public void getFollowers() {
	   String result=S.getFollowers(3);
	   JSONObject json = new JSONObject();
		json.put("id", 7);
		json.put("name","farida");
		String res=json.toJSONString();
	   AssertJUnit.assertEquals(result, res);
    
  }
  
 /* @Test(dependsOnMethods = { "getFollowers" })
  public void testSalutationMessage() {
     System.out.println("Inside testSalutationMessage()");
     message = "Hi!" + "Manisha";
     Assert.assertEquals(message, messageUtil.salutationMessage());
  }*/
}
