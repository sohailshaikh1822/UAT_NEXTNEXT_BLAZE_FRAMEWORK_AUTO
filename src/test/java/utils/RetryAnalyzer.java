package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_RETRY = 1; // run up to 3 times

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            System.out.println("Retrying " + result.getName() + " again — attempt " + (count + 1));
            return true; // TestNG will re-run it in the same thread sequentially
        }
        return false;
    }
}
