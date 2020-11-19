package Utilities;

import Extensions.ExternalData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.File;

public class HelperMethods extends CommonOps
{
    /**
     * @param imgElem Element to take screenshot of.
     * @return Screenshot of the element specified.
     */
    public static Screenshot TakeAshotScreenShot(WebElement imgElem)
    {
        return new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, imgElem);
    }


    /**
     * Take a screenshot of an element and save the file locally in the project.
     * @param imgElem Element to take screenshot of.
     * @param imgName Name of saved image.
     */
    public static void SaveElementScreenshot(WebElement imgElem, String imgName)
    {
        String imgFormat = ExternalData.GetSystemData("ImgFormat");
        imgScreenShot = TakeAshotScreenShot(imgElem);
        try
        {
            ImageIO.write(imgScreenShot.getImage(), imgFormat, new File(ExternalData.GetSystemData("ImgRepo") + imgName + "." + imgFormat));
        } catch (Exception ex)
        {
            throw new RuntimeException("Error writing image files, see details: " + ex);
        }
    }

    /**
     * Starts the server of the API (Config)
     */
    public static void StartStudentsAPI()
    {
        File studentsFile = new File(ExternalData.GetSystemData("localApiJar"));
        String apiPath = studentsFile.getAbsolutePath();
        String port = ExternalData.GetSystemData("APIUrl").split(":")[2].replace("/", "");
        StartCMD("java -jar " + apiPath + " --server.port=" + port, true);
    }

    /**
     * Starts the Allure Report Server (Config)
     */
    public static void StartAllureReport()
    {
        File allureResults = new File("./allure-results");
        String allurePath = allureResults.getAbsolutePath();
        StartCMD("allure serve " + allurePath, true);
    }

    /**
     * Starts the Jenkins Server (Config)
     */
    public static void StartJenkins()
    {
        String jenkinsPath = ExternalData.GetSystemData("JenkinsPath");
        StartCMD("java -jar " + jenkinsPath + " --httpPort=9090",true);
    }

    /**
     * Starts the WinAppDriver server (Config)
     */
    public static void StartWinAppDriver()
    {
        String winAppDriverPath = ExternalData.GetSystemData("WinAppDriverPath");
        StartCMD("cd " + winAppDriverPath + " & start WinAppDriver.exe", false);
    }

    /**
     * Opens the Windows Inspect tool(Config)
     */
    public static void StartWinInspect()
    {
        String inspectAppPath = ExternalData.GetSystemData("InspectAppPath");
        StartCMD("cd " + inspectAppPath + " & start inspect.exe", false);
    }

    /**
     * Run a command through CMD
     * @param command The command to be run
     * @param keepOpen Close or Open the CMD after running the command
     */
    public static void StartCMD(String command, boolean keepOpen)
    {
        try
        {
            if (keepOpen)
                Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"" + command + "\"");
            else
                Runtime.getRuntime().exec("cmd /c start cmd.exe /C \"" + command + "\"");
        } catch (Exception e)
        {
            throw new RuntimeException("There was a problem initiating CMD: " + e);
        }
    }


}
