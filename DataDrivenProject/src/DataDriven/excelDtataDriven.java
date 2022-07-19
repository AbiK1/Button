package DataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class excelDtataDriven {

	public  String Testcase;
	String[][] data= null;
	public WritableWorkbook workbook1;
	public WritableSheet sheet1;
	public String Title;
	public int i;
	
	
	
	
	@DataProvider(name = "loginData")
	public String[][] loginDataProvider() throws BiffException, IOException, RowsExceededException, WriteException {
		
		data = exceldata();
	   return data;	
	}

	public String[][] exceldata() throws BiffException, IOException, RowsExceededException, WriteException {
		
		FileInputStream excel = new FileInputStream("C:\\Selenium\\Logindetails.xls");
		
	    Workbook wb = Workbook.getWorkbook(excel);
	    
	    Sheet sheet = wb.getSheet("Sheet1");
	    
	    int rowCount = sheet.getRows();
	    int ColumnCount = sheet.getColumns();
	    
	    String testData[][] = new String[rowCount-1][ColumnCount];
	    
	    FileOutputStream excel1 = new FileOutputStream("C:\\Selenium\\LogindetailsResults.xls");
	    System.out.println("creating file one");
	     
	     workbook1 = Workbook.createWorkbook(excel1);
	     System.out.println("creating file 2");
	     
	      sheet1= workbook1.createSheet("Sheet1", 0);
	      System.out.println("creating file 3");
	      
	  for( i=1; i<rowCount; i++) {
		  for(int j=0; j<ColumnCount; j++){
			  
			  testData[i-1][j] =sheet.getCell(j, i).getContents();
			  Label l = new Label(j, i, testData[i-1][j]); 
			  Label l2 = new Label(2,0,"Results");
			  sheet1.addCell(l);
			  sheet1.addCell(l2);
			  if(Title.equalsIgnoreCase("OrangeHRM")) {
					
					Testcase="PASS";
					}else{
						Testcase="FAIL";	
				}
			  Label l3=new Label(2,i,Testcase);
			   sheet1.addCell(l3);
			  
			  
		  }
		}
	return testData;
	
	}
	
	
    @Test(dataProvider="loginData")	
    public void loginwithincorrectusername(String uname, String pword) throws RowsExceededException, WriteException, BiffException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		 Title = driver.getTitle();
		System.out.println(Title);
		

     
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);
		
		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(pword);
		
		WebElement loginbutton = driver.findElement(By.id("btnLogin"));
		loginbutton.click();
		
		driver.quit();
		
		}
    
    
    @AfterTest
    public void Writeexcel() {
    	try {
    		workbook1.write();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    
    
    try {
    	workbook1.close();
    	}catch (WriteException e) { 
    		e.printStackTrace(); 
    	} catch (IOException e) { 
    	e.printStackTrace();
    	}
    }
    
}

