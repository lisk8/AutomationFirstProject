package PageObjects.ElectronAPIDemos;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InformationPopup
{
    @FindBy(name = "Yes")
    public WebElement btn_yes;

    @FindBy(name = "No")
    public WebElement btn_no;
}
