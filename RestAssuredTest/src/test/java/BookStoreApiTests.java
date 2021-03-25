import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)

public class BookStoreApiTests {

    @Steps
    private UserAccountApiSteps userAccountApiSteps;

    @Steps
    private BookStoreApiSteps bookStoreApiSteps;

    private String strUserID;
    private String strAccessToken;

//    public void createTestUser()
//    {
//        String strTestUserName = getUniqueUserName();
//        strUserID = userAccountApiSteps.createNewUserAndReturnUserId(strTestUserName);
//        strAccessToken= userAccountApiSteps.generateAccessTockenForValidUser(strTestUserName);
//    }
//
//
//    @Test
//    public void registerBookToUser(){
//        createTestUser();
//        String strRandomISBN = bookStoreApiSteps.getIsbnOfARandomBook();
//        bookStoreApiSteps.assignBookToUser(strUserID,strRandomISBN,strAccessToken);
//    }
//
//    @Test
//    public void getBooksRegisteredToUser() {
//        createTestUser();
//        String strRandomISBN = bookStoreApiSteps.getIsbnOfARandomBook();
//        bookStoreApiSteps.assignBookToUser(strUserID,strRandomISBN,strAccessToken);
//
//        List<String> listRegisteredBooks = bookStoreApiSteps.getRegisteredBook(strUserID,strAccessToken);
//        Assert.assertTrue(listRegisteredBooks.contains(strRandomISBN));
//    }
//
//    @Test
//    public void removeABookFromUserAccount() {
//        createTestUser();
//        String strRandomISBN = bookStoreApiSteps.getIsbnOfARandomBook();
//        bookStoreApiSteps.assignBookToUser(strUserID,strRandomISBN,strAccessToken);
//        List<String> listRegisteredBooks = bookStoreApiSteps.getRegisteredBook(strUserID,strAccessToken);
//        Assert.assertTrue(listRegisteredBooks.contains(strRandomISBN));
//
//        bookStoreApiSteps.removeBookFromUser(strRandomISBN,strUserID,strAccessToken);
//        listRegisteredBooks = bookStoreApiSteps.getRegisteredBook(strUserID,strAccessToken);
//        Assert.assertTrue(listRegisteredBooks.isEmpty());
//    }
//
//    @Test
//    public void removeAllBooksRegisteredWithUserAccount() {
//        createTestUser();
//        List<String> listISBNs = bookStoreApiSteps.getTopBooks(4);
//        printAllTheItemsInList(listISBNs);
//        for (int i=0; i<listISBNs.size();i++) {
//            bookStoreApiSteps.assignBookToUser(strUserID, listISBNs.get(i), strAccessToken);
//        }
//        List<String> listRegisteredBooks = bookStoreApiSteps.getRegisteredBook(strUserID,strAccessToken);
//
//        //Verify that added books are the same as the books registered with user
//        Assert.assertEquals(listRegisteredBooks.size(),listISBNs.size());
//
//        bookStoreApiSteps.removeAllBooksFromUser(strUserID,strAccessToken);
//        listRegisteredBooks = bookStoreApiSteps.getRegisteredBook(strUserID,strAccessToken);
//
//        //Verify that there is no book registered to the user anymore
//        Assert.assertTrue(listRegisteredBooks.isEmpty());
//    }
//
//    @Test
//    public void removeAllBookFromUserIfThereIsNoBooks()
//    {
//        createTestUser();
//        bookStoreApiSteps.removeAllBookFromUserIfThereIsNoBooks(strUserID,strAccessToken);
//    }

    private String getUniqueUserName()
    {
        Random random = new Random(System.nanoTime());
        int uniqueInt = random.nextInt(1000000000);
        return "test-user-"+uniqueInt;
    }


    private void printAllTheItemsInList(List<String> list)
    {
        for (int i=0; i<list.size();i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void getQuery()
    {
//        bookStoreApiSteps.getResult();
        bookStoreApiSteps.getWrongURL();
    }

//    @Test
//    public void sr()
//    {
//        String requestURL = "http://localhost:8081/getTopResultsApi" ;
//        //String strPostBody =  "{ \"userName\": \"test-user-new5\",  \"password\": \"Test1234!\"}";
//        given()
//                .auth()
//                .basic("admin", "password").when().queryParam("count","3").get(requestURL).then().statusCode(200);
//    }


}
