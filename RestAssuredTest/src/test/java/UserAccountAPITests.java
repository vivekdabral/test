import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;


@RunWith(SerenityRunner.class)

//Test class to cover the UserAccount Scenarios
public class UserAccountAPITests {

    @Steps
    private UserAccountApiSteps userAccountApiSteps;

    @Test
    public void createNewUserWithValidCredentials() throws Exception {
        userAccountApiSteps.createNewUser(getUniqueUserName());

    }
    @Test
    public void createNewUserWithExistingCredentials() throws Exception {
        String strTestUserName = getUniqueUserName();
        userAccountApiSteps.createNewUser(strTestUserName);
        userAccountApiSteps.createNewUserWithExistingCredentials(strTestUserName);

    }

    @Test
    public void createNewUserWitInvalidCredentials() throws Exception {
        String strTestUserName = getUniqueUserName();
        userAccountApiSteps.createNewUserWithInvalidCredentials(strTestUserName);
    }

    @Test
    public void authoriseAnExistingUserWithValidCredentials() throws Exception {
        String strTestUserName = getUniqueUserName();
        userAccountApiSteps.createNewUser(strTestUserName);
        userAccountApiSteps.authoriseUserWithValidCredentials(strTestUserName);
    }

    @Test
    public void authoriseAnExistingUserWithInvalidCredentials() throws Exception {
        String strTestUserName = getUniqueUserName();
        userAccountApiSteps.authoriseUserWithInvalidCredentials(strTestUserName);
    }

    @Test
    public void generateAccessTokenForValidUser() throws Exception {
        String strTestUserName = getUniqueUserName();
        userAccountApiSteps.createNewUser(strTestUserName);
        userAccountApiSteps.generateAccessTockenForValidUser(strTestUserName);
    }

    @Test
    public void generateAccessTokenForInvalidUser() throws Exception {
        String strTestUserName = getUniqueUserName();
        userAccountApiSteps.generateAccessTokenForInvalidUser(strTestUserName);
    }

    @Test
    public void deleteValidUser() throws Exception {
        String strTestUserName = getUniqueUserName();
        String strUserID = userAccountApiSteps.createNewUserAndReturnUserId(strTestUserName);
        String strAccessToken= userAccountApiSteps.generateAccessTockenForValidUser(strTestUserName);
        userAccountApiSteps.deleteValidCredentials(strUserID, strAccessToken);
    }

    @Test
    public void deleteUserWithInvalidCredentials() throws Exception {
        String strUserId = getUniqueUserName();
        userAccountApiSteps.deleteUserWithInvalidCredentials(strUserId);
    }

    private String getUniqueUserName()
    {
        Random random = new Random(System.nanoTime());
        int uniqueInt = random.nextInt(1000000000);
        return "test-user-"+uniqueInt;
    }
}