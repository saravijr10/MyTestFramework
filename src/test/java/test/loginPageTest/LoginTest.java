package test.loginPageTest;

import com.testframework.BaseClass.BaseClass;
import com.testframework.PageObjects.HomePage;
import com.testframework.PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class LoginTest extends BaseClass {

    public LoginPage loginPage;
    public HomePage homePage;

    @BeforeMethod
    public void setUpLoginPage(){
        this.loginPage = new LoginPage(getDriver());
        this.homePage = new HomePage(getDriver());
    }

    @Test(priority = 1)
    public void validCredentialsTest(){
        loginPage.loginFunction("Admin", "admin123");
        Assert.assertTrue(homePage.isDashBoardDisplays(), "Login failure");
        System.out.println("Testcase Pass -> verify valid credentials test");
    }


    @Test(priority = 2)
    public void invalidCredentialsTest(){
        loginPage.loginFunction("Admin", "admin1232342");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String errmessage = loginPage.getErrorMessage();
        Assert.assertEquals(errmessage,"Invalid credentials", "error message not displayed");
        System.out.println("Testcase Pass -> verify invalid credentials test");
    }
}
