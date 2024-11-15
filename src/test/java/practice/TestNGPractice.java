package practice;

import org.testng.annotations.Test;

public class TestNGPractice {

	@Test(priority=2, invocationCount = 3)
	public void sampleTest()
	{
		System.out.println("hi");
	}
	
	@Test(priority=1)
	public void sampleTest2()
	{
		System.out.println("hello");
	}
	
	@Test(dependsOnMethods = "sampleTest4")
	public void sampleTest3()
	{
		System.out.println("hey");
	}
	
	@Test
	public void sampleTest4()
	{
		System.out.println("welcome");
	}
}
