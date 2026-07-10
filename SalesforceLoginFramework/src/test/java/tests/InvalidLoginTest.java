package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void testInvalidCredentials() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateTo(testData.getProperty("url"));

            loginPage.doLogin(
                testData.getProperty("invalid.username"),
                testData.getProperty("invalid.password")
            );

            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(
                actualError.contains(testData.getProperty("error.message")),
                "Error message mismatch — expected containing: " + testData.getProperty("error.message")
                    + " but got: " + actualError
            );
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials test failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testBlankCredentials() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateTo(testData.getProperty("url"));

            loginPage.doLogin("", "");

            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(
                actualError.contains(testData.getProperty("error.message")),
                "Error message mismatch for blank credentials — got: " + actualError
            );
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Blank credentials test failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testInvalidEmailFormat() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateTo(testData.getProperty("url"));

            loginPage.enterUsername("notanemail");
            loginPage.enterPassword(testData.getProperty("invalid.password"));
            loginPage.clickLogin();

            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(
                actualError.contains(testData.getProperty("error.message")),
                "Error message mismatch for invalid email format — got: " + actualError
            );
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Invalid email format test failed: " + e.getMessage(), e);
        }
    }
}
