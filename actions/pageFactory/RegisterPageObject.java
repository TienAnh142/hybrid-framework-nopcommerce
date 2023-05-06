package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import commons.BasePageFactory;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory {

	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='ico-register']")
	private WebElement registerLink;
	
	@FindBy(xpath="//span[@id='FirstName-error']")
	private WebElement firstNameErrorMessage;
	
	@FindBy(xpath="//span[@id='LastName-error']")
	private WebElement lastNameErrorMessage;
	
	@FindBy(xpath="//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath="//span[@id='Password-error']")
	private WebElement passwordErrorMessage;
	
	@FindBy(xpath="//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMessage;
	
	@FindBy(xpath="//div[contains(@class,'message-error')]//li")
	private WebElement existingEmailErrorMessage;
	
	@FindBy(xpath="//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath="//input[@id='FirstName']")
	private WebElement firstNameTextbox;
	
	@FindBy(xpath="//input[@id='LastName']")
	private WebElement lastNameTextbox;
	
	@FindBy(xpath="//input[@id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(xpath="//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(xpath="//button[@id='register-button']")
	private WebElement registerButton;
	
	@FindBy(xpath="//a[contains(@class, 'register-continue-button')]")
	private WebElement continueButton;
	
	@FindBy(xpath="//a[@class='ico-logout']")
	private WebElement logoutButton;
	
	
	@Test
	public void clickToButtonRegister() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}
	
	@Test
	public void clickToLogoutLink() {
		waitForElementClickable(driver, logoutButton);
		clickToElement(driver, logoutButton);
	}

	@Test
	public void clickToContinueButton() {
		waitForElementClickable(driver, continueButton);
		clickToElement(driver, continueButton);
	}
	
	@Test
	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendkeysToElement(driver, firstNameTextbox, firstName);
	}

	@Test
	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendkeysToElement(driver, lastNameTextbox, lastName);
	}

	@Test
	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeysToElement(driver, emailTextbox, emailAddress);
	}

	@Test
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeysToElement(driver, passwordTextbox, password);
	}

	@Test
	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendkeysToElement(driver, confirmPasswordTextbox, confirmPassword);
	}

	@Test
	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	@Test
	public String getErrorMessageAtLastNameTextbox() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	@Test
	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	@Test
	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	@Test
	public String  getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}
	
	@Test
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	@Test
	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}
	
	
}
