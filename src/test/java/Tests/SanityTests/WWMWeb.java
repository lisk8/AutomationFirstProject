package Tests.SanityTests;

import Extensions.Navigation;
import Extensions.Verifications;
import Utilities.CommonOps;
import WorkFlows.WebFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class WWMWeb extends CommonOps
{
    int formIndex = 0;
    String[] expectedConversionResult = {"15", "15.296", "217.51"};

    @Test(description = "Go To Calculator Page")
    @Description("Navigating to calculator page using the header menu")
    public void Test01_GoToCalculator()
    {
        WebFlows.SelectHeaderOption(5);
        Verifications.VerifyTextInUrl("http://www.worldwidemetric.com/measurements.html");
    }

    @Test(description = "Calculate Bar Conversions Values")
    @Description("Input, calculate & validate conversion values")
    public void Test02_ConvertBarPressure()
    {
        WebFlows.SelectHeaderOption(5);
        Navigation.SwitchToIframe(calculatorPage.iframe_conversion);
        WebFlows.InputAndCalculate(formIndex, 0, expectedConversionResult[0]);
        Verifications.VerifyInputValues(calculatorPage.GetConversionInputFields(formIndex), expectedConversionResult);
    }

    @Test(description = "Clear Bar Conversions Values")
    @Description("Clear & validate conversion values")
    public void Test03_ClearPressureValues()
    {
        String ExpectedEmpty = "";
        WebFlows.SelectHeaderOption(5);
        Navigation.SwitchToIframe(calculatorPage.iframe_conversion);
        WebFlows.InputAndCalculate(formIndex, 0, expectedConversionResult[0]);
        WebFlows.ClearConversionValues(formIndex);
        Verifications.VerifyInputValues(calculatorPage.GetConversionInputFields(formIndex), ExpectedEmpty);
    }

    @Test(description = "Verify Bar Conversions Labels")
    @Description("Validate conversion labels")
    public void Test04_VerifyPressureLabels()
    {
        String[] expectedLabels = {"Bar", "Kg/cm", "Psi"};
        WebFlows.SelectHeaderOption(5);
        Navigation.SwitchToIframe(calculatorPage.iframe_conversion);
        Verifications.VerifyTextInElements(calculatorPage.GetConversionLabels(formIndex), expectedLabels);
        Navigation.SwitchToDefault();
    }

    @Test(description = "Verify Header Logo")
    @Description("Comparing the header logo with a screenshot taken from a previous version")
    public void Test05_VerifyHeaderLogo()
    {
        Verifications.VerifyElementVisually(headerPage.img_logo,"headerLogo_1_0v");
    }
}
