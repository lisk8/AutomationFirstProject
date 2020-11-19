package Utilities;

import Extensions.ExternalData;
import Extensions.Navigation;
import Extensions.UIActions;
import WorkFlows.ElectronFlows;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners(TestNGListeners.class)
public class CommonOps extends Base
{

    /**
     * Setup for the test suites XML parameters to override Config parameters.
     * @param context Current suite test context
     */
    @BeforeTest
    public void InitTestParams(ITestContext context)
    {
        testParams = context.getCurrentXmlTest().getAllParameters();
    }

    /**
     * Initializes main driver as web driver.
     * @param browserType Chrome/Firefox/IE/edge
     */
    public static void InitBrowser(String browserType)
    {
        if (browserType.equalsIgnoreCase("chrome"))
            driver = InitChromeDriver();
        else if (browserType.equalsIgnoreCase("firefox"))
            driver = InitFirefoxDriver();
        else if (browserType.equalsIgnoreCase("IE"))
            driver = InitIEDriver();
        else if (browserType.equalsIgnoreCase("edge"))
            driver = InitEdgeDriver();
        else
            throw new RuntimeException("Invalid browser type stated");
        Navigation.NavigateToUrl();
        InitSeleniumDriverMisc();
    }

    /**
     * Initializes main driver as mobile driver.
     * @param mobileType android/apple
     */
    public static void InitMobile(String mobileType)
    {
        capabilities.setCapability("reportDirectory", new File(ExternalData.GetSystemData("AppiumReportDirectory")).getAbsolutePath());
        capabilities.setCapability("reportFormat", ExternalData.GetSystemData("AppiumReportFormat"));
        capabilities.setCapability("testName", ExternalData.GetSystemData("AppiumTestName"));

        if (mobileType.equalsIgnoreCase("android"))
            driver = InitAndroidDriver();
        else if (mobileType.equalsIgnoreCase("apple")) ;
        //not implemented
        InitSeleniumDriverMisc();
    }

    /**
     * Sub setups For the main driver such as: maximize, timeouts, actions, etc.
     */
    public static void InitSeleniumDriverMisc()
    {
        String platform = ExternalData.GetSystemData("PlatformName");
        if (platform.equalsIgnoreCase("electron"))
            ((JavascriptExecutor) driver).executeScript("require('electron').remote.BrowserWindow.getFocusedWindow().maximize();");
        else if (!platform.equalsIgnoreCase("mobile"))
            driver.manage().window().maximize();

        long timeout = Long.parseLong(ExternalData.GetSystemData("Timeout"));
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, timeout);
        action = new Actions(driver);
    }


    /**
     * Initializes API httpRequest automation environment.
     */
    public static void InitAPI()
    {
        RestAssured.baseURI = ExternalData.GetSystemData("APIUrl");
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
    }

    /**
     * Initializes main driver as an Electron application driver.
     */
    public static void InitElectron()
    {
        System.setProperty("webdriver.chrome.driver", ExternalData.GetSystemData("ElectronDriverPath"));
        opt = new ChromeOptions();
        opt.setBinary(ExternalData.GetSystemData("ElectronAppPath"));
        capabilities.setCapability("chromeOptions", opt);
        capabilities.setBrowserName("chrome");
        driver = new ChromeDriver(capabilities);
        InitSeleniumDriverMisc();
    }

    /**
     * Initializes main driver as a driver for desktop.
     */
    public static void InitDesktop()
    {
        String operatingSystem = ExternalData.GetSystemData("OS");
        if (operatingSystem.equalsIgnoreCase("windows"))
            driver = InitWinAppDriver();
        else if (operatingSystem.equalsIgnoreCase("mac")) ;
        //not implemented
        InitSeleniumDriverMisc();
    }

    /**
     * Initializes sub driver that can interact with already open Windows applications, such as popups.
     */
    public static void InitSubDesktopSession()
    {
        if(!Boolean.parseBoolean(ExternalData.GetSystemData("ActivateSubWinAppDriver"))) return;
        DesiredCapabilities desktopCapabilities = new DesiredCapabilities();
        desktopCapabilities.setCapability("platformName", "Windows");
        desktopCapabilities.setCapability("deviceName", "WindowsPC");
        desktopCapabilities.setCapability("app", "Root");
        try
        {
            String winAppServer = ExternalData.GetSystemData("WinAppServer");
            desktopSession = new WindowsDriver(new URL(winAppServer), desktopCapabilities);
        } catch (Exception e)
        {
            throw new RuntimeException("Cannot connect to WinAppDriver Server: " + e);
        }
        long timeout = Long.parseLong(ExternalData.GetSystemData("Timeout"));
        desktopSession.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    /**
     * @return WebDriver for Internet Explorer browser
     */
    public static WebDriver InitIEDriver()
    {
        WebDriverManager.iedriver().setup();
        WebDriver ieDriver = new InternetExplorerDriver();
        return ieDriver;
    }

    /**
     * @return WebDriver for Microsoft Edge browser
     */
    public static WebDriver InitEdgeDriver()
    {
        WebDriverManager.edgedriver().setup();
        WebDriver edgeDriver = new EdgeDriver();
        return edgeDriver;
    }

    /**
     * @return WebDriver for Firefox browser
     */
    public static WebDriver InitFirefoxDriver()
    {
        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();
        return firefoxDriver;
    }

    /**
     * @return WebDriver for Chrome browser
     */
    public static WebDriver InitChromeDriver()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();
        return chromeDriver;
    }

    /**
     * @return Android WebDriver
     */
    public static WebDriver InitAndroidDriver()
    {
        try
        {
            capabilities.setCapability(MobileCapabilityType.UDID, ExternalData.GetSystemData("MobileUDID"));
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ExternalData.GetSystemData("MobilePackageName"));
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ExternalData.GetSystemData("MobileActivityName"));
            return new AndroidDriver(new URL(ExternalData.GetSystemData("AppiumServer")), capabilities);
        } catch (MalformedURLException e)
        {
            throw new RuntimeException("Cannot connect to Appium Server: " + e);
        }
    }

    /**
     * @return WebDriver for Windows applications
     */
    public static WebDriver InitWinAppDriver()
    {
        String winAppPath = ExternalData.GetSystemData("WinAppPath");
        capabilities.setCapability("app", winAppPath);
        try
        {
            String winAppServer = ExternalData.GetSystemData("WinAppServer");
            return new WindowsDriver(new URL(winAppServer), capabilities);
        } catch (MalformedURLException e)
        {
            throw new RuntimeException("Cannot connect to WinAppDriver Server: " + e);
        }
    }

    /**
     * Runs before class: setups the appropriate automation environment according to information from the config XML/Test Suite XML files.
     */
    @BeforeClass
    public void StartSession()
    {
        String platform = ExternalData.GetSystemData("PlatformName");
        if (platform.equalsIgnoreCase("web"))
            InitBrowser(ExternalData.GetSystemData("BrowserName"));
        else if (platform.equalsIgnoreCase("mobile"))
            InitMobile(ExternalData.GetSystemData("MobileOS"));
        else if (platform.equalsIgnoreCase("api"))
            InitAPI();
        else if (platform.equalsIgnoreCase("electron"))
            InitElectron();
        else if (platform.equalsIgnoreCase("desktop"))
            InitDesktop();
        else
            throw new RuntimeException("Invalid platform stated");
        InitSubDesktopSession();
        ManagePages.InitPageObjects();
        ManageDB.InitConnection(ExternalData.GetSystemData("DBUrl"), ExternalData.GetSystemData("DBUser"), ExternalData.GetSystemData("DBPass"));
    }

    /**
     * After each @Test returns the environment to the initial condition according to the config environment.
     */
    @AfterMethod
    public void CloseMethod()
    {
        String platform = ExternalData.GetSystemData("PlatformName");

        if (platform.equalsIgnoreCase("web"))
        {
            Navigation.NavigateToUrl();
            Navigation.SwitchToDefault();
        }
        else if (platform.equalsIgnoreCase("electron"))
        {
            ElectronFlows.ToggleDemo(0, 0);
        }
        else if (platform.equalsIgnoreCase("desktop"))
        {
            UIActions.Click(calcMainPage.btn_clear);
        }
        else if (platform.equalsIgnoreCase("mobile"))
        {
            ((AndroidDriver)driver).startActivity(new Activity(ExternalData.GetSystemData("MobilePackageName"),ExternalData.GetSystemData("MobileActivityName")));
        }
    }

    /**
     * Closes all processes: main driver, sub driver, database, etc.
     */
    @AfterClass
    public void CloseSession()
    {
        String platform = ExternalData.GetSystemData("PlatformName");

        if (!platform.equalsIgnoreCase("api"))
        {
            driver.quit();
        }
        if (desktopSession != null) desktopSession.quit();
        if (subWindowDriver != null) subWindowDriver.quit();
        ManageDB.CloseConnection();
    }
}
