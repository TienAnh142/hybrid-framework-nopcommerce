package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Test
	public void enterToUsernameTextbox() {
		sendkeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, "Auto");
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR);

	}

	public String getErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.MESSAGE_ERROR);
		return getElementText(driver, LoginPageUI.MESSAGE_ERROR);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public void verifyTitleHomePage() {
		getTitle(driver);
	}
	
	
}
