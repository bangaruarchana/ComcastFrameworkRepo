package practice_Test;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import net.bytebuddy.asm.Advice.Return;

public class RetryListnerImpemtation implements IRetryAnalyzer{

	int count=0;
	int limitcount=5;
	@Override
	public boolean retry(ITestResult result) {

		if(count<limitcount) {
			count++;
			return true;
		}
		return false;
	
	
}

}
