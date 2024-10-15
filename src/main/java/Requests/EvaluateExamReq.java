package Requests;

import Utils.ExamBody.ExamBody;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EvaluateExamReq {
    private final String pathParameter = "/api/v1/Exams/evaluate";
    public Response evaluateExam(String token , String examId , String answer1 , String answer2 ){
        String evaluateExamBody = ExamBody.evaluateExamBody(examId,answer1,answer2);
        return given().headers("Authorization","Bearer "+token ,"Content-Type","application/json").body(evaluateExamBody)
                .when().post(pathParameter).then().log().body().assertThat().extract().response();
    }
}
