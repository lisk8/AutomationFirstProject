package Utilities;

import Extensions.DbActions;
import Extensions.ExternalData;
import WorkFlows.ApiFlows;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class TestNGListeners extends CommonOps implements ITestListener
{
    public void onTestStart(ITestResult iTestResult)
    {
        System.out.println("Starting Execution:" + iTestResult.getName());
    }

    public void onTestSuccess(ITestResult iTestResult)
    {
        System.out.println("Test Succeeded:" + iTestResult.getName());
    }

    public void onTestFailure(ITestResult iTestResult)
    {
        OnFailureMethods();
        System.out.println("Test Failed:" + iTestResult.getName());
    }

    public void onTestSkipped(ITestResult iTestResult)
    {
        System.out.println("Test Skipped:" + iTestResult.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult)
    {
        OnFailureMethods();
        System.out.println("Test Failed Within Success Percentage:" + iTestResult.getName());
    }


    /**
     * Runs when beginning the tests
     * @param iTestContext Information from Test
     */
    public void onStart(ITestContext iTestContext)
    {
        System.out.println("Starting Tests");
    }

    /**
     * Runs when finishing the tests
     * @param iTestContext Information from Test
     */
    public void onFinish(ITestContext iTestContext)
    {
        System.out.println("Finishing Tests");
    }

    /**
     * Gets information for to Allure Report in case of a failure.
     */
    private void OnFailureMethods()
    {
        String platform = ExternalData.GetSystemData("PlatformName");
        if (platform.equalsIgnoreCase("api"))
        {
            TakeAPIJSONSourceScreenshot();
        }
        else
        {
            TakeMainScreenShot();
            TakeMainHtmlSourceScreenshot();
        }
        if ((subWindowDriver) != null)
        {
            try
            {
                TakeSubScreenShot();
                TakeSubHtmlSourceScreenshot();
            } catch (WebDriverException e)
            {
            }
        }
        if (Boolean.parseBoolean(ExternalData.GetSystemData("ActivateDB")))
            GetDBTableScreenShot();
    }

    /**
     * @param driver driver to take screenshot of.
     */
    private byte[] TakeScreenShot(WebDriver driver)
    {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * @param driver driver to take HTML source screenshot of.
     */
    private String TakeHtmlSourceScreenshot(WebDriver driver)
    {
        return driver.getPageSource();
    }

    /**
     * @return Attaches the main driver screenshot to Allure Report
     */
    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] TakeMainScreenShot()
    {
        return TakeScreenShot(driver);
    }

    /**
     * @return Attaches the sub driver screenshot to Allure Report
     */
    @Attachment(value = "Sub Screenshot", type = "image/png")
    private byte[] TakeSubScreenShot()
    {
        return TakeScreenShot(subWindowDriver);
    }

    /**
     * @return Attaches the main driver HTML source to Allure Report
     */
    @Attachment(value = "HTML Source", type = "text/xml")
    private String TakeMainHtmlSourceScreenshot()
    {
        return TakeHtmlSourceScreenshot(driver);
    }

    /**
     * @return Attaches the sub driver HTML source to Allure Report
     */
    @Attachment(value = "Sub HTML Source", type = "text/xml")
    private String TakeSubHtmlSourceScreenshot()
    {
        return TakeHtmlSourceScreenshot(subWindowDriver);
    }

    /**
     * @return Attaches the API GET results to Allure Report
     */
    @Attachment(value = "API Source", type = "application/json")
    private String TakeAPIJSONSourceScreenshot()
    {
        String source = ApiFlows.GetStudentListResponse().prettyPrint();
        return source;
    }

    /**
     * @return Attaches the DB table to Allure Report
     */
    @Attachment(value = "DB Table", type = "text/csv")
    public static String GetDBTableScreenShot()
    {
        try
        {
            String result = "";
            ResultSet resultSet = DbActions.GetDBData("SELECT * FROM Pressure_Conversion");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++)
            {
                result += metadata.getColumnName(i) + ", ";
            }
            result = result.substring(0, result.length() - 2);
            result += "\n";
            while (resultSet.next())
            {
                for (int i = 1; i <= columnCount; i++)
                {
                    result += resultSet.getString(i) + ", ";
                }
                result = result.substring(0, result.length() - 2);
                result += "\n";
            }
            return result;
        } catch (Exception e)
        {
            throw new RuntimeException("There's been a problem getting the DB table: " + e);
        }
    }

}
