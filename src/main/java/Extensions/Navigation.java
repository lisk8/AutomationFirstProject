package Extensions;

import Utilities.CommonOps;
import Utilities.ManagePages;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Navigation extends CommonOps
{
    @Step("Navigate to Iframe")
    public static void SwitchToIframe(WebElement elem)
    {
        driver.switchTo().frame(elem);
    }

    @Step("Navigate to Default")
    public static void SwitchToDefault()
    {
        driver.switchTo().defaultContent();
    }

    @Step("Navigate to Site")
    public static void NavigateToUrl()
    {
        driver.navigate().to(ExternalData.GetSystemData("Url"));
    }

}
