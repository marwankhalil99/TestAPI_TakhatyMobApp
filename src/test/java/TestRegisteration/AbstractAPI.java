package TestRegisteration;
import Utils.Registeration.ClientUser;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAPI {
    protected final String endPoint = "https://takhaty-test.runasp.net";
    protected final ClientUser user = new ClientUser(0,"Maro1122332211@",
            "marwanshamsooo@yahoo.com","Maro","khalil",
            "egypt","Cairo","cairo - egypt",
            1,"+201017121378");
    protected static String userId;
    protected static String userToken;
    protected static List<String> Q1Answers = new ArrayList<String>();
    protected static List<String> Q2Answers = new ArrayList<String>();
    protected static String examID = "35906930-92cc-4464-ef7e-08dcbec630d0";

    public AbstractAPI(){
        RestAssured.baseURI = endPoint;
    }
}
