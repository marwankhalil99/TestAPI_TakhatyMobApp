package TestRegisteration;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Utils.Registeration.ClientUser;
import Requests.RegisterationReq;

public class TC_Registeration extends AbstractAPI{
    private RegisterationReq request = new RegisterationReq();
    @Test
    public void registerUser()  {
        System.out.println("Test user registration process with Valid data ( Positive Scenario )");
        String regResponseStr = request.registerationReqset(user.getUserRole(),user.getPassword(),user.getEmail(),user.getFirstName()
                ,user.getLastName(),user.getCountry(),user.getCity(),user.getAddress(),user.getGender(),user.getPhoneNumber(), 200);
        JsonPath regResJson = new JsonPath(regResponseStr);
        Assert.assertEquals(regResJson.getBoolean("isSuccess"), true);
        Assert.assertEquals(regResJson.getInt("statusCode"), 200);
        Assert.assertEquals(regResJson.getString("data.validationMessage"), "Check Your Email We Have Sent To You OTP To Confirm Your Email");
        Assert.assertEquals(regResJson.getBoolean("data.isValid"), true);
    }

    @Test(dataProvider = "InvalidEmailData",dependsOnMethods = {"TestRegisteration.TC_Registeration.registerUser"})
    public void registerUserWithInvalidEmail(String email) {
        System.out.println("Test user registration process with valid role, password, firstname, lastname, country, city, address, gender, phone number and Invalid email ( "+email+" )");
        String regResponseStr = request.registerationReqset(user.getUserRole(),user.getPassword(),email,user.getFirstName()
                ,user.getLastName(),user.getCountry(),user.getCity(),user.getAddress(),user.getGender(),user.getPhoneNumber() , 400);
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
    public void registerUserWithInvalidPassword( String password) {
        System.out.println("Test user registration process with valid role, email, firstname, lastname, country, city, address, gender, phone number and Invalid password ( "+password+" )");
        String regResponseStr = request.registerationReqset(user.getUserRole(),password,user.getEmail(),user.getFirstName()
                ,user.getLastName(),user.getCountry(),user.getCity(),user.getAddress(),user.getGender(),user.getPhoneNumber() , 400);

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
    public Object[] InvalidPasswordData() {
        return new Object[]{
                " "," Password12@","password12@","PASSWORD12@","Password12","Password@","pass12@","12345678910"
                        ,"#@##@#@#@#@#@#@","password123@password123@password123@pasword123@"};
    }
    @Test(dataProvider = "InvalidNameData")
    public void registerUserWithInvalidName( String fname , String lname) {
        // Write your test case here
        System.out.println("Test user registration process with valid role, email, password, country, city, address, gender, phone number and Invalid first name and last name ( "+fname+" "+lname+" )");
        String regResponseStr = request.registerationReqset(user.getUserRole(),user.getPassword(),user.getEmail(),fname
                ,lname,user.getCountry(),user.getCity(),user.getAddress(),user.getGender(),user.getPhoneNumber() , 400);
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
                {" "," "},{"&^%@#^@$#&%","@&%$&@%@&"},{"a","a"},{"12131","31515"}
                ,{"mar wan","kha lil"},{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}
        };
    }
    @Test(dataProvider = "InvalidCountryData")
    public void registerUserWithInvalidCountry(String country) {
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, city, address, gender, phone number and Invalid country ( "+country+" )");
        String regResponseStr = request.registerationReqset(user.getUserRole(),user.getPassword(),user.getEmail(),user.getFirstName()
                ,user.getLastName(),country,user.getCity(),user.getAddress(),user.getGender(),user.getPhoneNumber() , 400);
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
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, country, address, gender, phone number and Invalid city ( "+city+" )");
        String regResponseStr = request.registerationReqset(user.getUserRole(),user.getPassword(),user.getEmail(),user.getFirstName()
                ,user.getLastName(),user.getCountry(),city,user.getAddress(),user.getGender(),user.getPhoneNumber() , 400);
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
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, country, city, gender, phone number and Invalid address ( "+address+" )");
        String regResponseStr = request.registerationReqset(user.getUserRole(),user.getPassword(),user.getEmail(),user.getFirstName()
                ,user.getLastName(),user.getCountry(),user.getCity(),address,user.getGender(),user.getPhoneNumber() , 400);
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
        userTest.setGender(gender);
        String regResponseStr = request.registerationReqset(user.getUserRole(),user.getPassword(),user.getEmail(),user.getFirstName()
                ,user.getLastName(),user.getCountry(),user.getCity(),user.getAddress(),gender,user.getPhoneNumber() , 400);
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
        System.out.println("Test user registration process with valid role, email, password, firstname, lastname, country, address, gender, city and Invalid phone number ( "+phoneNumber+" )");
        String regResponseStr = request.registerationReqset(user.getUserRole(),user.getPassword(),user.getEmail(),user.getFirstName()
                ,user.getLastName(),user.getCountry(),user.getCity(),user.getAddress(),user.getGender(),phoneNumber,400);
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
