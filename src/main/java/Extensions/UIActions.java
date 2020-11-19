package Extensions;

import Utilities.CommonOps;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class UIActions extends CommonOps
{
    @Step("Click On Element")
    public static void Click(WebElement elem)
    {
        String platform = ExternalData.GetSystemData("PlatformName");
        if (platform.equalsIgnoreCase("web") || platform.equalsIgnoreCase("electron"))
            wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }

    @Step("Input Text in Element")
    public static void InputText(WebElement elem, String value)
    {
        String platform = ExternalData.GetSystemData("PlatformName");
        if (platform.equalsIgnoreCase("web") || platform.equalsIgnoreCase("electron"))
            wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.sendKeys(value);
    }

    @Step("Update Drop-Down Value")
    public static void UpdateDropDownByValue(WebElement elem, String value)
    {
        wait.until(ExpectedConditions.visibilityOf(elem));
        Select elemSelect = new Select(elem);
        elemSelect.selectByValue(value);
    }

    @Step("Hover Element")
    public static void HoverElement(WebElement elem)
    {
        wait.until(ExpectedConditions.visibilityOf(elem));
        action.moveToElement(elem).build().perform();
    }
}
