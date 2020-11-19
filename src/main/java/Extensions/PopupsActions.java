package Extensions;

import Utilities.CommonOps;
import Utilities.ManagePages;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PopupsActions extends CommonOps
{

    @Step("Set a Popup Window")
    public static void SetPopupWindow(WebElement window)
    {
        try
        {
            // Here you find the already running application and get the handle
            WebElement MAWebElement = window;
            String MAWinHandleStr = MAWebElement.getAttribute("NativeWindowHandle");
            int MAWinHandleInt = Integer.parseInt(MAWinHandleStr);
            String MAWinHandleHex = Integer.toHexString(MAWinHandleInt);

            // You attach to the already running application
            DesiredCapabilities MACapabilities = new DesiredCapabilities();
            MACapabilities.setCapability("platformName", "Windows");
            MACapabilities.setCapability("deviceName", "WindowsPC");
            // You set the Handle as one of the capabilities
            MACapabilities.setCapability("appTopLevelWindow", MAWinHandleHex);

            // My Application Session
            String winAppServer = ExternalData.GetSystemData("WinAppServer");
            subWindowDriver = new WindowsDriver(new URL(winAppServer), MACapabilities);
            long timeout = Long.parseLong(ExternalData.GetSystemData("Timeout"));
            subWindowDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            ManagePages.InitSubWindowDriverPO();
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
}
