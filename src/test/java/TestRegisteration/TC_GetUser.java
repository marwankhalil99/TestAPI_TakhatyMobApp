package TestRegisteration;

import Requests.GetUserReq;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC_GetUser extends AbstractAPI{
    GetUserReq request = new GetUserReq();
    @Test(dependsOnMethods = {"TestRegisteration.TC_LogIn.loginUserTest"})
    public void testGetUser() {
        String respones = request.getUserData(userId , 200);
        JsonPath resJson = new JsonPath(respones);
        Assert.assertNotNull(resJson.getString("data.id"));
    }
    @Test(dependsOnMethods = {"TestRegisteration.TC_RemoveUser.testRemoveUser"})
    public void testGetRemoverUser() {
        String respones = request.getUserData(userId , 400);
        JsonPath resJson = new JsonPath(respones);
        Assert.assertNull(resJson.get("data"));
        Assert.assertEquals(resJson.getString("error.message"),"User not found");

    }
}
