package Requests;

import static io.restassured.RestAssured.given;

public class LogOutReq {
    private final String pathParameter = "/api/v1/Users/Logout";
    public void logout(String token , int expectedSC){
        given().header("Authorization","Bearer "+token)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(expectedSC);
    }
}
