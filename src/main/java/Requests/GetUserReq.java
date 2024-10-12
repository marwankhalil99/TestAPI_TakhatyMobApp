package Requests;

import static io.restassured.RestAssured.given;

public class GetUserReq {
    private final String pathParameter = "api/v1/Users/{id}";
    public String getUserData(String id, int expectedSC){
        return given().pathParams("id",id)
                .when().get(pathParameter).then().log().body().assertThat().statusCode(expectedSC)
                .extract().response().asString();
    }
}
