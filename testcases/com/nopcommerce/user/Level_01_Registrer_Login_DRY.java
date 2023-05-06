package com.nopcommerce.user;

import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
import pageUIs.HomePageUI;

import org.testng.annotations.BeforeClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Registrer_Login_DRY {

	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String emailAddress;
	HomePageObject HomePageObject;
	RegisterPageObject RegisterPageObject;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		emailAddress = "Auto" + Gen_Num() + "@gmail.com";
		driver.get("https://demo.nopcommerce.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Register_With_Empty() {
		
		HomePageObject.clickToRegisterLink();
		RegisterPageObject.clickToButtonRegister();

		Assert.assertEquals(RegisterPageObject.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(RegisterPageObject.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(RegisterPageObject.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(RegisterPageObject.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(RegisterPageObject.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		HomePageObject.clickToRegisterLink();

		RegisterPageObject.inputToFirstNameTextbox("Automation");
		RegisterPageObject.inputToLastNameTextbox("FC");
		RegisterPageObject.inputToEmailTextbox("123@222#$%");
		RegisterPageObject.inputToPasswordTextbox("AutomationFC");
		RegisterPageObject.inputToConfirmPasswordTextbox("AutomationFC");

		Assert.assertEquals(RegisterPageObject.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		HomePageObject.clickToRegisterLink();

		RegisterPageObject.inputToFirstNameTextbox("Automation");
		RegisterPageObject.inputToLastNameTextbox("FC");
		RegisterPageObject.inputToEmailTextbox(emailAddress);
		RegisterPageObject.inputToPasswordTextbox("123456");
		RegisterPageObject.inputToConfirmPasswordTextbox("123456");
		
		RegisterPageObject.clickToButtonRegister();
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		HomePageObject.clickToRegisterLink();

		RegisterPageObject.inputToFirstNameTextbox("Auto");
		RegisterPageObject.inputToLastNameTextbox("FC");
		RegisterPageObject.inputToEmailTextbox(emailAddress);
		RegisterPageObject.inputToPasswordTextbox("123456");
		RegisterPageObject.inputToConfirmPasswordTextbox("123456");

		RegisterPageObject.clickToButtonRegister();
		Assert.assertEquals(RegisterPageObject.getErrorExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.id("FirstName")).sendKeys("Auto");
		driver.findElement(By.id("LastName")).sendKeys("FC");
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys("12345");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("12345");
		
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(), "Password must meet the following rules: must have at least 6 characters");

	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.id("FirstName")).sendKeys("Auto");
		driver.findElement(By.id("LastName")).sendKeys("FC");
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys("12345");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123");
		
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
	}

	public int Gen_Num() {
		Random rand=new Random();
		return rand.nextInt(9999);
	}
	@AfterClass
	public void afterClass() {

	}

}
