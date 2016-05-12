package com.models;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FollowTest {
	FollowActivities follow=new FollowActivities();
  @Test
  public void testFollowUser() {
	  boolean test=follow.followUser(1,2);
	  Assert.assertEquals(test,true);
  }
  
  @Test
  public void testUnFollowUser() {
	  boolean test=follow.unFollowUser(1,2);
	  Assert.assertEquals(test,true);
  }
}
