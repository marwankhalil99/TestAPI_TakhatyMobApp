package Requests;

import static io.restassured.RestAssured.given;

public class RegisterationReq {
    private final String pathParameter = "api/v1/Users";

    public String registerationReqset(int role , String password , String email , String fname , String lname , String country
            , String city , String address , int gender , String phone , int expectedSC){
        return given().header("Accept","text/plain")
                .formParam("UserRole",role)
                .formParam("UserData.Password", password)
                .formParam("UserData.Email", email)
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", fname)
                .formParam("UserData.LastName", lname)
                .formParam("UserData.Country", country)
                .formParam("UserData.City", city)
                .formParam("UserData.Address", address)
                .formParam("UserData.Gender", gender)
                .formParam("UserData.PhoneNumber", phone)
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(expectedSC)
                .extract().response().asString();
    }
}
