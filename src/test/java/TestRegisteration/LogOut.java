package TestRegisteration;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogOut extends AbstractAPI{
    public void logOut(String token) {
        RestAssured.baseURI = endPoint;
         given().header("Authorization","Bearer "+token)
                .when().post("/api/v1/Users/Logout").then().log().body().assertThat().statusCode(200);
        System.out.println("{\n\tLogged out\n}");
    }
    @Test
    public void testLogOut() {
        RestAssured.baseURI = endPoint;
        LogIn lg = new LogIn();
        String token = lg.loginUser();
        given().header("Authorization","Bearer "+token)
                .when().post("/api/v1/Users/Logout").then().log().body().assertThat().statusCode(200).extract().response().asString();
        System.out.println("{\n\tLogged out\n}");
    }
    @Test
    public void logOutTestInvalidToken() {
        RestAssured.baseURI = endPoint;

        String logOutRes = given().header("Authorization","Bearer "+"InvalidToken")
                .when().post("/api/v1/Users/Logout").then().log().body().assertThat().statusCode(200).extract().response().asString();
        System.out.println("{\n\tLogged out\n}");
    }
}
