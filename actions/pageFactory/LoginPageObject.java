package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import commons.BasePageFactory;


public class LoginPageObject extends BasePageFactory {

	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(xpath="//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath="//button[contains(@class, 'login-button')]")
	private WebElement loginButton;

	@FindBy(xpath="//span[@id='Email-error']")
	private WebElement emailError;
	
	@FindBy(xpath="//div[contains(@class,'message-error')]")
	private WebElement messageError;
	
	@Test
	public void enterToUsernameTextbox() {
		sendkeysToElement(driver, emailTextbox, "Auto");
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeysToElement(driver, emailTextbox, emailAddress);
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailError);
		return getElementText(driver, emailError);

	}

	public String getErrorMessage() {
		waitForElementVisible(driver,messageError);
		return getElementText(driver, messageError);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeysToElement(driver, passwordTextbox, password);
	}

	public void verifyTitleHomePage() {
		getTitle(driver);
	}
}
