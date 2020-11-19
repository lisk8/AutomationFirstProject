package WorkFlows;

import Extensions.Navigation;
import Extensions.UIActions;
import Utilities.CommonOps;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class ElectronFlows extends CommonOps
{
    @Step("Click On View Demo on a Menu Option")
    public static void ClickViewDemo(int leftMenuOptionIndex, int rightMenuOptionIndex)
    {
        ToggleDemo(leftMenuOptionIndex, rightMenuOptionIndex);
        UIActions.Click(electronRightMenu.btn_demo_view(leftMenuOptionIndex).get(rightMenuOptionIndex));
    }

    @Step("Toggle a Demo Option on a Menu Option")
    public static void ToggleDemo(int leftMenuOptionIndex, int rightMenuOptionIndex)
    {
        UIActions.Click(electronLeftMenu.btn_options.get(leftMenuOptionIndex));
        UIActions.Click(electronRightMenu.btn_demo_toggle(leftMenuOptionIndex).get(rightMenuOptionIndex));
    }

    @Step("Click a Button on a popup")
    public static void PopupClickBtn(WebElement btn)
    {
        UIActions.Click(btn);
    }
}
