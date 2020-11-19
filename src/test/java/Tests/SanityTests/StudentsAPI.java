package Tests.SanityTests;

import Extensions.Verifications;
import Utilities.CommonOps;
import WorkFlows.ApiFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.List;

public class StudentsAPI extends CommonOps
{
    String currentId = "101";

    @Test(description = "Verify Student Name")
    @Description("Checking that the value of student 5# name is correct")
    public void Test01_GetStudentName()
    {
        String name = ApiFlows.GetStudentProperty("5", "firstName");
        Verifications.VerifyText(name, "Cullen");
    }

    @Test(description = "Add Student")
    @Description("Adding a new student and verify it")
    public void Test02_AddStudent()
    {
        ApiFlows.CreateStudent("Liad", "Skiva", "liad@skiva.com", "Automation Engineer", new String[]{"CSharp", "Java", "Test Automation", "API"});
        currentId = Integer.toString(ApiFlows.GetLastStudentIndex());
        Verifications.VerifyText(ApiFlows.GetStudentProperty(currentId, "courses[1]"), "Java");
    }

    @Test(description = "Update Student")
    @Description("Updating an existing student and verify it")
    public void Test03_UpdateStudent()
    {
        ApiFlows.UpdateStudent(currentId, "Liad", "Skiva", "liad@skiva.com", "Automation Engineer", new String[]{"CSharp", "Python", "Test Automation", "API"});
        Verifications.VerifyText(ApiFlows.GetStudentProperty(currentId, "courses[1]"), "Python");
    }

    @Test(description = "Delete Student")
    @Description("Deleting a student and verify it")
    public void Test04_UpdateStudent()
    {
        ApiFlows.DeleteStudent(currentId);
        Verifications.VerifyText(ApiFlows.GetStudentProperty(currentId, "error"), "Not Found!");
    }

    @Test(description = "Verify No Duplicate Mails")
    @Description("Add a new record with an existing mail and ensuring that there are no identical mails")
    public void Test05_CheckNoIdenticalMails()
    {
        String existingMail = ApiFlows.GetStudentProperty("5", "email");
        ApiFlows.CreateStudent("LiadTest", "SkivaTest", existingMail, "Automation Engineer", new String[]{"CSharp", "Java", "Test Automation", "API"});
        List<String> emails = ApiFlows.GetStudentStringList("email");
        Verifications.VerifyUniqueList(emails);
    }
}
