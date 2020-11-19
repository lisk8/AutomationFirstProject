package Tests.SanityTests;

import Extensions.Verifications;
import Utilities.CommonOps;
import WorkFlows.CalcFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class CalculatorWindows extends CommonOps
{
    @Test(description = "Calculate Addition Using Num Buttons")
    @Description("Calculate Addition With Buttons and Verify The Result")
    public void Test01_CalculateAdditionButtons()
    {
        int firstNum = 8, secondNum = 6;
        String expectedResult = "" + (firstNum + secondNum);
        CalcFlows.CalculateAddition(firstNum,secondNum);
        Verifications.VerifyTextInElement(calcMainPage.txt_result,"Display is",expectedResult);
    }

    @Test(description = "Calculate Addition Using Keyboard")
    @Description("Calculate Addition With Keyboard and Verify The Result")
    public void Test01_CalculateAdditionKeyboard()
    {
        int firstNum = 3, secondNum = 4;
        String expectedResult = "" + (firstNum + secondNum);
        CalcFlows.TypeFormula("" + firstNum + "+" + secondNum);
        Verifications.VerifyTextInElement(calcMainPage.txt_result,"Display is",expectedResult);
    }
}
