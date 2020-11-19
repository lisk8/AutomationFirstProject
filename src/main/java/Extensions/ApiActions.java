package Extensions;

import Utilities.CommonOps;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.List;

public class ApiActions extends CommonOps
{
    @Step("Get Data From API")
    public static Response Get(String urlParams)
    {
        Response currentResponse = httpRequest.get(urlParams);
        return currentResponse;
    }

    @Step("Extract Value From JSON Format")
    public static String ExtractFromJSON(Response response, String jPath)
    {
        jp = response.jsonPath();
        return jp.get(jPath).toString();
    }

    @Step("Extract List Values From JSON Format")
    public static List<String> ExtractStringListFromJSON(Response response, String jPath)
    {
        jp = response.jsonPath();
        return jp.getList(jPath);
    }

    @Step("Extract List Values From JSON Format")
    public static List<Integer> ExtractIntListFromJSON(Response response, String jPath)
    {
        jp = response.jsonPath();
        return jp.getList(jPath);
    }

    @Step("Post Data To API")
    public static Response Post(String urlParams, JSONObject JsonParams)
    {
        httpRequest.body(JsonParams.toJSONString());
        Response currentResponse = httpRequest.post(urlParams);
        return currentResponse;
    }

    @Step("Update Data In API")
    public static Response Put(String studentId, JSONObject JsonParams)
    {
        httpRequest.body(JsonParams.toJSONString());
        Response currentResponse = httpRequest.put("/student/" + studentId);
        return currentResponse;
    }

    @Step("Delete Data From API")
    public static Response Delete(String studentId)
    {
        Response currentResponse = httpRequest.delete("/student/" + studentId);
        return currentResponse;
    }
}
