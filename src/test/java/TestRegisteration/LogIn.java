package TestRegisteration;

import Login.ResponseBody.LoginResponse;
import Login.LoginUser.UserClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import Registeration.ClientUser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LogIn extends AbstractAPI {
    private String pathParameter = "api/v1/Users/Login";
    public String loginUser()  {
        System.out.println("Test user Log in process with Valid data ( Positive Scenario )");
        ClientUser user1 = new ClientUser(user);
        RestAssured.baseURI = endPoint;
        UserClass POJOuser = new UserClass();
        POJOuser.setEmail(user1.getEmail());
        POJOuser.setPassword(user1.getPassword());
        LoginResponse logResObj = given().header("Content-Type", "application/json").header("Accept","text/plain")
                .body(POJOuser).when().post(pathParameter).then().log().body()
                .assertThat().statusCode(200).extract().response().as(LoginResponse.class);
        Assert.assertEquals(logResObj.getIsSuccess(), true);
        Assert.assertEquals(logResObj.getStatusCode(),200);
        return logResObj.getData().getToken();
    }
    @Test
    public void loginUserTest()  {
        System.out.println("Test user Log in process with Valid data ( Positive Scenario )");
        ClientUser user1 = new ClientUser(user);
        RestAssured.baseURI = endPoint;
        UserClass POJOuser = new UserClass();
        POJOuser.setEmail(user1.getEmail());
        POJOuser.setPassword(user1.getPassword());
        LoginResponse logResObj = given().header("Content-Type", "application/json").header("Accept","text/plain")
                .body(POJOuser).when().post(pathParameter).then().log().body()
                .assertThat().statusCode(200).extract().response().as(LoginResponse.class);
        Assert.assertEquals(logResObj.getIsSuccess(), true);
        Assert.assertEquals(logResObj.getStatusCode(),200);
        Assert.assertNotNull(logResObj.getData().getId());
        Assert.assertNotNull(logResObj.getData().getRole());
        Assert.assertNotNull(logResObj.getData().getToken());
        Assert.assertNotNull(logResObj.getData().getToken());
        Assert.assertNotNull(logResObj.getData().getEmail());
        Assert.assertNotNull(logResObj.getData().getFirstName());
        Assert.assertNotNull(logResObj.getData().getLastName());
        Assert.assertNotNull(logResObj.getData().getUserName());
    }
    @Test(dataProvider = "InvalidEmailDataLogin")
    public void loginUserWithInvalidEmail(String email)  {
        System.out.println("Test user Log in process with valid  password and Invalid email ( "+email+" )");
        RestAssured.baseURI = endPoint;
        String pathParameter = "api/v1/Users/Login";
        UserClass POJOUser = new UserClass();
        POJOUser.setEmail(email);
        POJOUser.setPassword(user.getPassword());
        String loginRes = given().header("Content-Type", "application/json").header("Accept","text/plain")
                .body(POJOUser).when().post(pathParameter).then().log().body()
                .assertThat().statusCode(400).extract().response().asString();
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
        RestAssured.baseURI = endPoint;
        String pathParameter = "api/v1/Users/Login";
        UserClass POJOUser = new UserClass();
        POJOUser.setEmail(user.getEmail());
        POJOUser.setPassword(password);
        String loginRes = given().header("Content-Type", "application/json").header("Accept","text/plain")
                .body(POJOUser).when().post(pathParameter).then().log().body()
                .assertThat().statusCode(400).extract().response().asString();
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
