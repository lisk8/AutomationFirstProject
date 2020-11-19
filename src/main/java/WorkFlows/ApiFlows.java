package WorkFlows;

import Extensions.ApiActions;
import Utilities.CommonOps;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

public class ApiFlows extends CommonOps
{
    @Step("Get and Extract Property From JSON API")
    public static String GetStudentProperty(String studentId, String jsonPath)
    {
        Response currentResponse = ApiActions.Get("/student/" + studentId);
        return ApiActions.ExtractFromJSON(currentResponse, jsonPath);
    }

    @Step("Get Student List Response From JSON API")
    public static Response GetStudentListResponse()
    {
        Response currentResponse = ApiActions.Get("/student/list");
        return currentResponse;
    }

    @Step("Get List of Students (String)")
    public static List<String> GetStudentStringList(String jsonPath)
    {
        Response currentResponse = GetStudentListResponse();
        List<String> result = ApiActions.ExtractStringListFromJSON(currentResponse,jsonPath);
        return result;
    }

    @Step("Get List of Students (Integer)")
    public static List<Integer> GetStudentIntList(String jsonPath)
    {
        Response currentResponse = GetStudentListResponse();
        List<Integer> result = ApiActions.ExtractIntListFromJSON(currentResponse,jsonPath);
        return result;
    }

    @Step("Get Last Student Index")
    public static int GetLastStudentIndex()
    {
        List<Integer> indexes = GetStudentIntList("id");
        return indexes.get(indexes.size() - 1);
    }

    @Step("Create New Student")
    public static Response CreateStudent(String firstName, String lastName, String email, String programme, String[] courses)
    {
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("email", email);
        requestParams.put("programme", programme);
        for (String course : courses)
        {
            requestParamsArray.add(course);
        }
        requestParams.put("courses", requestParamsArray);
        Response currentResponse = ApiActions.Post("/student",requestParams);
        requestParams.clear();
        requestParamsArray.clear();
        return currentResponse;
    }

    @Step("Update Existing Student")
    public static Response UpdateStudent(String studentId, String firstName, String lastName, String email, String programme, String[] courses)
    {
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("email", email);
        requestParams.put("programme", programme);
        for (String course : courses)
        {
            requestParamsArray.add(course);
        }
        requestParams.put("courses", requestParamsArray);
        Response currentResponse = ApiActions.Put(studentId, requestParams);
        requestParams.clear();
        requestParamsArray.clear();
        return currentResponse;
    }

    @Step("Delete Student")
    public static Response DeleteStudent(String studentId)
    {
        Response currentResponse = ApiActions.Delete(studentId);
        return currentResponse;
    }
}
