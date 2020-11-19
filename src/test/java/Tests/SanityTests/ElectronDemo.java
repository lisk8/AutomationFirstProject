package Tests.SanityTests;

import Extensions.PopupsActions;
import Extensions.Verifications;
import Utilities.CommonOps;
import WorkFlows.ElectronFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ElectronDemo extends CommonOps
{
    @Test(description = "Verify Screen Information")
    @Description("Choose the Get Screen Information option and Verify the Resolution")
    public void Test01_ViewScreenInformation()
    {
        int leftOptionIndex = 8, rightOptionIndex = 3;
        ElectronFlows.ClickViewDemo(leftOptionIndex,rightOptionIndex);
        Verifications.VerifyTextInElement(electronRightMenu.text_demo_response(leftOptionIndex).get(rightOptionIndex),"Your screen is: 1920px x 1080px");
    }

    @Test(description = "Verify Clipboard Paste")
    @Description("Paste and Verify the Result")
    public void Test02_PasteClipboard()
    {
        int leftOptionIndex = 9, rightOptionIndex = 1, resultIndex = 0;
        ElectronFlows.ClickViewDemo(leftOptionIndex,rightOptionIndex);
        Verifications.VerifyTextInElement(electronRightMenu.text_demo_response(leftOptionIndex).get(resultIndex),"Clipboard contents: What a demo!");
    }

    @Test(description = "Verify System Dialog Shows No")
    @Description("Choose System Dialog Click 'No' and Verify the Result")
    public void Test03_SystemDialogVerify()
    {
        int leftOptionIndex = 5, rightOptionIndex = 2, resultIndex = 1;
        ElectronFlows.ClickViewDemo(leftOptionIndex,rightOptionIndex);
        PopupsActions.SetPopupWindow(electronPopups.window_information_pop_up);
        ElectronFlows.PopupClickBtn(electronInformationPopup.btn_no);
        Verifications.VerifyTextInElement(electronRightMenu.text_demo_response(leftOptionIndex).get(resultIndex),"You selected no.");
    }
}
