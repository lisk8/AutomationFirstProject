package PageObjects.Mortgage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage
{
    @FindBy(id = "etAmount")
    public WebElement txt_amount;

    @FindBy(id = "etTerm")
    public WebElement txt_term;

    @FindBy(id = "etRate")
    public WebElement txt_rate;

    @FindBy(id = "btnCalculate")
    public WebElement btn_calculate;

    @FindBy(id = "tvRepayment")
    public WebElement txt_result_repayment;

    @FindBy(id = "tvInterestOnly")
    public WebElement txt_result_interest_only;
}
