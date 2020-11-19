package Utilities;

import io.appium.java_client.windows.WindowsDriver;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

public class Base
{
    public static WebDriver driver;
    public static ChromeOptions opt;
    public static DesiredCapabilities capabilities = new DesiredCapabilities();;
    public static WebDriverWait wait;
    public static Actions action;
    public static Screenshot imgScreenShot;
    public static ImageDiffer imgDiff = new ImageDiffer();
    public static ImageDiff diff;

    public static WindowsDriver desktopSession;
    public static WindowsDriver subWindowDriver;

    public static Map<String, String> testParams;

    public static PageObjects.WorldWideMetric.HeaderPage headerPage;
    public static PageObjects.WorldWideMetric.CalculatorPage calculatorPage;

    public static PageObjects.Mortgage.MainPage mortgageMain;

    public static PageObjects.ElectronAPIDemos.LeftMenu electronLeftMenu;
    public static PageObjects.ElectronAPIDemos.RightMenu electronRightMenu;
    public static PageObjects.ElectronAPIDemos.PopupWindows electronPopups;
    public static PageObjects.ElectronAPIDemos.InformationPopup electronInformationPopup;

    public static PageObjects.Calculator.MainPage calcMainPage;

    public static RequestSpecification httpRequest;
    public static JSONObject requestParams = new JSONObject();
    public static JSONArray requestParamsArray = new JSONArray();
    public static JsonPath jp;

    public static Connection dbConnection;
    public static Statement dbStatement;
}
