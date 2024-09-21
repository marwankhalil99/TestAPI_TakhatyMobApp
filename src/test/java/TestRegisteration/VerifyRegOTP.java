package TestRegisteration;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import Registeration.ClientUser;
import static io.restassured.RestAssured.given;

public class VerifyRegOTP extends AbstractAPI {
    private String pathParameter = "api/v1/Users/VerifyRegisterOtp";

    public void VerifiyRegisterOTP() {

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter OTP Received on your Email:");
//        String otp = scanner.nextLine();
        ClientUser user1 = new ClientUser(user);

        String otp = "852524";
        RestAssured.baseURI = endPoint;
        String VerifyOtpStr = given().queryParam("email",user.getEmail()).queryParam("otp",otp)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath VerifyOtpJson = new JsonPath(VerifyOtpStr);
        Assert.assertEquals(VerifyOtpJson.getBoolean("isSuccess"), true);
        Assert.assertEquals(VerifyOtpJson.getString("data"), "Your Email Confirmed Correctly Pleas Try Login");
    }

    public void VerifiyRegisterOTP(String OTP) {
        pathParameter = "api/v1/Users/VerifyRegisterOtp";
        ClientUser user1 = new ClientUser(user);
        String otp = OTP;
        RestAssured.baseURI = endPoint;
        String VerifyOtpStr = given().queryParam("email",user.getEmail()).queryParam("otp",otp)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath VerifyOtpJson = new JsonPath(VerifyOtpStr);
        Assert.assertEquals(VerifyOtpJson.getBoolean("isSuccess"), true);
        Assert.assertEquals(VerifyOtpJson.getString("data"), "Your Email Confirmed Correctly Pleas Try Login");
    }
}
