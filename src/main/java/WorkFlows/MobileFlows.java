package WorkFlows;

import Extensions.UIActions;
import Utilities.CommonOps;
import io.qameta.allure.Step;

public class MobileFlows extends CommonOps
{
    @Step("Fill and Calculate Mortgage")
    public static void Calculate(String amount, String term, String rate)
    {
        UIActions.InputText(mortgageMain.txt_amount,amount);
        UIActions.InputText(mortgageMain.txt_term,term);
        UIActions.InputText(mortgageMain.txt_rate,rate);
        UIActions.Click(mortgageMain.btn_calculate);
    }
}
