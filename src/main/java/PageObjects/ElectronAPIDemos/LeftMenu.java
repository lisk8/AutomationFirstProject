package PageObjects.ElectronAPIDemos;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LeftMenu
{
    @FindBy(xpath = "//nav/div//button")
    public List<WebElement> btn_options;
}
