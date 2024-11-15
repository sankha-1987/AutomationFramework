package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	// Declaration
	@FindBy(className = "inventory_item_name")
	private WebElement productnameLnk;
	
	// Initialization
	public CartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
	// Utilization
	public WebElement getProductnameLnk() {
		return productnameLnk;
	}
	
	// Business Library 
	/**
	 * This method will capture the product title in cart and return to caller
	 * @return
	 */
	public String getProductTitle()
	{
		return productnameLnk.getText();
	}

}