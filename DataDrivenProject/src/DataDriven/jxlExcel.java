package DataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class jxlExcel {

	String[][] data= null;
	
	

	@DataProvider(name = "loginData")
	public String[][] loginDataProvider() throws BiffException, IOException, RowsExceededException, WriteException {
		
		data = exceldata();
	   return data;	
	}

	public String[][] exceldata() throws BiffException, IOException {
		
		FileInputStream excel = new FileInputStream("C:\\Selenium\\Logindetails.xls");
		
	    Workbook wb = Workbook.getWorkbook(excel);
	    
	    Sheet sheet = wb.getSheet("Sheet1");
	    
	    int rowCount = sheet.getRows();
	    int ColumnCount = sheet.getColumns();
	    
	    String testData[][] = new String[rowCount-1][ColumnCount];
	    
	    for(int i=1; i<rowCount; i++) {
		  for(int j=0; j<ColumnCount; j++){
			  
			  testData[i-1][j] =sheet.getCell(j, i).getContents();
			 
		  }
	    }
		return testData;
	}
	
	
    @Test(dataProvider="loginData")	
    public void loginwithincorrectusername(String uname, String pword) throws RowsExceededException, WriteException {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
     
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);
		
		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(pword);
		
		WebElement loginbutton = driver.findElement(By.id("btnLogin"));
		loginbutton.click();
		
		driver.quit();
		
		}
}
