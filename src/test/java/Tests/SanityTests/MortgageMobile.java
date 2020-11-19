package Tests.SanityTests;

import Extensions.Verifications;
import Utilities.CommonOps;
import WorkFlows.MobileFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class MortgageMobile extends CommonOps
{

    @Test(description = "Verify Mortgage Repayment")
    @Description("Inputting values, Pressing Calculate and Verifying Repayment")
    public void Test01_VerifyRepayment()
    {
        MobileFlows.Calculate("3000","3","4");
        Verifications.VerifyTextInElement(mortgageMain.txt_result_repayment, "£90.09");
    }

    @Test(description = "Verify Mortgage Interest Only")
    @Description("Inputting values, Pressing Calculate and Verifying Interest Only")
    public void Test02_VerifyInterestOnly()
    {
        MobileFlows.Calculate("100","1","1");
        Verifications.VerifyTextInElement(mortgageMain.txt_result_interest_only, "£.08");
    }

}
