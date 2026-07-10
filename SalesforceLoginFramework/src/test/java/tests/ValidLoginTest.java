package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class ValidLoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateTo(testData.getProperty("url"));
            Assert.assertTrue(loginPage.getPageTitle().contains("Login"));

            loginPage.doLogin(
                testData.getProperty("valid.username"),
                testData.getProperty("valid.password")
            );

            String expectedDashboardTitle = testData.getProperty("dashboard.title");
            Assert.assertTrue(
                loginPage.getPageTitle().contains(expectedDashboardTitle),
                "Login failed — expected dashboard title but got: " + loginPage.getPageTitle()
            );
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Valid login test failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testValidLoginWithRememberMe() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateTo(testData.getProperty("url"));

            loginPage.enterUsername(testData.getProperty("valid.username"));
            loginPage.enterPassword(testData.getProperty("valid.password"));
            loginPage.selectRememberMe(true);
            loginPage.clickLogin();

            String expectedDashboardTitle = testData.getProperty("dashboard.title");
            Assert.assertTrue(
                loginPage.getPageTitle().contains(expectedDashboardTitle),
                "Login with remember me failed — expected dashboard but got: " + loginPage.getPageTitle()
            );
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Valid login with remember me test failed: " + e.getMessage(), e);
        }
    }
}
