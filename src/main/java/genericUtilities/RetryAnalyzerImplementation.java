package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImplementation implements IRetryAnalyzer{

	int count = 0;
	int retryCount = 3;
	
	@Override
	public boolean retry(ITestResult result) {
		
		//0<3  1<3  2<3  3<3-No
		if(count<retryCount)
		{
			count++; //1 2 3
			return true; //retry retry retry
		}	
		return false; // not retry
	}
}