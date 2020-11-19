package Tests.Helpers;

import Utilities.HelperMethods;
import org.testng.annotations.Test;

public class StartProcesses
{
    /**
     * Starts Allure Report Server
     */
    @Test
    private void StartAllureReport()
    {
        HelperMethods.StartAllureReport();
    }

    /**
     * Starts API Server
     */
    @Test
    private void StartStudentsAPI()
    {
        HelperMethods.StartStudentsAPI();
    }

    /**
     * Opens Inspect Tool
     */
    @Test
    private void StartWinInspect()
    {
        HelperMethods.StartWinInspect();
    }

    /**
     * Starts WinAppDriver Server
     */
    @Test
    private void StartWinAppDriver()
    {
        HelperMethods.StartWinAppDriver();
    }


    /**
     * Starts Jenkins
     */
    @Test
    private void StartJenkins()
    {
        HelperMethods.StartJenkins();
    }
}
