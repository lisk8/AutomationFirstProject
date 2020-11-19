package Utilities;

import org.openqa.selenium.support.PageFactory;

public class ManagePages extends Base
{

    /**
     * Initializes all PageObjects for the main driver.
     */
    public static void InitPageObjects()
    {
        InitWebPO();
        InitMobilePO();
        InitElectronPO();
        InitDesktopPO();
        InitDesktopSessionDriverPO();
    }

    /**
     * Initializes web PageObjects for the main driver.
     */
    private static void InitWebPO()
    {
        headerPage = PageFactory.initElements(driver, PageObjects.WorldWideMetric.HeaderPage.class);
        calculatorPage = PageFactory.initElements(driver, PageObjects.WorldWideMetric.CalculatorPage.class);
    }

    /**
     * Initializes Desktop PageObjects for the main driver.
     */
    private static void InitDesktopPO()
    {
        calcMainPage = PageFactory.initElements(driver, PageObjects.Calculator.MainPage.class);
    }

    /**
     * Initializes Electron PageObjects for the main driver.
     */
    private static void InitElectronPO()
    {
        electronLeftMenu = PageFactory.initElements(driver, PageObjects.ElectronAPIDemos.LeftMenu.class);
        electronRightMenu = PageFactory.initElements(driver, PageObjects.ElectronAPIDemos.RightMenu.class);
    }

    /**
     * Initializes Mobile PageObjects for the main driver.
     */
    private static void InitMobilePO()
    {
        mortgageMain = PageFactory.initElements(driver, PageObjects.Mortgage.MainPage.class);
    }

    /**
     * Initializes application's windows PageObjects for the desktop session driver.
     */
    public static void InitDesktopSessionDriverPO()
    {
        electronPopups = PageFactory.initElements(desktopSession, PageObjects.ElectronAPIDemos.PopupWindows.class);
    }

    /**
     * Initializes popups PageObjects for the sub windows driver.
     */
    public static void InitSubWindowDriverPO()
    {
        electronInformationPopup = PageFactory.initElements(subWindowDriver, PageObjects.ElectronAPIDemos.InformationPopup.class);
    }
}
