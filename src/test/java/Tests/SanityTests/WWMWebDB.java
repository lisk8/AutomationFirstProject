package Tests.SanityTests;

import Extensions.Navigation;
import Extensions.Verifications;
import Utilities.CommonOps;
import WorkFlows.DBFlows;
import WorkFlows.WebFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class WWMWebDB extends CommonOps
{

    @Test(description = "Calculate Bar Conversions Values Using DB Values")
    @Description("Input, calculate & validate conversion values From DB")
    public void Test01_ConvertBarPressureFromDB()
    {
        int formIndex = 0;
        String[] expectedConversionResult = DBFlows.GetPressureConversionValues(4);
        WebFlows.SelectHeaderOption(5);
        Navigation.SwitchToIframe(calculatorPage.iframe_conversion);
        WebFlows.InputAndCalculate(formIndex, 0, expectedConversionResult[0]);
        Verifications.VerifyInputValues(calculatorPage.GetConversionInputFields(formIndex), expectedConversionResult);
    }
}
