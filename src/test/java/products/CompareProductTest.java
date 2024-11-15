package products;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;

@Listeners(genericUtilities.ListenersImplementation.class)
/*Test Class*/
public class CompareProductTest extends BaseClass{
	
	@Test
	public void compareTest()
	{
		System.out.println("products compared successfully");
	}
	
	@Test
	public void compareTest2()
	{
		System.out.println("products compared successfully - 2");
	}

	@Test
	public void compareTest3()
	{
		System.out.println("products compared successfully - 3");
	}
}
