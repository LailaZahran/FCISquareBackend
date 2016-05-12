package com.models;

import org.testng.Assert;
import org.testng.annotations.Test;

public class placesTest {
	
	Places place=new Places ();
	Places place2=new Places ();

	
  @Test
  public void  addNewPlace() {
	  place.name="giza";
	  place.desc="nice";
	  place.lat=25.5;
	  place.lon=22.9;
	  place2=place.addNewPlace("giza", "nice", 25.5, 22.9);
	  Assert.assertEquals(place2,place);
	  
  }
}
