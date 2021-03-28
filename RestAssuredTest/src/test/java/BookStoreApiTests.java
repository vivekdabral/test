import io.restassured.response.Response;
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
    private BookStoreApiSteps bookStoreApiSteps;


    @Test
    public void loginWithValidCredentialsAndVerifyResponseCorrectnessWithStatusCode()
    {
        //Assign a random value to count
        int iRandomCount = getRandomNumberUsingNextInt(0,5);
        Response response= bookStoreApiSteps.loginWithValidCredentialsAndCallApiWithCount("admin", "password", "3");
        bookStoreApiSteps.isStatusCodeCorrect(response.getStatusCode(), 200);
        bookStoreApiSteps.isCorrectObjectsReturned(response.getBody().asString(),3);
    }

    @Test
    public void verifyReturnedStatusCodeWhenIncorrectEndPointIsCalled()
    {
        int iRandomCount = getRandomNumberUsingNextInt(1,5);
        Response response= bookStoreApiSteps.callAPIWithInvalidEndpoint("getTopResultsAp", iRandomCount);
        bookStoreApiSteps.isStatusCodeCorrect(response.getStatusCode(), 404);

    }

    @Test
    public void verifyReturnedStatusCodeWhenIncorrectApiIsCalledWithWrongQueryParamerName()
    {
        int iRandomCount = getRandomNumberUsingNextInt(1,5);
        Response response= bookStoreApiSteps.callAPIWithInvalidQueryParameter("counts", iRandomCount);
        bookStoreApiSteps.isStatusCodeCorrect(response.getStatusCode(), 400);

    }
    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
