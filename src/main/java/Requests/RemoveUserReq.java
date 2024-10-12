package Requests;

import static io.restassured.RestAssured.given;

public class RemoveUserReq {
    private final String pathParameter = "api/v1/Users/RemoveUser/{userId}";
    public String removeUserReq(String id , String token , int expectedSC){
        return given().header("Authorization","Bearer "+token)
                .pathParam("userId",id)
                .when().delete(pathParameter).then().assertThat().statusCode(expectedSC).extract().response().asString();
    }
}
