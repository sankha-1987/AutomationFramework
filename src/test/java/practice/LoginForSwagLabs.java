package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginForSwagLabs {

	public static void main(String[] args) throws InterruptedException {
		
		//step 1 : launch the browser
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//step 2 : load the URL
		driver.get("https://www.saucedemo.com/");
		
		//step 3 : login to the application
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		//step 4 : click on a product
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']")).click();
	String productTitle = driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']")).getText();

		//step5 : add product to cart
		Thread.sleep(1000);
		driver.findElement(By.id("add-to-cart")).click();

		//step 6 : navigate to cart and validate the product
		driver.findElement(By.className("shopping_cart_link")).click();
String productTitleInCart = driver.findElement(By.className("inventory_item_name")).getText();

	if(productTitleInCart.equalsIgnoreCase(productTitle))
	{
		System.out.println("product successfully added to cart - PASS");
		System.out.println(productTitleInCart);
	}
	else
	{
		System.out.println("product not added to cart - FAIL");
	}

		//step 7 : logout of application
	Thread.sleep(1000);
	driver.findElement(By.id("react-burger-menu-btn")).click();
	driver.findElement(By.linkText("Logout")).click();
	System.out.println("logout successful");
		
	}	
}
