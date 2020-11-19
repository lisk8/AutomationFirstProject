package Extensions;

import Utilities.CommonOps;
import io.qameta.allure.Step;

import java.sql.ResultSet;

public class DbActions extends CommonOps
{
    @Step("Get Data From Database")
    public static ResultSet GetDBData(String query)
    {
        try
        {
            ResultSet resultSet = dbStatement.executeQuery(query);
            return resultSet;
        } catch (Exception e)
        {
            throw new RuntimeException("There's been a problem sending query to DB: " + e);
        }
    }
}
