package Requests;

import static io.restassured.RestAssured.given;

public class VerifyOtpReq {
    private final String pathParameter = "api/v1/Users/VerifyRegisterOtp";

    public String verifyOtpReq(String email , String otp , int expectedSC ){
        return given().queryParam("email",email).queryParam("otp",otp)
            .when().post(pathParameter).then().log().body().assertThat().statusCode(expectedSC)
            .extract().response().asString();
    }
}
