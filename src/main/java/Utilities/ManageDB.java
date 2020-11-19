package Utilities;

import Extensions.ExternalData;

import java.sql.DriverManager;

public class ManageDB extends Base
{

    /**
     * Opens the connection for the database automation
     * @param dbUrl URL of the database
     * @param user User to access the database
     * @param pass Password to access the database
     */
    public static void InitConnection(String dbUrl, String user, String pass)
    {
        if (!Boolean.parseBoolean(ExternalData.GetSystemData("ActivateDB"))) return;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(dbUrl, user, pass);
            dbStatement = dbConnection.createStatement();
        } catch (Exception e)
        {
            throw new RuntimeException("There's been a problem connecting to DB: " + e);
        }
    }

    /**
     * Closes the connection for the database automation.
     */
    public static void CloseConnection()
    {
        if (!Boolean.parseBoolean(ExternalData.GetSystemData("ActivateDB"))) return;
        try
        {
            dbConnection.close();
        } catch (Exception e)
        {
            throw new RuntimeException("There's been a problem closing the connection to the DB: " + e);
        }
    }
}
