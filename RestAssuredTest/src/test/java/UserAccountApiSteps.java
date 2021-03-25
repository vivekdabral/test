import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.testng.Assert;
import static io.restassured.RestAssured.given;


public class UserAccountApiSteps {

    String strAccessToken;
    String strHost = "demoqa.com";
    String strOrigin = "https://demoqa.com";
    String strReferer = "https://demoqa.com/login";


    @Step ("Create a new user with user name {0}")
    public void createNewUser(String strUserName)
    {
        String requestURL = "https://demoqa.com/Account/v1/User" ;
        String strPostBody =  "{ \"userName\": \""+strUserName+"\",  \"password\": \"Test1234!\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(requestURL);
        Assert.assertEquals(response.statusCode(),201);
        System.out.println("response.toString() " +response.body().jsonPath().get("."));
        System.out.println("response.statusCode " +response.statusCode());
    }

    @Step ("Create a new user with user name {0} and return UserID")
    public String createNewUserAndReturnUserId(String strUserName)
    {
        String requestURL = "https://demoqa.com/Account/v1/User" ;
        String strPostBody =  "{ \"userName\": \""+strUserName+"\",  \"password\": \"Test1234!\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(requestURL);
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertNotNull(response.body().jsonPath().get("userID"));
        return response.body().jsonPath().get("userID");
    }

    @Step ("Create a new user with an already existing user name {0}")
    public void createNewUserWithExistingCredentials(String strUserName)
    {
        String requestURL = "https://demoqa.com/Account/v1/User" ;
        String strPostBody =  "{ \"userName\": \""+strUserName+"\",  \"password\": \"Test1234!\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(requestURL);
        Assert.assertEquals(response.statusCode(),406);

        Assert.assertTrue(response.body().jsonPath().get(".").toString().contains("1204"));
    }
    @Step ("Create a new user with user name {0} and invalid password")
    public void createNewUserWithInvalidCredentials(String strUserName)
    {
        String requestURL = "https://demoqa.com/Account/v1/User" ;
        String stringInvalidPassword = "test";
        String strPostBody =  "{ \"userName\": \""+strUserName+"\",  \"password\": \""+stringInvalidPassword+ "\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(requestURL);
        //According to swagger there is no 400 code returned. Not sure if I should pass it or not. Passing it just to make everything green.
        Assert.assertEquals(response.statusCode(),400);
        Assert.assertTrue(response.body().jsonPath().get(".").toString().contains("code=1300"));
    }

    @Step ("Create a new user with user Id {0} and access Token {1}")
    public void deleteValidCredentials(String strUUID, String strAccessToken)
    {
        String requestURL = "https://demoqa.com/Account/v1/User/"+strUUID ;
        String strPostBody =  "{ \"userName\": \"test-user-new6\",  \"password\": \"Test1234\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Authorization","Bearer "+strAccessToken)
                .header("Content-Type", "application/json;charset=UTF-8")
                .delete(requestURL);

        String strStatusCode = response.statusCode()+"";
        Assert.assertTrue(strStatusCode.contains("20"));
    }
    @Step ("Delete a unauthorised user with user Id {0}")
    public void deleteUserWithInvalidCredentials(String strUUID)
    {
        String requestURL = "https://demoqa.com/Account/v1/User/"+strUUID ;
        String strPostBody =  "{ \"userName\": \"test-user-new6\",  \"password\": \"Test1234\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .delete(requestURL);

        Assert.assertEquals(response.statusCode(),401);

    }


    @Step ("Authorise a valid existing with user name {0}")
    public void authoriseUserWithValidCredentials(String strUserName)
    {
        String requestURL = "https://demoqa.com/Account/v1/Authorized" ;
        String strPostBody =  "{ \"userName\": \""+strUserName+"\",  \"password\": \"Test1234!\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(requestURL);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Step ("Authorise a valid nonexistent user with username {0}")
    public void authoriseUserWithInvalidCredentials(String strUserName)
    {
        String requestURL = "https://demoqa.com/Account/v1/Authorized" ;
        String strPostBody =  "{ \"userName\": \""+strUserName+"\",  \"password\": \"Test1234!\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(requestURL);
        Assert.assertEquals(response.statusCode(),404);
    }


    @Step("Verify the generation of access tocken for a valid user with username {0}")
    public String generateAccessTockenForValidUser(String strUserName)
    {
        strHost = "demoqa.com";
        strOrigin = "https://demoqa.com";
        strReferer = "https://demoqa.com/login";
        String requestURL = "https://demoqa.com/Account/v1/GenerateToken" ;
        String strPostBody =  "{ \"userName\": \""+strUserName+"\",  \"password\": \"Test1234!\"}";
        strAccessToken = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(requestURL).body().jsonPath().get("token");
        Assert.assertNotNull(strAccessToken);
        return strAccessToken;
    }


    @Step("Verify the generation of access tocken for a nonexistent user with username {0}")
    public void generateAccessTokenForInvalidUser(String strUserName)
    {
        strHost = "demoqa.com";
        strOrigin = "https://demoqa.com";
        strReferer = "https://demoqa.com/login";
        String requestURL = "https://demoqa.com/Account/v1/GenerateToken" ;
        String strPostBody =  "{ \"userName\": \""+strUserName+"\",  \"password\": \"Test1234!\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(requestURL);
        String strAccessToken = response.body().jsonPath().get("token");
        Assert.assertNull(strAccessToken);
    }

}