package WorkFlows;

import Extensions.UIActions;
import Utilities.CommonOps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class WebFlows extends CommonOps
{
    @Step("Select Header Option")
    public static void SelectHeaderOption(int btnIndex)
    {
        UIActions.Click(headerPage.btn_headerList.get(btnIndex));
    }

    @Step("Select Sub-Header Option")
    public static void SelectHeaderOption(int btnIndex, int subIndex)
    {
        UIActions.HoverElement(headerPage.btn_headerList.get(btnIndex));
        WebElement subOption = headerPage.GetHeaderSubOptionXpath(btnIndex, subIndex);
        UIActions.Click(subOption);
    }

    @Step("Input and Calculate Conversions")
    public static void InputAndCalculate(int conversionIndex,int fieldIndex, String inputValue)
    {
        WebElement inputFld = calculatorPage.GetConversionInputFields(conversionIndex).get(fieldIndex);
        UIActions.InputText(inputFld,inputValue);
        UIActions.Click(calculatorPage.GetConversionCalcBtn(conversionIndex));
    }

    @Step("Clear Conversions")
    public static void ClearConversionValues(int conversionIndex)
    {
        UIActions.Click(calculatorPage.GetConversionClearBtn(conversionIndex));
    }

}
