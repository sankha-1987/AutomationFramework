package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllProductsPage {  // Rule 1 : create separate POM class for web page

	// Rule 2 : Identify the web elements using annotations	
	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuBtn;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutLnk;

	// Rule 3 : Create a constructor and initialize the Web Elements	
	public AllProductsPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	// Rule 4 : Provide getters to access the Web Elements
	public WebElement getMenuBtn() {
		return menuBtn;
	}

	public WebElement getLogoutBtn() {
		return logoutLnk;
	}
	
	// Business Library - operation
	
	/**
	 * This method will click on Menu Button
	 */
	public void clickOnMenuBtn()
	{
		menuBtn.click();
	}
	
	/**
	 * This method will perform logout operation
	 */
	public void logoutOfApp()
	{
		menuBtn.click();
		logoutLnk.click();
	}
	
	
	/**
	 * This method will click on particular product and return the product title to caller
	 * @param driver
	 * @param PRODUCTNAME
	 * @return
	 */
	public String clickOnProductName(WebDriver driver, String PRODUCTNAME)
	{
		String productTitle = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).getText();
		driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();
		return productTitle;	// For Validation
	}
	
	
	
	
}