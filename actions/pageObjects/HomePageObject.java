package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.GetElementDisplayed;
import org.testng.annotations.Test;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	@Test
	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver,HomePageUI.REGISTER_LINK);
		return PageGeneratorManagement.getRegisterPage(driver);
	}
	
	@Test
	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver,HomePageUI.LOGIN_LINK);
		return PageGeneratorManagement.getLoginPage(driver);
	}

	public boolean isMyAccountDisplayed() {
		return isElementDisplayed(driver, HomePageUI.ACCOUNT_LINK);
		
	}
}
