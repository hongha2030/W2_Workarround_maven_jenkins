package test.java.Testcases;

import main.java.Core.PageObject;
import main.java.Pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by Abraham on 1/9/2017.
 */
public class LoginPageTest extends PageObject{
    private String validEmail = "lthha+1@lhv.vn";
    private String validPassword = "hamaymay";
    private String invalidEmail = "aabab@";
    private String invalidPass = "";
    private String wrongPass = "ababababababa";

    private String messageInvalidEmail = "This is not a valid email address.";
    private String messageInvalidPass = "Password too short. Minimum length is 7 characters.";
    private String messageInvalidAccount = "Your email or password is incorrect.";
    LoginPage login = null;

    @Test(priority = 0, description = "Login failed when trying to login by invalid account")
    public void testInvalidAccount() {

        login = PageFactory.initElements(driver,LoginPage.class);
        waitForElementVisible(login.getEmailElement());
        login.login(validEmail,wrongPass);
        waitForElementVisible(login.getMessageAtPasswordElement());
        Assert.assertTrue (login.getMessageInvalidAccount().equals(messageInvalidAccount), "Message isn't correct");
        Reporter.log("Testcase: Testing login page by correct email and wrong password", true);
    }

    @Test(priority = 1, description = "System validates invalid account")
    public void testInvalidEmail() {

        login = PageFactory.initElements(driver,LoginPage.class);
        waitForElementVisible(login.getEmailElement());
        login.login(invalidEmail,validPassword);
        Assert.assertTrue (login.getMessageInvalidEmail().equalsIgnoreCase(messageInvalidEmail), "Message isn't correct");

        Reporter.log("Testcase: Testing login page by invalid email", true);
    }

    @Test(priority = 2, description = "System validates invalid password")
    public void testInvalidPass() {

        waitForElementVisible(login.getEmailElement());
        login.login(invalidEmail, "");

        waitForElementVisible(login.getMessageAtPasswordElement());
        Assert.assertTrue (login.getMessageInvalidPasword().equals(messageInvalidPass), "Message isn't correct");
        Reporter.log("Testcase: Testing login page by invalid pasword (password < 7 characters)", true);
    }

    @Test(priority = 3, description = "System validates blank value")
    public void testBlankValue() {
        waitForElementVisible(login.getEmailElement());
        login.login("","");
        waitForElementVisible(login.getMessageAtPasswordElement());
        Assert.assertTrue (login.getMessageInvalidEmail().equals(messageInvalidEmail), "Message isn't correct");
        Assert.assertTrue (login.getMessageInvalidPasword().equals(messageInvalidPass), "Message isn't correct");
        Reporter.log("Testcase: Testing login page by blank email and password",true);
    }

    @Test(priority = 4, description = "Login successful when login by valid account")
    public void testValidAccount() {
        waitForElementVisible(login.getEmailElement());
        login.login(validEmail,validPassword);
        Assert.assertTrue(login.messageNotShow(),"Login fail");

        Reporter.log("Testcase: Login successful when try to login by correct email and password", true);
    }
}
