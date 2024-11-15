package practice;


import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.LoginPage;

public class AddProductToCartUsingDDTandGU {

	public static void main(String[] args) throws InterruptedException, IOException {
	
		// Create Object of Utility Classes
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		SeleniumUtility sUtil = new SeleniumUtility();
		
		
	//Read the Common Data from property file
	String URL = pUtil.readDataFromPropertyFile("url");
	String USERNAME1 = pUtil.readDataFromPropertyFile("username1");
	String PASSWORD = pUtil.readDataFromPropertyFile("password");	
		
	//Read the Test Data from Excel file
	String PRODUCTNAME = eUtil.readDataFromExcel("Products", 1, 2);
	
		// step 1 : launch the browser
		WebDriver driver = new ChromeDriver();	
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);
		
		// step 2 : load the URL
		driver.get(URL);
		
		// step 3 : login to the application
//	driver.findElement(By.id("user-name")).sendKeys(USERNAME1);
//	driver.findElement(By.id("password")).sendKeys(PASSWORD);
//	driver.findElement(By.id("login-button")).click();

		LoginPage lp = new LoginPage(driver);
		lp.getUsernameEdt().sendKeys(USERNAME1);
		lp.getPasswordEdt().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		
		// step 4 : click on a product
	Thread.sleep(1000);
	System.out.println("Product name from excel: " + PRODUCTNAME);

/*the below line is an example of dynamic xpath*/
driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();
String productTitle = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).getText();

		// step5 : add product to cart
Thread.sleep(1000);
driver.findElement(By.id("add-to-cart")).click();

		// step 6 : navigate to cart and validate the product
driver.findElement(By.className("shopping_cart_link")).click();
String productTitleInCart = driver.findElement(By.className("inventory_item_name")).getText();

	if(productTitleInCart.equalsIgnoreCase(productTitle))
	{
		System.out.println("product successfully added to cart");
		System.out.println("PASS");
		System.out.println(productTitleInCart);
	}
	else
	{
		System.out.println("product not added to the cart - FAIL");
	}

		// step 7 : logout of application
	Thread.sleep(1000);
	driver.findElement(By.id("react-burger-menu-btn")).click();
	driver.findElement(By.linkText("Logout")).click();
	System.out.println("logout successful");
		
	}
}
