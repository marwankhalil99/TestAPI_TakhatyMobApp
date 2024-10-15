package TestRegisteration;

import Requests.EvaluateExamReq;
import Requests.LoginReq;
import Utils.ResponseBody.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static TestRegisteration.AbstractAPI.*;

public class TC_EvaluateExam extends AbstractAPI {
    EvaluateExamReq request = new EvaluateExamReq();
//    @BeforeSuite
//    public void loginUserTest()  {
//        LoginReq request = new LoginReq();
//        LoginResponse logResObj = request.getLoginResponsePojo(user.getEmail(), user.getPassword(), 200);
//        userToken = logResObj.getData().getToken();
//        userId = logResObj.getData().getId();
//    }
    @Test//(dependsOnMethods = {"TestRegisteration.TC_GetExam.getExamTest"})
    public void evaluateExamTestHighestScore() {
        Response response = request.evaluateExam(userToken , examID , Q1Answers.get(0), Q2Answers.get(0));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getFloat("data.totalScore"),8);
        Assert.assertEquals(response.jsonPath().getFloat("data.percentage"),100);
        Assert.assertEquals(response.jsonPath().getString("data.message"),"اكتئاب شديد");
        Assert.assertNotNull(response.jsonPath().get("data.recommendedDoctors"));
    }
    @Test//(dependsOnMethods = {"TestRegisteration.TC_GetExam.getExamTest"})
    public void evaluateExamTestLowestScore() {
        Response response = request.evaluateExam(userToken , examID , Q1Answers.get(3), Q2Answers.get(3));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getFloat("data.totalScore"),2);
        Assert.assertEquals(response.jsonPath().getFloat("data.percentage"),25);
        Assert.assertEquals(response.jsonPath().getString("data.message"),"اكتئاب أقل من المتوسط");
        Assert.assertNull(response.jsonPath().get("data.recommendedDoctors[0]"));

    }
}
