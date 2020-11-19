package Extensions;

import Utilities.CommonOps;
import Utilities.HelperMethods;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class Verifications extends CommonOps
{
    @Step("Verify Text In Element")
    public static void VerifyTextInElement(WebElement elem, String expectedText)
    {
        VerifyText(elem.getText(), expectedText);
    }

    @Step("Verify Text In Element")
    public static void VerifyTextInElement(WebElement elem,String excludeText, String expectedText)
    {
        VerifyText(elem.getText().replaceAll(excludeText,"").trim(), expectedText);
    }

    @Step("Verify Multiple Text In Elements")
    public static void VerifyTextInElements(List<WebElement> elems, String[] expectedTexts)
    {
        for (int i = 0; i < elems.size(); i++)
        {
            VerifyTextInElement(elems.get(i), expectedTexts[i]);
        }
    }

    @Step("Verify Text In Elements")
    public static void VerifyTextInElements(List<WebElement> elems, String expectedText)
    {
        for (int i = 0; i < elems.size(); i++)
        {
            VerifyTextInElement(elems.get(i), expectedText);
        }
    }

    @Step("Verify Text In Url")
    public static void VerifyTextInUrl(String expectedUrl)
    {
        String actualUrl = driver.getCurrentUrl();
        VerifyText(actualUrl, expectedUrl);
    }

    @Step("Verify Text In Input Element")
    public static void VerifyInputValue(WebElement elem, String expectedValue)
    {
        String inputValue = elem.getAttribute("value");
        VerifyText(inputValue, expectedValue);
    }

    @Step("Verify Multiple Text In Input Elements")
    public static void VerifyInputValues(List<WebElement> elems, String[] expectedTexts)
    {
        for (int i = 0; i < elems.size(); i++)
        {
            VerifyInputValue(elems.get(i), expectedTexts[i]);
        }
    }

    @Step("Verify Text In Input Elements")
    public static void VerifyInputValues(List<WebElement> elems, String expectedText)
    {
        for (int i = 0; i < elems.size(); i++)
        {
            VerifyInputValue(elems.get(i), expectedText);
        }
    }

    @Step("Verify Text")
    public static void VerifyText(String actual, String expected)
    {
        Assert.assertEquals(actual, expected);
    }

    @Step("Verify Element Visually")
    public static void VerifyElementVisually(WebElement imgElem, String expectedImgName)
    {
        String imageFormat = ExternalData.GetSystemData("ImgFormat");
        BufferedImage expectedImage = null;
        try
        {
            expectedImage = ImageIO.read(new File(ExternalData.GetSystemData("ImgRepo") + expectedImgName + "." + imageFormat));
        } catch (Exception ex)
        {
            throw new RuntimeException("Image not found, details: " + ex);
        }
        Screenshot imageScreenShot = HelperMethods.TakeAshotScreenShot(imgElem);
        BufferedImage actualImg = imageScreenShot.getImage();
        diff = imgDiff.makeDiff(expectedImage, actualImg);
        Assert.assertFalse(diff.hasDiff(), "The images are different");
    }

    @Step("Verify String List Values Are Unique")
    public static void VerifyUniqueList(List<String> listToCheck)
    {
        for (int i = 0; i < listToCheck.size(); i++)
        {
            String firstValue = listToCheck.get(i);
            for (int j = 0; j < listToCheck.size(); j++)
            {
                if(i==j) continue;
                String secondValue = listToCheck.get(j);
                Assert.assertNotEquals(firstValue,secondValue,"Values " + i + " & " + j + " both equal: " + secondValue);
            }
        }
    }
}
