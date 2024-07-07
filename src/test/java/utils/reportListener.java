package utils;


import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class reportListener extends ReportManager implements ITestListener {


    public void onTestStart(ITestResult result) {
        logger = extent.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {


            logger.log(Status.PASS, "Pass Test case is: " + result.getName());
            try {
                logger.addScreenCaptureFromBase64String(captureScreen(result.getName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }
    }

    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, "Skipped Test case is: " + result.getName());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.log(Status.INFO, "Test case failed but within success percentage: " + result.getName());
    }

    public void onStart(ITestContext context) {
        //test.log(Status.INFO, "Test Execution Started");
    }

    public void onFinish(ITestContext context) {
        try {
            Map<String, Object> testResult = new HashMap<>();
            testResult.put("TotalTestCaseCount", context.getAllTestMethods().length);
            testResult.put("PassedTestCaseCount", context.getPassedTests().size());
            testResult.put("FailedTestCaseCount", context.getFailedTests().size());
            testResult.put("SkippedTestCaseCount", context.getSkippedTests().size());

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String filePath = "test-output/ExtentReport/TestExecutionReport.json";
            mapper.writeValue(new File(filePath), testResult);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while writing to TestExecutionReport.json file: ",
                    e);
        }
    }

    public String captureScreen(String screenshotName) throws IOException {
        TakesScreenshot screen = (TakesScreenshot) driver;
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File src = screen.getScreenshotAs(OutputType.FILE);
        String dest =System.getProperty("user.dir") + "/Screenshots/"+screenshotName+dateName+".png";
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }


}