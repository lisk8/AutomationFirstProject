package WorkFlows;

import Extensions.UIActions;
import Utilities.CommonOps;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CalcFlows extends CommonOps
{
    @Step("Calculate Addition")
    public static void CalculateAddition(int firstNum, int secondNum)
    {
        WebElement[] nums = calcMainPage.GetNumButtons();
        UIActions.Click(nums[firstNum]);
        UIActions.Click(calcMainPage.btn_plus);
        UIActions.Click(nums[secondNum]);
        UIActions.Click(calcMainPage.btn_equals);
    }

    @Step("Type Formula")
    public static void TypeFormula(String formula)
    {
        WebElement result = calcMainPage.txt_result;
        result.sendKeys(formula + Keys.ENTER);
    }
}
