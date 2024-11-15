package products;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericUtilities.BaseClass;
import objectRepository.AllProductsPage;
import objectRepository.CartPage;
import objectRepository.ProductPage;

@Listeners(genericUtilities.ListenersImplementation.class)
/* Test Class */  
public class AddProductToCartTest extends BaseClass{ 

	
	@Test(groups="Smoke")  /* Test Method or Test Script */
	public void tc_001_AddSingleProductToCartTest() throws IOException, InterruptedException {
			
	    // Read the Test Data from Excel file
	String PRODUCTNAME = eUtil.readDataFromExcel("Products", 1, 2);
	System.out.println("product name : " + PRODUCTNAME);
		
		// step 4 : click on a product
	Thread.sleep(1000);
	AllProductsPage app = new AllProductsPage(driver);
	String productTitle = app.clickOnProductName(driver, PRODUCTNAME);
	System.out.println("product title : " + productTitle);
	
		// step 5 : add product to cart
	Thread.sleep(1000);
	ProductPage pp = new ProductPage(driver);
	pp.clickOnAddToCartButton();

//	Assert.fail();		// Fail the Test Script
	
		// step 6 : navigate to cart and validate the product
	pp.clickOnCartButton();	
	
		// Step 7 : Capture product details in Cart 
	CartPage cp = new CartPage(driver); 
	String productTitleInCart = cp.getProductTitle(); 

	    // Step 8 : validate for product name
	Assert.assertEquals(productTitleInCart, productTitle);
	
	Assert.assertTrue(productTitleInCart.equals(productTitle));	
	
	Assert.assertTrue(productTitleInCart.contains("Bike Light"));
	}
}