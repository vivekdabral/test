import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import net.thucydides.core.annotations.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class BookStoreApiSteps {

    String strHost = "demoqa.com";
    String strOrigin = "https://demoqa.com";
    String userNameForBookStore;
    String strBaseURL;
    String strAccessToken ="Something";


//    BookStoreApiSteps()
//    {
//        userNameForBookStore = getUniqueUserName();
//        String requestURL = "https://demoqa.com/Account/v1/User" ;
//        //String strPostBody =  "{ \"userName\": \"test-user-new5\",  \"password\": \"Test1234!\"}";
//        String strPostBody =  "{ \"userName\": \""+userNameForBookStore+"\",  \"password\": \"Test1234!\"}";
//        Response response = given()
//                .header("Accept", "application/json")
//                .header("Accept-Encoding", "gzip, deflate, br")
//                .header("Connection", "keep-alive")
//                .header("Host", strHost)
//                .header("Origin", strOrigin)
////                .header("Referer","https://demoqa.com/swagger/")
//                .header("Content-Type", "application/json;charset=UTF-8")
//                .body(strPostBody)
//                .post(requestURL);
//    }


    public void testCode()
    {

    }

//    public void getRegisteredBook()
//    {
//
//        strBaseURL = "https://demoqa.com/Account/v1/User/d92cf6f7-99f1-4a90-8f3c-3bbc39b1ac25";
//        testCode();
//        String strPostBody = "{\"dashboardName\":\"Test"+iRandom+"\",\"setting\":[]}";
//        String sDashoboardId = given().auth().oauth2(strAccessToken)
//                .header("Accept", "application/json, text/plain, */*")
//                .header("Accept-Encoding", "gzip, deflate, br")
//                .header("Authorization","Bearer "+strAccessToken)
//                .header("Connection", "keep-alive")
//                .header("Host", strHost)
//                .header("Origin", strOrigin)
////                .header("Referer",strOrigin+"/dashboard/overview")
//                .header("Content-Type", "application/json;charset=UTF-8")
//                //.queryParam("ISBN","9781449331818")
////                .queryParam("userId","d92cf6f7-99f1-4a90-8f3c-3bbc39b1ac25")
//                .get(strBaseURL).body().jsonPath().get("books").toString();
//        System.out.println("Dashboard Id "+sDashoboardId);
//
//    }

//    @Step ("Create a new user with user name {0}")
    @Step
    public List<String>  getListOfBooks()
    {
        String requestURL = "https://demoqa.com/BookStore/v1/Books" ;
        //String strPostBody =  "{ \"userName\": \"test-user-new5\",  \"password\": \"Test1234!\"}";
        Response response = given()
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .get(requestURL);

        List<String> jsonResponse = response.body().jsonPath().getList("books.isbn");
        return jsonResponse;
    }




    @Step("get list ISBN of top {0} books in the book store")
    public List<String> getTopBooks(int iCount)
    {
        iCount= iCount-1;
        return getListOfBooks().subList(0,iCount);

    }

    @Step("Get the ISBN of any random book in the book store")
    public String getIsbnOfARandomBook()
    {
        int iRandom = getRandomNumberUsingNextInt(0,getListOfBooks().size()-1);
        return  getListOfBooks().get(iRandom);
    }




    @Step("get the list of registered books to user with UserId {0}")
    public List<String> getRegisteredBook(String strUserId, String strAccessToken)
    {

        strBaseURL = "https://demoqa.com/Account/v1/User/"+strUserId;

        Response response = given().auth().oauth2(strAccessToken)
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Authorization","Bearer "+strAccessToken)
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .get(strBaseURL);
        return response.body().jsonPath().getList("books.isbn");

    }

    @Step ("Assign a book with isbn {1} to the user with UserId {0}")
    public void assignBookToUser(String strUserId, String strISBN, String strAccessToken)
    {

        strBaseURL = "https://demoqa.com/BookStore/v1/Books";

        String strPostBody = "{\"userId\":\""+strUserId+"\",\"collectionOfIsbns\":[{\"isbn\":\""+strISBN+"\"}]}";
        System.out.println(strPostBody);
        String sDashoboardId = given().auth().oauth2(strAccessToken)
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Authorization","Bearer "+strAccessToken)
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .post(strBaseURL).body().jsonPath().get(".").toString();
        System.out.println("Dashboard Id "+sDashoboardId);

    }

    @Step("Remove Book with ISBN {0} from user account of the user with UserID {1}")
    public void removeBookFromUser(String strISBN, String strUserID, String strAccessToken)
    {

        strBaseURL = "https://demoqa.com/BookStore/v1/Book";
        testCode();
        String strPostBody = "{\"isbn\":\""+strISBN+"\",\"userId\":\""+strUserID+"\"}";
        Response sDashoboardId = given().auth().oauth2(strAccessToken)
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Authorization","Bearer "+strAccessToken)
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
//                .header("Referer",strOrigin+"/dashboard/overview")
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(strPostBody)
                .delete(strBaseURL);
        System.out.println("Dashboard Id "+sDashoboardId.getStatusCode());

    }


    @Step("Remove all the books registered to the user with UserId {0}")
    public void removeAllBooksFromUser(String strUserID, String strAccessToken)
    {

        strBaseURL = "https://demoqa.com/BookStore/v1/Books";

        Response sDashoboardId = given().auth().oauth2(strAccessToken)
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Authorization","Bearer "+strAccessToken)
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .queryParam("UserId" ,strUserID)
                .delete(strBaseURL);

        //Status should be 204
        System.out.println("Dashboard Id "+sDashoboardId.getStatusCode());

    }

    @Step ("Remove all the books registered to the user with UserId {0} if user does not have any book registered")
    public void removeAllBookFromUserIfThereIsNoBooks(String strUserID, String strAccessToken)
    {
        strBaseURL = "https://demoqa.com/BookStore/v1/Books";
        Response sDashoboardId = given().auth().oauth2(strAccessToken)
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Authorization","Bearer "+strAccessToken)
                .header("Connection", "keep-alive")
                .header("Host", strHost)
                .header("Origin", strOrigin)
                .header("Content-Type", "application/json;charset=UTF-8")
                .queryParam("UserId" ,"d92cf6f7-99f1-4a90-8f3c-3bbc39b1ac25")
                .delete(strBaseURL);
        //Status should be 204
        System.out.println("Dashboard Id "+sDashoboardId.getStatusCode());

    }

    @Step
    public String  getResult()
    {
        String requestURL = "http://localhost:8081/getTopResultsApi" ;
        //String strPostBody =  "{ \"userName\": \"test-user-new5\",  \"password\": \"Test1234!\"}";
        Response response = given()
            .auth()
            .basic("admin", "password").queryParam("count","3").get(requestURL);

        //String jsonResponse = response.body().toString();
        ResponseBody body = response.getBody();

        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());
        return body.asString();
    }

    @Step
    public void  getWrongURL()
    {
        String requestURL = "http://localhost:8081/getTopResultsApi" ;
        //String strPostBody =  "{ \"userName\": \"test-user-new5\",  \"password\": \"Test1234!\"}";
        given()
                .auth()
                .basic("admin", "password").when().queryParam("count","3").get(requestURL).then().statusCode(200);
//                .basic("admin", "password").get(requestURL);

        //String jsonResponse = response.body().toString();
//        ResponseBody body = response.getBody();
//        System.out.println("response.getStatusCode() is "+response.getStatusCode()) ;
//
//        // By using the ResponseBody.asString() method, we can convert the  body
//        // into the string representation.
//        System.out.println("Response Body is: " + body.asString());
//        return body.asString();
    }

    private String getUniqueUserName()
    {
        Random random = new Random(System.nanoTime());
        int uniqueInt = random.nextInt(1000000000);
        return "test-user-"+uniqueInt;
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}