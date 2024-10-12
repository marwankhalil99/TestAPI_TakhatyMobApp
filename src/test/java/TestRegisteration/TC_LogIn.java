package TestRegisteration;

import Requests.LoginReq;
import Utils.ResponseBody.LoginResponse;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC_LogIn extends AbstractAPI {
    private LoginReq request = new LoginReq();
    @Test//(dependsOnMethods = {"TestRegisteration.TC_Registeration.registerUser"})
    public void loginUserTest()  {
        System.out.println("Test user Log in process with Valid data ( Positive Scenario )");
        LoginResponse logResObj = request.getLoginResponsePojo(user.getEmail(), user.getPassword(), 200);
        Assert.assertEquals(logResObj.getIsSuccess(), true);
        Assert.assertEquals(logResObj.getStatusCode(),200);
        Assert.assertNotNull(logResObj.getData().getId());
        Assert.assertNotNull(logResObj.getData().getRole());
        Assert.assertNotNull(logResObj.getData().getToken());
        Assert.assertNotNull(logResObj.getData().getEmail());
        Assert.assertNotNull(logResObj.getData().getFirstName());
        Assert.assertNotNull(logResObj.getData().getLastName());
        Assert.assertNotNull(logResObj.getData().getUserName());
        userToken = logResObj.getData().getToken();
        userId = logResObj.getData().getId();
    }
    @Test(dataProvider = "InvalidEmailDataLogin")
    public void loginUserWithInvalidEmail(String email)  {
        System.out.println("Test user Log in process with valid  password and Invalid email ( "+email+" )");
        String loginRes = request.getLoginResponseStr(email , user.getPassword(),400);
        JsonPath loginResJson =  new JsonPath(loginRes);
        if(email.equals(" ")){
            Assert.assertEquals(loginResJson.getString("error.message"),"Email must be provided.");
        }else{
            Assert.assertEquals(loginResJson.getString("error.message"),"Invalid email format.");
        }
    }
    @DataProvider(name="InvalidEmailDataLogin")
    public Object[] InvalidEmailDataLogin() {
        return new Object[]{
                " ", "user", "user12@domain", "user.com", "@domain.com", "marwanshamsooo#gmail.com", "marwanshamsooo@gmail..com", "marwan@$#.com"
                , "&*(@gmail.com"
        };
    }

    @Test(dataProvider = "InvalidPasswordDataLogin")
    public void loginUserWithInvalidPassword(String password)  {
        System.out.println("Test user Log in process with valid Email and Invalid Password ( "+password+" )");
        String loginRes = request.getLoginResponseStr(user.getEmail() , password,400);
        JsonPath loginResJson =  new JsonPath(loginRes);
        if(password.equals(" ")){
            Assert.assertEquals(loginResJson.getString("error.message"),"Password must be provided.");
        }else{
            Assert.assertEquals(loginResJson.getString("error.message"),"Invalid password format.");
        }
    }
    @DataProvider(name="InvalidPasswordDataLogin")
    public Object[] InvalidPasswordDataLogin() {
        return new Object[]{
                " ", " Password12@", "password12@", "PASSWORD12@", "Password12", "Password@", "pass12@", "12345678910", "#@##@#@#@#@#@#@"
                , "password123@password123@password123@pasword123@"
        };
    }
}
