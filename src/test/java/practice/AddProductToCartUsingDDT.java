package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddProductToCartUsingDDT {

	public static void main(String[] args) throws InterruptedException, IOException {
		
	//Read the Common Data from property file
	FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");	
	Properties p = new Properties();
	p.load(fisp);
	String URL = p.getProperty("url");	
	String USERNAME1 = p.getProperty("username1");
	String PASSWORD = p.getProperty("password");
		
	//Read the Test Data from Excel file
	FileInputStream fise = new FileInputStream("C:\\Users\\User\\Desktop\\TestData.xlsx"); 
	Workbook wb = WorkbookFactory.create(fise);
	Sheet sh = wb.getSheet("Products");
	Row rw = sh.getRow(1);
	Cell cl = rw.getCell(2);
	String PRODUCTNAME = cl.getStringCellValue();
	
		// step 1 : launch the browser
		WebDriver driver = new ChromeDriver();	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// step 2 : load the URL
		driver.get(URL);
		
		// step 3 : login to the application
	driver.findElement(By.id("user-name")).sendKeys(USERNAME1);
	driver.findElement(By.id("password")).sendKeys(PASSWORD);
	driver.findElement(By.id("login-button")).click();

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
