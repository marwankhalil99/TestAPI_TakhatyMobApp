package Requests;

import Utils.ExamBody.ExamBody;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetExamReq {
    private final String pathParameter = "/api/v1/Exams/Search";
    public Response getExam(String token , String examId){
        String examBody = ExamBody.getExamBody(examId);
        return given().headers("Authorization","Bearer "+token ,"Content-Type","application/json").body(examBody)
                .when().post(pathParameter).then().log().body().assertThat().extract().response();
    }
}
