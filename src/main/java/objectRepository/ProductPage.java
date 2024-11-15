package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	// Declaration
	@FindBy(id = "add-to-cart")
	private WebElement addToCartBtn;
	
	@FindBy(id = "shopping_cart_container")
	private WebElement cartContainerBtn;
	
	@FindBy(id = "remove")
	private WebElement removeBtn;
	
	// Initialization
	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public WebElement getAddToCartBtn() {
		return addToCartBtn;
	}

	public WebElement getCartContainerBtn() {
		return cartContainerBtn;
	}


	/**
	 * This method will click on add to cart button
	 */
	public void clickOnAddToCartButton()
	{
		addToCartBtn.click();
	}

	/**
	 * This method will click on cart container button 
	 */
	public void clickOnCartButton()
	{
		cartContainerBtn.click();
	}
	
	/**
	 * This method will click on remove button
	 */
	public void removeButton()
	{
		removeBtn.click();
	}
	
}