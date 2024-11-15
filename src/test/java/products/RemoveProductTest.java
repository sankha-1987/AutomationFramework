package products;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;

@Listeners(genericUtilities.ListenersImplementation.class)
/*Test Class*/
public class RemoveProductTest extends BaseClass {

	@Test(groups="Regression")
	public void sampleTest()
	{
		System.out.println("product removed successfully");
	}
	
}
