package com.nopcommerce.user;

import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_01_Register {

	private String projectPath = System.getProperty("user.dir");
	private WebDriver driver;
	private String emailAddress;
	private HomePageObject HomePage;
	private RegisterPageObject RegisterPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		HomePage= new HomePageObject(driver);
		RegisterPage= new RegisterPageObject(driver);
		emailAddress = "Auto" + Gen_Num() + "@gmail.com";
		driver.get("https://demo.nopcommerce.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Register_With_Empty() {
		
		HomePage.clickToRegisterLink();
		RegisterPage.clickToButtonRegister();

		Assert.assertEquals(RegisterPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(RegisterPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(RegisterPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(RegisterPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(RegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		HomePage.clickToRegisterLink();

		RegisterPage.inputToFirstNameTextbox("Automation");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox("123@222#$%");
		RegisterPage.inputToPasswordTextbox("AutomationFC");
		RegisterPage.inputToConfirmPasswordTextbox("AutomationFC");

		Assert.assertEquals(RegisterPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		HomePage.clickToRegisterLink();

		RegisterPage.inputToFirstNameTextbox("Automation");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox(emailAddress);
		RegisterPage.inputToPasswordTextbox("123456");
		RegisterPage.inputToConfirmPasswordTextbox("123456");
		
		RegisterPage.clickToButtonRegister();
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		RegisterPage.clickToContinueButton();
		HomePage.clickToRegisterLink();

		RegisterPage.inputToFirstNameTextbox("Auto");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox(emailAddress);
		RegisterPage.inputToPasswordTextbox("123456");
		RegisterPage.inputToConfirmPasswordTextbox("123456");

		RegisterPage.clickToButtonRegister();
		Assert.assertEquals(RegisterPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		HomePage.clickToRegisterLink();

		RegisterPage.inputToFirstNameTextbox("Auto");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox(emailAddress);
		RegisterPage.inputToPasswordTextbox("12345");
		RegisterPage.inputToConfirmPasswordTextbox("12345");
		
		RegisterPage.clickToButtonRegister();
		Assert.assertEquals(RegisterPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		HomePage.clickToRegisterLink();

		RegisterPage.inputToFirstNameTextbox("Auto");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox(emailAddress);
		RegisterPage.inputToPasswordTextbox("12345");
		RegisterPage.inputToConfirmPasswordTextbox("123");
		
		RegisterPage.clickToButtonRegister();
		Assert.assertEquals(RegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	public int Gen_Num() {
		Random rand=new Random();
		return rand.nextInt(9999);
	}
	@AfterClass
	public void afterClass() {

	}

}
