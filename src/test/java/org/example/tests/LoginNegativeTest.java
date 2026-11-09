package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginNegativeTest extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {

        return new Object[][]{
                {"wrong_user", "secret_sauce"},
                {"standard_user", "wrong_password"},
                {"", "secret_sauce"},
                {"standard_user", ""}
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginTest(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(username, password);

        String actualError = loginPage.getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Epic sadface"),
                "Expected login error was not displayed"
        );
    }
}