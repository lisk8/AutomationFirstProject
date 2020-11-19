package Tests.Helpers;

import Utilities.CommonOps;
import Utilities.HelperMethods;
import org.testng.annotations.Test;

public class VisualTesting extends CommonOps
{
    @Test
    public void CreateHeaderLogoScreenShot()
    {
        HelperMethods.SaveElementScreenshot(headerPage.img_logo, "headerLogo_1_0v");
    }
}
