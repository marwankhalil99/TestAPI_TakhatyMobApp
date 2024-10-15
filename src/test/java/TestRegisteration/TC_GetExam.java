package TestRegisteration;

import Requests.GetExamReq;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TC_GetExam extends AbstractAPI {
    GetExamReq request = new GetExamReq();

    @Test
    public void getExamTest(){
        Response response = request.getExam(userToken, examID);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("data.items[0].id"),examID);
        for(int i = 0; i < 4 ; i++) {

            Q1Answers.add(response.jsonPath().getString("data.items[0].questions[0].answer["+i+"].answerID"));
            Assert.assertNotNull(Q1Answers.get(i));
            System.out.println("Question 1 Answer "+ (i+1) +" : "+Q1Answers.get(i));
        }
        for(int i = 0; i < 4 ; i++) {
            Q2Answers.add(response.jsonPath().getString("data.items[0].questions[1].answer["+i+"].answerID"));
            Assert.assertNotNull(Q2Answers.get(i));
            System.out.println("Question 2 Answer "+ (i+1) +" : "+Q2Answers.get(i));
        }
    }
    @Test(dataProvider = "getExamInvalidData")
    public void getExamTestWithInvalidExamID(String examId){
        Response response = request.getExam(userToken, examId);
        Assert.assertEquals(response.statusCode(),400);
    }
    @DataProvider(name="getExamInvalidData")
    public Object[] getExamInvalidData(){
        return new Object[]{
                "", null
        };
    }
}
