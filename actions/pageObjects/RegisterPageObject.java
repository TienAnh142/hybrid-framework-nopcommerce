package pageObjects;

import org.testng.annotations.Test;

import commons.BasePage;
import pageUIs.RegisterPageUI;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;


public class RegisterPageObject extends BasePage {

	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver=driver;
	}

	@BeforeClass
	public void beforeClass() {

	}

	@Test
	public void clickToButtonRegister() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
		
	}
	
	@Test
	public void clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_BUTTON);
		clickToElement(driver, RegisterPageUI.LOGOUT_BUTTON);
	}

	@Test
	public HomePageObject clickToContinueButton() {
		waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
		return  PageGeneratorManagement.getHomePage(driver);
	}
	
	@Test
	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendkeysToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	@Test
	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendkeysToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	@Test
	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	@Test
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	@Test
	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	@Test
	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	@Test
	public String getErrorMessageAtLastNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}

	@Test
	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Test
	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	@Test
	public String  getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}
	
	@Test
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	@Test
	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}
	
	
}