package PageObjects.WorldWideMetric;

import Utilities.CommonOps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeaderPage extends CommonOps
{
    @FindBy(xpath = "//img[@title='World Wide Metric']")
    public WebElement img_logo;

    @FindBy(xpath = "//*[@id='topnav_new']/span/ul/li/a")
    public List<WebElement> btn_headerList;

    /**
     * @param index Index of header option
     * @param subOptionIndex index of the sub option
     * @return The sub option Element
     */
    public WebElement GetHeaderSubOptionXpath(int index, int subOptionIndex)
    {
        String newIndex = Integer.toString(index + 1);
        String newSubIndex = Integer.toString(subOptionIndex + 1);
        String xpath = "//*[@id='topnav_new']//li[" + newIndex +"]//li["+ newSubIndex +"]/a";
        return driver.findElement(By.xpath(xpath));
    }
}


