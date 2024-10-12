package TestRegisteration;
import Utils.Registeration.ClientUser;
import io.restassured.RestAssured;

public abstract class AbstractAPI {
    protected final String endPoint = "https://takhaty.runasp.net";
    protected final ClientUser user = new ClientUser(0,"Maro1122332211@",
            "marwanshamsooo@yahoo.com","Maro","khalil",
            "egypt","Cairo","cairo - egypt",
            1,"+201017121378");
    protected static String userId;
    protected static String userToken;

    public AbstractAPI(){
        RestAssured.baseURI = endPoint;
    }
}
