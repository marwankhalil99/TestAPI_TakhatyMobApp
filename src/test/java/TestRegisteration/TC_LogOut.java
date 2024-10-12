package TestRegisteration;

import Requests.LogOutReq;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC_LogOut extends AbstractAPI{
    LogOutReq request = new LogOutReq();
    @Test(dependsOnMethods = {"TestRegisteration.TC_LogIn.loginUserTest"})
    public void testLogOut() {
        request.logout(userToken,200);
        System.out.println("{\n\tLogged out\n}");
    }
}
