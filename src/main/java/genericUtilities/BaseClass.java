package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import objectRepository.AllProductsPage;
import objectRepository.LoginPage;

public class BaseClass {
	
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public SeleniumUtility sUtil = new SeleniumUtility();
	public WebDriver driver;
	
	/*used for Listeners*/
	public static WebDriver sDriver; //null
	
	@BeforeSuite(groups={"Smoke", "Regression"})
	public void bsConfig()
	{
		System.out.println("--- Database connection successful ---");
	}
	
	
//	@Parameters("browser")					// For Cross Browser Execution
//	@BeforeTest								// For Cross Browser Execution
	@BeforeClass(alwaysRun = true)			// commented for cross browser testing
	public void bcConfig(/*String BROWSER*/) throws IOException
	{
		/*For Cross Browser Execution*/
/*		if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else
		{
			driver = new FirefoxDriver();
		}
*/
		String URL = pUtil.readDataFromPropertyFile("url");
			driver = new ChromeDriver();   // commented for cross browser testing
		
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);
		driver.get(URL);
		
		System.out.println("--- Browser launch successful ---");
		
		/*Used for Listeners*/
		sDriver = driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws IOException
	{
		String USERNAME1 = pUtil.readDataFromPropertyFile("username1");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME1, PASSWORD);
		
		System.out.println("--- Login to application successful ---");
	}
	
	@AfterMethod(alwaysRun = true)
	public void amConfig()
	{
		AllProductsPage app = new AllProductsPage(driver);
		app.logoutOfApp();
		
		System.out.println("--- Logout of application successful ---");
	}

//	@AfterTest						   // For Cross Browser Execution
	@AfterClass(alwaysRun = true)      // commented for cross browser testing
	public void acConfig()
	{
		driver.quit();
		System.out.println("--- Browser closure successful ---");
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		System.out.println("--- Database closure successful ---");
		
	}
}