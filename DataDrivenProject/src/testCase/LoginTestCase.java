package testCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pOM.LoginPageObjects;


public class LoginTestCase {

	@Test
	public void login() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.phptravels.net/login");
		
		
		PageFactory.initElements(driver, LoginPageObjects.class);
		
		LoginPageObjects.username.sendKeys("user@phptravels.com");
		LoginPageObjects.password.sendKeys("demouser");
		LoginPageObjects.submit.click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
		
		/*
		 * LoginPageObjects.username(driver).sendKeys("user@phptravels.com");
		 * LoginPageObjects.password(driver).sendKeys("demouser");
		 * LoginPageObjects.loginButton(driver).click();
		 */
		/*WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("user@phptravels.com");
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("demouser");
		
		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\'fadein\']/div[1]/div/div[2]/div[2]/div/form/div[3]/button"));
		loginButton.click();*/
		
	}
}
