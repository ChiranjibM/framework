package com.selenium.testScripts;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.pageLibrary.HomePage;
import com.selenium.testBase.TestBase;

public class TC01_EnterDetails extends TestBase {

	@BeforeClass
	public void setUp() throws IOException
	{
		init();
	}
	
	@Test
	public void entryDetails() throws Exception
	{
		HomePage hp = new HomePage();
		hp.enterDetailsroundTrip();
	}
	
	
}