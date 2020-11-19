package Extensions;

import Utilities.Base;
import io.qameta.allure.Step;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ExternalData extends Base
{
    /**
     * Get a config value from external file
     * @param nodeName Name of XML Node.
     * @param fileName File name of the XML.
     * @return Extracted value from XML file.
     */
    private static String GetData(String nodeName, String fileName)
    {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("./Configuration/" + fileName + ".xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e)
        {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }


    /**
     * @param paramName Name of XML Node.
     * @return Test suite XML node value.
     */
    private static String GetTestSuiteData(String paramName)
    {
        if(testParams == null) return null;
        String result = testParams.get(paramName);
        return result;
    }

    /**
     * @param nodeName Name of XML Node.
     * @return Config value overridden by Test suite XML node value (if available)
     */
    public static String GetSystemData(String nodeName)
    {
        String testParam = GetTestSuiteData(nodeName);
        if(testParam != null)
            return  testParam;
        else
            return GetData(nodeName, "SystemConfig");
    }
}
