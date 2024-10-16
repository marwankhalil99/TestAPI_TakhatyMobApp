package E2E_Test;

import Requests.LoginReq;
import Requests.RegisterationReq;
import TestRegisteration.*;
import Utils.ResponseBody.LoginResponse;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class E2EScenario extends AbstractAPI{
    @Test(priority = 1)
    public void createAccAndDoExam() throws InterruptedException {
        TC_Registeration reg = new TC_Registeration();
        TC_VerifyRegOTP vt = new TC_VerifyRegOTP();
        TC_LogIn lg = new TC_LogIn();
        TC_LogOut TCLogOut = new TC_LogOut();
        TC_GetExam tcGetExam = new TC_GetExam();
        TC_EvaluateExam tcEvaluateExam = new TC_EvaluateExam();
        reg.registerUser();
        Thread.sleep(1000);
        vt.testVerifyRegisterOTP();
        lg.loginUserTest();
        tcGetExam.getExamTest();
        tcEvaluateExam.evaluateExamTestHighestScore();
        TCLogOut.testLogOut();
    }
    @Test(priority = 2)
    public void createUserThenLoginWithoutOTP(){
        RegisterationReq registerationReq = new RegisterationReq();
        LoginReq loginReq = new LoginReq();
        registerationReq.registerationReqset(user.getUserRole(),user.getPassword(),user.getEmail(),user.getFirstName()
                ,user.getLastName(),user.getCountry(),user.getCity(),user.getAddress(),user.getGender(),user.getPhoneNumber(), 200);
        String response = loginReq.getLoginResponseStr(user.getEmail(),user.getPassword(),423);
        JsonPath resJson = new JsonPath(response);
        Assert.assertEquals(resJson.getString("error.message"),"Email Not Confirmed. Please Confirm Your Email First");
        TC_VerifyRegOTP vt = new TC_VerifyRegOTP();
        vt.testVerifyRegisterOTP();
    }
    @AfterMethod
    public void deleteAcc(){
        TC_GetUser getUser = new TC_GetUser();
        LoginReq loginReq = new LoginReq();
        TC_RemoveUser removeUser = new TC_RemoveUser();
        LoginResponse logResObj = loginReq.getLoginResponsePojo(user.getEmail(),user.getPassword(),200);
        userToken = logResObj.getData().getToken();
        userId = logResObj.getData().getId();
        removeUser.testRemoveUser();
        getUser.testGetRemoverUser();
    }
}
