package test.homePageTest;

import com.testframework.BaseClass.BaseClass;
import com.testframework.PageObjects.HomePage;
import com.testframework.PageObjects.LoginPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest extends BaseClass {

    public LoginPage loginPage;
    public HomePage homePage;

    @BeforeMethod
    public void setupHomepage(){
        this.loginPage = new LoginPage(getDriver());
        this.homePage = new HomePage(getDriver());
    }

    @Step("Testcase 001")
    @Test(priority = 1)
    public void homepageAdminTest(){
        loginPage.loginFunction("Admin", "admin123");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean adminDisplayed = homePage.isDashBoardDisplays();
        if(adminDisplayed == true){
            String text = homePage.clickadminTab();
            System.out.println("Admin tab displayed "+text);
        }
        Assert.assertTrue(adminDisplayed, "Admin tab not displayed");
        System.out.println("Testcase Pass -> verify admin tab test");
    }

    @Step("Testcase 002")
    @Test(priority = 2)
    public void logout(){
        loginPage.loginFunction("Admin", "admin123");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        homePage.clickDropdown();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String logoutText = homePage.clickLogout();
        Assert.assertEquals(logoutText, "Login", "Logout failed");
        System.out.println("Testcase Pass -> verify logout test");
    }
}
