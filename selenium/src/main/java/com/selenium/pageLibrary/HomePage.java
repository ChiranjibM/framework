package com.selenium.pageLibrary;

import java.io.IOException;import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.testBase.TestBase;

public class HomePage extends TestBase {
	
	public void enterDetailsOneTrip() throws Exception
	{ 
		//init();
		//implicitWait(30);
		WebElement from = getWebElement("from");
		from.sendKeys(pp.getProperty("from1"));
		selectItem("Bangalore, IN - Kempegowda International Airport (BLR)","li");
		Thread.sleep(2000);
		WebElement to = getWebElement("to1");
		to.sendKeys(pp.getProperty("to1"));
		selectItem("Bhubaneswar, IN - Biju Patnaik (BBI)","li");
		WebElement calendar = getWebElement("calendar");	
		selectDate("30","td");		
		selectDropDown("selectAdults","1");
		WebElement search = getWebElement(pp.getProperty("searchButton"));	
	}
	
	public void enterDetailsroundTrip() throws Exception
	{
		//init();
		//implicitWait(30);
		WebElement roundTrip = getWebElement("roundTrip");
		roundTrip.click();
		implicitWait(30);
		WebElement from = getWebElement("from");
		from.sendKeys(pp.getProperty("from2"));
		selectItem("Mumbai, IN - Chatrapati Shivaji Airport (BOM)","li");
		Thread.sleep(2000);
		WebElement to = getWebElement("to2");
		to.sendKeys(pp.getProperty("to2"));
		selectItem("New Delhi, IN - Indira Gandhi Airport (DEL)","li");	
		WebElement calendar = getWebElement("calendar");	
		selectItem("30","td");
		WebElement calendar2 = getWebElement("calendar2");	
		selectItem("31","td");
	}
	/*public static void main(String[] args) throws Exception {
		{
			HomePage hp = new HomePage();
			hp.enterDetailsroundTrip();
		}
	}*/
}
