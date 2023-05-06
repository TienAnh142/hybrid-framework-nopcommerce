package com.nopcommerce.user;

import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import pageUIs.HomePageUI;

import org.testng.annotations.BeforeClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_02_Login {

	private String projectPath = System.getProperty("user.dir");
	private WebDriver driver;
	private String emailAddress, invalidEmailAddress, password, unregisteredEmailAddress, wrongPassword;
	private LoginPageObject LoginPage;
	private HomePageObject HomePage;
	private RegisterPageObject RegisterPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		LoginPage= new LoginPageObject(driver);
		HomePage= new HomePageObject(driver);
		RegisterPage= new RegisterPageObject(driver);
		emailAddress = "Auto" + Gen_Num() + "@gmail.com";
		unregisteredEmailAddress="autofc@gmail.com";
		invalidEmailAddress="auto@fc@gmail.com";
		wrongPassword="123457";
		password="123456";
		driver.get("https://demo.nopcommerce.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		HomePage.clickToRegisterLink();

		RegisterPage.inputToFirstNameTextbox("Automation");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox(emailAddress);
		RegisterPage.inputToPasswordTextbox(password);
		RegisterPage.inputToConfirmPasswordTextbox(password);
		
		RegisterPage.clickToButtonRegister();
		
	}

	@Test
	public void TC_01_Login_With_Empty() {
		HomePage.clickToLoginLink();
		LoginPage.clickToLoginButton();
		Assert.assertEquals(LoginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");	
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		HomePage.clickToLoginLink();
		LoginPage.inputToEmailTextbox(invalidEmailAddress);
		LoginPage.clickToLoginButton();
		Assert.assertEquals(LoginPage.getErrorMessageAtEmailTextbox(), "Wrong email");	
	}

	@Test
	public void TC_03_Login_With_Unregistered_Email() {
		HomePage.clickToLoginLink();
		LoginPage.inputToEmailTextbox(unregisteredEmailAddress);
		LoginPage.clickToLoginButton();
		Assert.assertEquals(LoginPage.getErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");	
	}

	@Test
	public void TC_04_Login_With_Registered_Email_Empty_Password() {
		HomePage.clickToLoginLink();
		LoginPage.inputToEmailTextbox(emailAddress);
		LoginPage.clickToLoginButton();
		Assert.assertEquals(LoginPage.getErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");	
	}

	@Test
	public void TC_05_Login_With_Registered_Email_Wrong_Password() {
		HomePage.clickToLoginLink();
		LoginPage.inputToEmailTextbox(emailAddress);
		LoginPage.inputToPasswordTextbox(wrongPassword);
		LoginPage.clickToLoginButton();
		Assert.assertEquals(LoginPage.getErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");	
	}

	@Test
	public void TC_06_Login_With_Registered_Email_Correct_Password() {
		HomePage.clickToLoginLink();
		LoginPage.inputToEmailTextbox(emailAddress);
		LoginPage.inputToPasswordTextbox(password);
		LoginPage.clickToLoginButton();
		Assert.assertTrue(HomePage.isMyAccountDisplayed());
	}

	public int Gen_Num() {
		Random rand=new Random();
		return rand.nextInt(9999);
	}
	@AfterClass
	public void afterClass() {

	}

}
