package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManagement;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_06_Page_Generator_III_Register extends BaseTest {

	private String projectPath = System.getProperty("user.dir");
	private WebDriver driver;
	private String emailAddress;
	private HomePageObject HomePage;
	private RegisterPageObject RegisterPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver=getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		emailAddress = "Auto" + Gen_Num() + "@gmail.com";
	}

	@Test
	public void TC_01_Register_With_Empty() {

		HomePage=PageGeneratorManagement.getHomePage(driver);
		
		RegisterPage = HomePage.clickToRegisterLink();
		
		RegisterPage.clickToButtonRegister();
		

		Assert.assertEquals(RegisterPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(RegisterPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(RegisterPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(RegisterPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(RegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		RegisterPage = HomePage.clickToRegisterLink();

		RegisterPage.inputToFirstNameTextbox("Automation");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox("123@222#$%");
		RegisterPage.inputToPasswordTextbox("AutomationFC");
		RegisterPage.inputToConfirmPasswordTextbox("AutomationFC");

		Assert.assertEquals(RegisterPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		RegisterPage = HomePage.clickToRegisterLink();

		RegisterPage.inputToFirstNameTextbox("Automation");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox(emailAddress);
		RegisterPage.inputToPasswordTextbox("123456");
		RegisterPage.inputToConfirmPasswordTextbox("123456");
		
		RegisterPage.clickToButtonRegister();
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		HomePage=RegisterPage.clickToContinueButton();
		
		RegisterPage = HomePage.clickToRegisterLink();

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
		
		RegisterPage = HomePage.clickToRegisterLink();

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

		RegisterPage = HomePage.clickToRegisterLink();
		
		RegisterPage.inputToFirstNameTextbox("Auto");
		RegisterPage.inputToLastNameTextbox("FC");
		RegisterPage.inputToEmailTextbox(emailAddress);
		RegisterPage.inputToPasswordTextbox("12345");
		RegisterPage.inputToConfirmPasswordTextbox("123");
		
		RegisterPage.clickToButtonRegister();
		
		Assert.assertEquals(RegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {

	}

}
