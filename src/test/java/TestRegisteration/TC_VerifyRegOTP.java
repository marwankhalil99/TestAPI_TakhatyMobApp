package TestRegisteration;
import Requests.VerifyOtpReq;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import Utils.EmailReader;

public class TC_VerifyRegOTP extends AbstractAPI {
    VerifyOtpReq requset = new VerifyOtpReq();
    @Test
    public void testVerifyRegisterOTP() {
        System.out.println("Test vreify account using OTP with Valid data ( Positive Scenario )");
        String otp = EmailReader.getOTPFromEmail();
        System.out.println(otp);
        String VerifyOtpStr = requset.verifyOtpReq(user.getEmail(),otp,200);
        JsonPath VerifyOtpJson = new JsonPath(VerifyOtpStr);
        Assert.assertEquals(VerifyOtpJson.getBoolean("isSuccess"), true);
        Assert.assertEquals(VerifyOtpJson.getString("data"), "Your Email Confirmed Correctly Pleas Try Login");
    }
    @Test
    public void testVerifyRegisterOTPWithInvalidOTP() {
        System.out.println("Test vreify account using OTP with invalid Otp ( 000000 )");
        String VerifyOtpStr = requset.verifyOtpReq(user.getEmail(),"000000",400);
        JsonPath VerifyOtpJson = new JsonPath(VerifyOtpStr);
        Assert.assertEquals(VerifyOtpJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(VerifyOtpJson.getString("error.message"), "Invalid OTP");
    }

}
