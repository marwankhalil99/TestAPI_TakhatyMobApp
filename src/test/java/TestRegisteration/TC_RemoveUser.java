package TestRegisteration;

import Requests.RemoveUserReq;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC_RemoveUser extends AbstractAPI {
    RemoveUserReq request = new RemoveUserReq();
    @Test(dependsOnMethods = {"TestRegisteration.TC_LogIn.loginUserTest"})
    public void testRemoveUser() {
        System.out.println("Remove User!");
        String res = request.removeUserReq(userId, userToken,200);
        JsonPath resJson = new JsonPath(res);
        Assert.assertEquals(resJson.getString("data"), "Removed Successfully");
        //System.out.println("User removed successfully");
    }
}
