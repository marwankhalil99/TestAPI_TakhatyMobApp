package TestRegisteration;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Registeration.ClientUser;


import java.util.Scanner;

public class Registeration extends AbstractAPI{
    private final String pathParameter = "api/v1/Users";
    @Test
    public void registerUser() {
        // Write your test case here
        ClientUser user1 = new ClientUser(user);
        System.out.println("Test user registration process with Valid data ( Positive Scenario )");
        RestAssured.baseURI = endPoint;
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",user1.getUserRole())
                .formParam("UserData.Password", user1.getPassword())
                .formParam("UserData.Email", user1.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", user1.getFirstName())
                .formParam("UserData.LastName", user1.getLastName())
                .formParam("UserData.Country", user1.getCountry())
                .formParam("UserData.City", user1.getCity())
                .formParam("UserData.Address", user1.getAddress())
                .formParam("UserData.Gender", user1.getGender())
                .formParam("UserData.PhoneNumber", user1.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), true);
        Assert.assertEquals(regResJson.getInt("statusCode"), 200);
        Assert.assertEquals(regResJson.getString("data.validationMessage"), "Check Your Email We Have Sent To You OTP To Confirm Your Email");
        Assert.assertEquals(regResJson.getBoolean("data.isValid"), true);
    }
    @Test(dataProvider = "InvalidEmailData")
    public void registerUserWithInvalidEmail(String email) {
        ClientUser userTest = new ClientUser(user);
        System.out.println("Test user registration process with valid role, password, firstname, lastname, country, city, address, gender, phone number and Invalid email ( "+email+" )");
        RestAssured.baseURI = endPoint;
        userTest.setEmail(email);
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",userTest.getUserRole())
                .formParam("UserData.Password", userTest.getPassword())
                .formParam("UserData.Email", userTest.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", userTest.getFirstName())
                .formParam("UserData.LastName", userTest.getLastName())
                .formParam("UserData.Country", userTest.getCountry())
                .formParam("UserData.City", userTest.getCity())
                .formParam("UserData.Address", userTest.getAddress())
                .formParam("UserData.Gender", userTest.getGender())
                .formParam("UserData.PhoneNumber", userTest.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(400)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(regResJson.getInt("statusCode"), 400);
        if(email.equals(" "))
        {
            Assert.assertEquals(regResJson.getString("error.message"), "Email is required.");
        }
        else if(email.equals(user.getEmail())){
            Assert.assertEquals(regResJson.getString("error.message"), "Email already used");
        }
        else {
            Assert.assertEquals(regResJson.getString("error.message"), "Invalid email format.");
        }
    }
    @DataProvider(name="InvalidEmailData")
    public Object[] InvalidEmailData() {
        return new Object[]{
               this.user.getEmail()," ","user","user12@domain","user.com","@domain.com","marwanshamsooo#gmail.com","marwanshamsooo@gmail..com","marwan@$#.com"
                ,"&*(@gmail.com"
        };
    }

    @Test(dataProvider = "InvalidPasswordData")
    public void registerUserWithInvalidPassword(String email , String password) {
        ClientUser userTest = new ClientUser(user);
        // Write your test case here
        System.out.println("Test user registration process with valid role, email, firstname, lastname, country, city, address, gender, phone number and Invalid password ( "+password+" )");
        RestAssured.baseURI = endPoint;
        userTest.setEmail(email);
        userTest.setPassword(password);
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",userTest.getUserRole())
                .formParam("UserData.Password", userTest.getPassword())
                .formParam("UserData.Email", userTest.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", userTest.getFirstName())
                .formParam("UserData.LastName", userTest.getLastName())
                .formParam("UserData.Country", userTest.getCountry())
                .formParam("UserData.City", userTest.getCity())
                .formParam("UserData.Address", userTest.getAddress())
                .formParam("UserData.Gender", userTest.getGender())
                .formParam("UserData.PhoneNumber", userTest.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(400)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(regResJson.getInt("statusCode"), 400);
        if(password.equals(" "))
        {
            Assert.assertEquals(regResJson.getString("error.message"), "Password is required.");
        }else
        {
            Assert.assertEquals(regResJson.getString("error.message"), "Password must contain at least one uppercase letter, one lowercase letter, one digit, one of the following special characters: @$!%*?&, and be at least 8 characters long.");
        }


    }
    @DataProvider(name="InvalidPasswordData")
    public Object[][] InvalidPasswordData() {
        return new Object[][]{
                {"mgfmm@gmail.com"," "},{"mgfmm@gmail.com"," Password12@"},{"mmmmgfmm@gmail.com","password12@"}, {"mmmmmmdfgm@gmail.com","PASSWORD12@"}, {"mmdgmmmmmm@gmail.com","Password12"},
                        {"mmdfmmmmmm@gmail.com","Password@"}, {"mfdmmamm@gmail.com","pass12@"}, {"mmmaafdmm@gmail.com","12345678910"}, {"mmfdmaamm@gmail.com","#@##@#@#@#@#@#@"},
                        {"mmcmbmm@gmail.com","password123@password123@password123@pasword123@"}
        };
    }
    @Test(dataProvider = "InvalidNameData")
    public void registerUserWithInvalidName(String email , String phone , String fname , String lname) {
        ClientUser userTest = new ClientUser(user);
        // Write your test case here
        System.out.println("Test user registration process with valid role, email, password, country, city, address, gender, phone number and Invalid first name and last name ( "+fname+" "+lname+" )");
        RestAssured.baseURI = endPoint;
        userTest.setEmail(email);
        userTest.setPhoneNumber(phone);
        userTest.setFirstName(fname);
        userTest.setLastName(lname);
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",userTest.getUserRole())
                .formParam("UserData.Password", userTest.getPassword())
                .formParam("UserData.Email", userTest.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", userTest.getFirstName())
                .formParam("UserData.LastName", userTest.getLastName())
                .formParam("UserData.Country", userTest.getCountry())
                .formParam("UserData.City", userTest.getCity())
                .formParam("UserData.Address", userTest.getAddress())
                .formParam("UserData.Gender", userTest.getGender())
                .formParam("UserData.PhoneNumber", userTest.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(400)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(regResJson.getInt("statusCode"), 400);
        if(fname.equals(" ") && lname.equals(" ")){
            Assert.assertEquals(regResJson.getString("error.message"), "First name is required.");
        }else{
            Assert.assertEquals(regResJson.getString("error.message"), "Invalid first name format.");
        }

    }
    @DataProvider(name="InvalidNameData")
    public Object[][] InvalidNameData() {
        return new Object [][]{
                {"mmmm@yahoo.com","+201021331325"," "," "},{"mmsmm@yahoo.com","+201021331324","&^%@#^@$#&%","@&%$&@%@&"},{"mmamm@yahoo.com","+201021331335","a","a"},{"mmmasm@yahoo.com","+201021331425","12131","31515"}
                ,{"mmasdmm@yahoo.com","+201021031325","mar wan","kha lil"},{"mmdasmm@yahoo.com","+201121331325","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}
        };
    }
    @Test(dataProvider = "InvalidCountryData")
    public void registerUserWithInvalidCountry(String country) {
        ClientUser userTest = new ClientUser(user);
        // Write your test case here
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, city, address, gender, phone number and Invalid country ( "+country+" )");
        RestAssured.baseURI = endPoint;
        userTest.setCountry(country);
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",userTest.getUserRole())
                .formParam("UserData.Password", userTest.getPassword())
                .formParam("UserData.Email", userTest.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", userTest.getFirstName())
                .formParam("UserData.LastName", userTest.getLastName())
                .formParam("UserData.Country", userTest.getCountry())
                .formParam("UserData.City", userTest.getCity())
                .formParam("UserData.Address", userTest.getAddress())
                .formParam("UserData.Gender", userTest.getGender())
                .formParam("UserData.PhoneNumber", userTest.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(400)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(regResJson.getInt("statusCode"), 400);
        if(country.equals(" "))
        {
            Assert.assertEquals(regResJson.getString("error.message"), "Country is required.");
        }else {
            Assert.assertEquals(regResJson.getString("error.message"), "Invalid country format.");
        }
    }
    @DataProvider(name="InvalidCountryData")
    public Object[]InvalidCountryData() {
        return new Object[]{
                " ","e","#@#$@","1234567"
        };
    }

    @Test(dataProvider = "InvalidCityData")
    public void registerUserWithInvalidCity(String city) {
        ClientUser userTest = new ClientUser(user);
        // Write your test case here
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, country, address, gender, phone number and Invalid city ( "+city+" )");
        RestAssured.baseURI = endPoint;
        userTest.setCity(city);
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",userTest.getUserRole())
                .formParam("UserData.Password", userTest.getPassword())
                .formParam("UserData.Email", userTest.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", userTest.getFirstName())
                .formParam("UserData.LastName", userTest.getLastName())
                .formParam("UserData.Country", userTest.getCountry())
                .formParam("UserData.City", userTest.getCity())
                .formParam("UserData.Address", userTest.getAddress())
                .formParam("UserData.Gender", userTest.getGender())
                .formParam("UserData.PhoneNumber", userTest.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(400)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(regResJson.getInt("statusCode"), 400);
        Assert.assertEquals(regResJson.getString("error.message"), "Invalid city format.");

    }
    @DataProvider(name="InvalidCityData")
    public Object[] InvalidCityData() {
        return new Object[]{
                "c","cityyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy","#@#$@","1234567"
        };
    }

    @Test(dataProvider = "InvalidAddressData")
    public void registerUserWithInvalidAddress(String address) {
        ClientUser userTest = new ClientUser(user);
        // Write your test case here
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, country, city, gender, phone number and Invalid address ( "+address+" )");
        RestAssured.baseURI = endPoint;
        userTest.setAddress(address);
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",userTest.getUserRole())
                .formParam("UserData.Password", userTest.getPassword())
                .formParam("UserData.Email", userTest.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", userTest.getFirstName())
                .formParam("UserData.LastName", userTest.getLastName())
                .formParam("UserData.Country", userTest.getCountry())
                .formParam("UserData.City", userTest.getCity())
                .formParam("UserData.Address", userTest.getAddress())
                .formParam("UserData.Gender", userTest.getGender())
                .formParam("UserData.PhoneNumber", userTest.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(400)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(regResJson.getInt("statusCode"), 400);
        Assert.assertEquals(regResJson.getString("error.message"), "Invalid address format.");
    }
    @DataProvider(name="InvalidAddressData")
    public Object[] InvalidAddressData() {
        return new Object[]{
                "#@#$@","1234567"
        };
    }
    @Test(dataProvider = "InvalidGenderData")
    public void registerUserWithInvalidGender(int gender) {
        ClientUser userTest = new ClientUser(user);
        // Write your test case here
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, country, address, city, phone number and Invalid gender ( "+gender+" )");
        RestAssured.baseURI = endPoint;
        userTest.setGender(gender);
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",userTest.getUserRole())
                .formParam("UserData.Password", userTest.getPassword())
                .formParam("UserData.Email", userTest.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", userTest.getFirstName())
                .formParam("UserData.LastName", userTest.getLastName())
                .formParam("UserData.Country", userTest.getCountry())
                .formParam("UserData.City", userTest.getCity())
                .formParam("UserData.Address", userTest.getAddress())
                .formParam("UserData.Gender", userTest.getGender())
                .formParam("UserData.PhoneNumber", userTest.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(400)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(regResJson.getInt("statusCode"), 400);
        Assert.assertEquals(regResJson.getString("error.message"), "The value '"+gender+"' is invalid.");

    }
    @DataProvider(name="InvalidGenderData")
    public Object[] InvalidGenderData() {
        return new Object[]{
                -1,3
        };
    }
    @Test(dataProvider = "InvalidPhoneNumberData")
    public void registerUserWithInvalidPhoneNumber(String phoneNumber) {
        ClientUser userTest = new ClientUser(user);
        // Write your test case here
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, country, address, gender, city and Invalid phone number ( "+phoneNumber+" )");
        RestAssured.baseURI = endPoint;
        userTest.setPhoneNumber(phoneNumber);
        String regResponseStr = given().header("Accept","text/plain")
                .formParam("UserRole",userTest.getUserRole())
                .formParam("UserData.Password", userTest.getPassword())
                .formParam("UserData.Email", userTest.getEmail())
                .formParam("UserData.AcceptNewsLetters", false)
                .formParam("UserData.FirstName", userTest.getFirstName())
                .formParam("UserData.LastName", userTest.getLastName())
                .formParam("UserData.Country", userTest.getCountry())
                .formParam("UserData.City", userTest.getCity())
                .formParam("UserData.Address", userTest.getAddress())
                .formParam("UserData.Gender", userTest.getGender())
                .formParam("UserData.PhoneNumber", userTest.getPhoneNumber())
                .formParam("UserData.TwoFactorEnabled", false)
                .when().post(pathParameter).then().log().body().assertThat().statusCode(400)
                .extract().response().asString();
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), false);
        Assert.assertEquals(regResJson.getInt("statusCode"), 400);
        if(phoneNumber.equals(" ")){
            Assert.assertEquals(regResJson.getString("error.message"), "Phone number is required.");
        }else{
            Assert.assertEquals(regResJson.getString("error.message"), "Invalid phone number format.");
        }

    }
    @DataProvider(name="InvalidPhoneNumberData")
    public Object[] InvalidPhoneNumberData() {
        return new Object[]{
               "01012141618","+20101214161821235","+2"," ","+2@#$*&^*$@#^","+2abcabcabcabc"
        };
    }


}
