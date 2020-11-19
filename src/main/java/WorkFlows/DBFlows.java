package WorkFlows;

import Extensions.DbActions;
import Utilities.Base;
import Utilities.CommonOps;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBFlows extends CommonOps
{
    @Step("Get Pressure Conversion Values From The Database")
    public static String[] GetPressureConversionValues(int id)
    {
        ResultSet resultSet = DbActions.GetDBData("SELECT bar, kg_cm, psi FROM Pressure_Conversion WHERE id='" + id + "'");
        String[] conversion = new String[3];
        try
        {
            resultSet.next();
            for (int i = 0; i < conversion.length; i++)
            {
                conversion[i] = resultSet.getString(i + 1);
            }
        } catch (java.sql.SQLException sqlException)
        {
            throw new RuntimeException("There's been a problem getting info from the DB: " + sqlException);
        }
        return conversion;
    }
}
