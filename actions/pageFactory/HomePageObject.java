package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import commons.BasePageFactory;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePageFactory{

	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='ico-register']")
	private WebElement registerLink;
	
	@FindBy(xpath="//a[@class='ico-login']")
	private WebElement loginLink;
	
	@FindBy(xpath="//a[@class='ico-account']")
	private WebElement accountLink;
	
	@Test
	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver,registerLink);
	}
	
	@Test
	public void clickToLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(driver,loginLink);
	}

	public boolean isMyAccountDisplayed() {
		return isElementDisplayed(driver, accountLink);
		
	}
	
	
	

}
