package com.selenium.testBase;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class TestBase
{
	//public static final Logger logger =logger.g
	public static WebDriver driver;
	public Properties pp;
	public File f1;
	public FileInputStream ff;
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	
	public void init() throws IOException
	{
		loadpropertiesFile();
		getBrowser(pp.getProperty("browser"));
		implicitWait(30);
		driver.get(pp.getProperty("url"));
		
	}
	
	public void selectItem(String item, String tag)
	{
		
		List<WebElement> l1 = driver.findElements(By.tagName(tag));
		System.out.println(l1.size());
		for(WebElement e1:l1)
		{
			if(e1.getText().equals(item))
			{
				System.out.println(e1.getText());
				e1.click();
			}
		}
	}
	
	public void selectDate(String item, String tg) throws Exception
	{	WebElement selectdates = getWebElement("calendarDates");
		List<WebElement> l1 = selectdates.findElements(By.tagName("tg"));
		System.out.println(l1.size());
		for(WebElement e1:l1)
		{
			if(e1.getText().equals(item))
			{
				System.out.println(e1.getText());
				e1.click();
			}
		}
	}
	public void selectDropDown(String dropdown, String dropdownValue) throws Exception
	{
		Select item = new Select(getWebElement(pp.getProperty(dropdown)));
		item.selectByValue(dropdownValue);
	}
	public void getBrowser(String browser)
	{
		
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.out.println(System.getProperty("user.dir"));
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				
			
			}
			if (browser.equalsIgnoreCase("ie"))
			{
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\java\\com\\selenium\\drivers\\IEDriverServer.exe");
				//System.setProperty("webdriver.ie.driver", "user.dir"+"\\src\\main\\java\\com\\selenium\\drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get("https://www.google.com");
				driver.findElement(By.xpath("googleSearch"));
			}
		}
	public void getPropetiesData()
	{
		
	}
	public void loadpropertiesFile() throws IOException
	{
		String log4jconfig ="log4j.properties";
		PropertyConfigurator.configure(log4jconfig);
		pp = new Properties();
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/selenium/config/config.properties");
		ff = new FileInputStream(f1);
		pp.load(ff);
		logger.info("Loading config.properties");
		
	
	
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/selenium/config/OR.properties");
		ff = new FileInputStream(f1);
		pp.load(ff);
		logger.info("Loading OR.properties");
		
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/selenium/properties/home.properties");
		ff = new FileInputStream(f1);
		pp.load(ff);
		logger.info("Loading home.properties");
	}
	public String getScreenShot() throws IOException
	{
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imageLocation = System.getProperty("user.dir")+"/selenium/src/main/java/com/selenium/screenshot/";
		String actualImageName = System.currentTimeMillis() + ".png";
		File destinationFile = new File(actualImageName);
	    FileUtils.copyFile(image, destinationFile);
	    return actualImageName;
	}
	
	public WebElement waitForElement(WebDriver driver,long time, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));		
	}
	public void implicitWait(long time)
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	
	public static WebElement getLocator(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public static List<WebElement> getLocators(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
	
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public WebElement getWebElement(String locator) throws Exception{
		return getLocator(pp.getProperty(locator));
	}
	
	public List<WebElement> getWebElements(String locator) throws Exception{
		return getLocators(pp.getProperty(locator));
	}
	
	public void closeBrowser(){
		driver.quit();
	}
	public String[][] readExcelFile() throws IOException
	{
		try{
			String dataSets[][]=null;
		
		 String excelFilePath = System.getProperty("user.dir")+"/src/main/java/com/selenium/excelReader/testExcel.xlsx";
	        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	        logger.info("Reading excel data");
	         
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        Sheet firstSheet = workbook.getSheetAt(0);
	        int totalRow = firstSheet.getLastRowNum()+1;
	        int totalColumn = firstSheet.getRow(0).getLastCellNum();	
	       dataSets = new String [totalRow -1][totalColumn];
	        Iterator<Row> iterator = firstSheet.iterator();
	        int i=0;
            int t=0;
	         
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            if(i++ !=0)
                { 
                	int k=t;
                	t++;
                Iterator<Cell> cellIterator = nextRow.cellIterator();
	             
	            while (cellIterator.hasNext()) {
	            	int j=0;
	                Cell cell = cellIterator.next();
	                
	                
	                 
	                switch (cell.getCellType()) {
	                    case Cell.CELL_TYPE_STRING:
	                    	dataSets[k][j++] = cell.getStringCellValue();
	                        System.out.print(cell.getStringCellValue());
	                        break;
	                        
	                    case Cell.CELL_TYPE_BOOLEAN:
	                    	dataSets[k][j++] = cell.getStringCellValue();
	                    	System.out.print(cell.getStringCellValue());
	                        
	                       break;
	                    case Cell.CELL_TYPE_NUMERIC:
	                      	dataSets[k][j++] = cell.getStringCellValue();
	                    	System.out.print(cell.getStringCellValue());
	                }
	                System.out.print(" ");
	                
	                
	            }
	            System.out.println();
	            
	        }
		}
	        inputStream.close();
	        return dataSets;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
		
			
	    }
	
	
	
	/*public static void main(String[] args) throws Exception {
		TestBase t1 = new TestBase();
		t1.readExcelFile();
		//t1.getBrowser("ie");
		//t1.loadpropertiesFile();
		//System.out.println(t1.pp.getProperty("url"));
		//System.out.println(t1.pp.getProperty("testname"));
		//t1.getWebElement("from");
		//t1.getBrowser("firefox");
		//Thread.sleep(1000);
		
	}*/
}
