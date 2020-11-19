package PageObjects.ElectronAPIDemos;

import Utilities.CommonOps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RightMenu extends CommonOps
{

    /**
     * @param sectionIndex The index of the right menu main option.
     * @return Right menu main option.
     */
    public List<WebElement> btn_demo_toggle(int sectionIndex)
    {
        return driver.findElements(By.xpath("//section[" + (sectionIndex + 1) + "]//div[@class='demo']/div/button"));
    }

    /**
     * @param sectionIndex The index of the right menu main option.
     * @return Right menu View Demo button.
     */
    public List<WebElement> btn_demo_view(int sectionIndex)
    {
        return driver.findElements(By.xpath("//section[" + (sectionIndex + 1) + "]//div[@class='demo']//div[@class='demo-box']//button"));
    }

    /**
     * @param sectionIndex The index of the right menu demo response.
     * @return Right menu Demo Response.
     */
    public List<WebElement> text_demo_response(int sectionIndex)
    {
        return driver.findElements(By.xpath("//section[" + (sectionIndex + 1) + "]//div[@class='demo']//span[@class='demo-response']"));
    }
}
