package PageObjects.WorldWideMetric;

import Utilities.CommonOps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CalculatorPage extends CommonOps
{
    @FindBy(id = "TopFrame")
    public WebElement iframe_conversion;

    @FindBy(xpath = "//form[contains(@id,'frm')]")
    private List<WebElement> form_conversions;

    /**
     * @param index Index of the Conversion Table
     * @return A list of all the Units labels of the table specified.
     */
    public List<WebElement> GetConversionLabels(int index)
    {
        String InputFieldsSubXpath = ".//td[@align='middle' and @class='wwmservicezoom']";
        List<WebElement> result = form_conversions.get(index).findElements(By.xpath(InputFieldsSubXpath));
        return result;
    }

    /**
     * @param index Index of the Conversion Table
     * @return A list of all the Input fields of the table specified.
     */
    public List<WebElement> GetConversionInputFields(int index)
    {
        String InputFieldsSubXpath = ".//input[contains(@name,'val')]";
        List<WebElement> result = form_conversions.get(index).findElements(By.xpath(InputFieldsSubXpath));
        return result;
    }

    /**
     * @param index Index of the Conversion Table
     * @return The Calculate button of the specified table
     */
    public WebElement GetConversionCalcBtn(int index)
    {
        String InputFieldsSubXpath = ".//input[@value='Calculate']";
        WebElement result = form_conversions.get(index).findElement(By.xpath(InputFieldsSubXpath));
        return result;
    }

    /**
     * @param index Index of the Conversion Table
     * @return The Clear button of the specified table
     */
    public WebElement GetConversionClearBtn(int index)
    {
        String InputFieldsSubXpath = ".//input[@value='Clear']";
        WebElement result = form_conversions.get(index).findElement(By.xpath(InputFieldsSubXpath));
        return result;
    }



}
