package Requests;

import Utils.LoginUser.UserClass;
import Utils.ResponseBody.LoginResponse;

import static io.restassured.RestAssured.given;

public class LoginReq {
    private final String pathParameter = "api/v1/Users/Login";
    UserClass POJOuser;
    public LoginResponse getLoginResponsePojo(String email , String password , int expectedSC){
        POJOuser = new UserClass();
        POJOuser.setEmail(email);
        POJOuser.setPassword(password);
        return given().header("Content-Type", "application/json").header("Accept","text/plain")
                .body(POJOuser).when().post(pathParameter).then().log().body()
                .assertThat().statusCode(expectedSC).extract().response().as(LoginResponse.class);
    }

    public String getLoginResponseStr(String email , String password , int expectedSC){
        POJOuser = new UserClass();
        POJOuser.setEmail(email);
        POJOuser.setPassword(password);
        return given().header("Content-Type", "application/json").header("Accept","text/plain")
                .body(POJOuser).when().post(pathParameter).then().log().body()
                .assertThat().statusCode(expectedSC).extract().response().asString();
    }
}
